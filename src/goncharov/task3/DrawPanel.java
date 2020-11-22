package goncharov.task3;

import goncharov.task3.formules.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class DrawPanel extends JPanel implements MouseMotionListener, MouseListener, MouseWheelListener, KeyListener {

    private ScreenConverter sc = new ScreenConverter(-6, 6, 12, 12, 1920, 1080);
    private Line xAxis = new Line(-1, 0, 1, 0);
    private Line yAxis = new Line(0, -1, 0, 1);
    private ArrayList<Line> allLines = new ArrayList<>();
    private double scale = 1;
    private ArrayList<IFunction> functions = new ArrayList<>();
    private ArrayList<Line> lines = new ArrayList<>();
    private IFunction function;
    private JButton draw = new JButton("DRAW");
    private JTextArea getFunction = new JTextArea("1");

    public void setFunction(IFunction function) {
        this.function = function;
    }

    public DrawPanel() {
        this.addMouseMotionListener(this);
        this.addMouseListener(this);
        this.addMouseWheelListener(this);
    }

    @Override
    public void paint(Graphics gr) {
        sc.setScreenW(getWidth());
        sc.setScreenH(getHeight());
        BufferedImage bi = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics bi_g = bi.createGraphics();
        bi_g.setColor(Color.WHITE);
        bi_g.fillRect(0, 0, bi.getWidth(), bi.getHeight());
        bi_g.setColor(Color.BLACK);

        for (int i = (int) sc.getRealX(); i <= (int) (sc.getRealX() + sc.getRealW()); i++) {
            bi_g.drawString(Integer.toString(i), (int) (sc.getScreenW() * (i - sc.getRealX()) / sc.getRealW()), sc.r2s(new RealPoint(0, 0)).getY());
        }
        for (int i = (int) sc.getRealY(); i >= (int) (sc.getRealY() - sc.getRealH()); i--) {
            bi_g.drawString(Integer.toString(i), sc.r2s(new RealPoint(0, 0)).getX(), (int) (sc.getScreenH() * (sc.getRealY() - i) / sc.getRealH()));
        }
        bi_g.dispose();
        PixelDrawer pd = new BufferedImagePixelDrawer(bi);
        LineDrawer ld = new DDALineDrawer(pd);
        //drawGrid(ld);
        //drawAxes(ld);
        /**/

        for (int i = (int) sc.getRealX(); i <= (int) (sc.getRealX() + sc.getRealW()); i++) {
            ld.drawLine(new ScreenPoint((int) ((sc.getScreenW() * (i - sc.getRealX()) / sc.getRealW())), 0),
                    new ScreenPoint((int) (sc.getScreenW() * (i - sc.getRealX()) / sc.getRealW()), sc.getScreenH()), Color.blue);

        }
        for (int i = (int) sc.getRealY(); i >= (int) (sc.getRealY() - sc.getRealH()); i--) {
            ld.drawLine(new ScreenPoint(0, (int) (sc.getScreenH() * (sc.getRealY() - i) / sc.getRealH())),
                    new ScreenPoint(sc.getScreenW(), (int) (sc.getScreenH() * (sc.getRealY() - i) / sc.getRealH())), Color.blue);
        }

        try {
            drawAll(ld);
        } catch (Exception e) {
            e.printStackTrace();
        }


        gr.drawImage(bi, 0, 0, null);
//        this.add(draw);
//        this.add(getFunction);
//        setFunction(Integer.parseInt(getFunction.getText()));
//        getFunction.setSize(20, 30);
//        super.paint(g);
    }

    private void drawAxes(LineDrawer ld) {
        ld.setColor(new Color(0, 0, 0));
        RealPoint xp1 = new RealPoint(sc.getRealX(), 0);
        RealPoint xp2 = new RealPoint(sc.getRealX() + sc.getRealW(), 0);
        RealPoint yp1 = new RealPoint(0, sc.getRealY());
        RealPoint yp2 = new RealPoint(0, sc.getRealY() - sc.getRealH());
        Line xAxis = new Line(xp1, xp2);
        Line yAxis = new Line(yp1, yp2);
        drawLine(ld, xAxis, Color.red);
        drawLine(ld, yAxis, Color.red);
    }

    private void drawLine(LineDrawer ld, Line l, Color color) {
        ld.drawLine(sc.r2s(l.getP1()), sc.r2s(l.getP2()), color);
    }

    private void drawFunction(LineDrawer ld) throws Exception {
        FunctionService.drawFunction(ld, sc, function);
    }

    private void drawAll(LineDrawer ld) throws Exception {
        drawLine(ld, xAxis, Color.red);
        drawLine(ld, yAxis, Color.red);
        for (Line q: allLines) {
            drawLine(ld, q, Color.pink);
        }
        if (newLine != null) {
            drawLine(ld, newLine, Color.pink);
        }
        if(function != null)
            drawFunction(ld);
        drawAxes(ld);
    }
    private ScreenPoint prevPoint = null;



    @Override
    public void mouseDragged(MouseEvent mouseEvent) {
        ScreenPoint currentPoint = new ScreenPoint(mouseEvent.getX(), mouseEvent.getY());
        if (prevPoint != null) {
            ScreenPoint deltaScreen = new ScreenPoint(currentPoint.getX() - prevPoint.getX(),
                    currentPoint.getY() - prevPoint.getY());
            RealPoint deltaReal = sc.s2r(deltaScreen);
            double vectorX = deltaReal.getX() - sc.getRealX();
            double vectorY = deltaReal.getY() - sc.getRealY();

            sc.setRealX(sc.getRealX() - vectorX);
            sc.setRealY(sc.getRealY() - vectorY);
            prevPoint = currentPoint;
        }

        if (newLine != null) {
            newLine.setP2(sc.s2r(currentPoint));
        }
        repaint();
    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) { // на нажатие

    }

    private Line newLine = null;

    @Override
    public void mousePressed(MouseEvent e) { // на момент нажатия
        if (e.getButton() == e.BUTTON3) {
            prevPoint = new ScreenPoint(e.getX(), e.getY());
        } else if (e.getButton() == e.BUTTON1) {
            newLine = new Line(sc.s2r(new ScreenPoint(e.getX(), e.getY())), sc.s2r(new ScreenPoint(e.getX(), e.getY())));
        }
        repaint();
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) { // отжатия клавиши
        if (mouseEvent.getButton() == mouseEvent.BUTTON3) {
            prevPoint = null;
        } else if (mouseEvent.getButton() == mouseEvent.BUTTON1) {
            allLines.add(newLine);
            newLine = null;
        }
        repaint();
    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        int clicks = e.getWheelRotation();
        double scale = 1;
        double step = (clicks > 0) ? 1.05 : 0.95;
        for (int i = Math.abs(clicks); i > 0; i--) {
            scale *= step;
        }
        sc.setRealW(scale * sc.getRealW());
        sc.setRealH(scale * sc.getRealH());
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {

//        if(key == KeyEvent.VK_7) {
//            setFunction(6);
//        }
//        if(key == KeyEvent.VK_8) {
//            setFunction(7);
//        }
//        if(key == KeyEvent.VK_9) {
//            setFunction(9);
//        }

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
