package goncharov.task3;

import goncharov.task3.formules.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class DrawPanel extends JPanel implements MouseMotionListener, MouseListener, MouseWheelListener, KeyListener {

    private ScreenConverter sc = new ScreenConverter(-10, 10, 20, 20, 1200, 800);
    private Line xAxis = new Line(-1, 0, 1, 0);
    private Line yAxis = new Line(0, -1, 0, 1);
    private ArrayList<Line> allLines = new ArrayList<>();
    private double scale = 1;
    private ArrayList<IFunction> functions = new ArrayList<>();
    private ArrayList<Line> lines = new ArrayList<>();
    private IFunction function;
    private RealPoint mouseCoordinates = new RealPoint(0, 0);
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

        drawFigure(bi_g);
//        bi_g.dispose();
        PixelDrawer pd = new BufferedImagePixelDrawer(bi);
        LineDrawer ld = new DDALineDrawer(pd);

        try {
            drawAll(ld, gr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        bi_g.dispose();

        gr.drawImage(bi, 0, 0, null);
    }



    private void drawFigure(Graphics g) {
        double step = scale;
        double xStart = sc.getRealX() - ((sc.getRealX() + step) % step);
        double yStart = sc.getRealY() - ((sc.getRealY() + step) % step) + step; // нетипичная формула для нахождения левой верхней точки по шкале, соседствующей с позицией левой верхней точки экрана
        double xFinish = sc.getRealX() + sc.getRealW();
        double yFinish = sc.getRealY() - sc.getRealH();
        for(double i = xStart; i < xFinish; i += step) {
            DecimalFormat df = new DecimalFormat("0.####");
            String s = df.format(i);
            if(Math.abs(i) < 0.000001) s = "0";
            int opacity = s.length() * 5;
            double x = sc.r2s(new RealPoint(i, sc.getRealY() - sc.getRealH())).getX() - opacity;
            double y = sc.r2s(new RealPoint(i, sc.getRealY() - sc.getRealH())).getY();
            g.drawString(s, (int)x, (int)y);
        }

        for(double i = yStart; i > yFinish; i -= step) {
            DecimalFormat df = new DecimalFormat("0.####");
            String s = df.format(i);
            if(Math.abs(i) < 0.000001) s = "0";
            int opacity = s.length() * 7;
            double x = sc.r2s(new RealPoint(sc.getRealX() + sc.getRealW(), i)).getX() - opacity;
            double y = sc.r2s(new RealPoint(sc.getRealX(), i)).getY() + 4;
            g.drawString(s, (int)x, (int)y);
        }
    }

    private void drawGrid(LineDrawer ld, Graphics g) {
        ld.setColor(new Color(131, 208, 255));
        g.setColor(Color.black);
        double step = scale;
        double xStart = sc.getRealX() - ((sc.getRealX() + step) % step);
        double yStart = sc.getRealY() - ((sc.getRealY() + step) % step) + step; // нетипичная формула для нахождения левой верхней точки по шкале, соседствующей с позицией левой верхней точки экрана
        double xFinish = sc.getRealX() + sc.getRealW();
        double yFinish = sc.getRealY() - sc.getRealH();
        double xWidth = xFinish - xStart;
        if(xWidth < 20) scale = 1;
        if(xWidth > 20 && xWidth < 50) scale = 5;
        if(xWidth > 50 && xWidth < 100) scale = 10;
        if(xWidth > 100) scale = 15;
        if(xWidth > 200) scale = 30;


        for(double i = xStart; i < xFinish; i += step) {
            drawLine(ld, new Line(new RealPoint(i, sc.getRealY()), new RealPoint(i, sc.getRealY() - sc.getRealH())), Color.blue);
        }
        for(double i = yStart; i > yFinish; i -= step) {
            drawLine(ld, new Line(new RealPoint(sc.getRealX(), i), new RealPoint(sc.getRealX() + sc.getRealW(), i)), Color.blue);
        }
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

    private void drawAll(LineDrawer ld, Graphics g) throws Exception {
        for (Line q: allLines) {
            drawLine(ld, q, Color.pink);
        }
        if (newLine != null) {
            drawLine(ld, newLine, Color.pink);
        }
        drawGrid(ld, g);
        drawAxes(ld);
        if(function != null) {
            drawFunction(ld);
        }
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
        sc.setRealX(sc.getRealX() * scale);
        sc.setRealY(sc.getRealY() * scale);
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
