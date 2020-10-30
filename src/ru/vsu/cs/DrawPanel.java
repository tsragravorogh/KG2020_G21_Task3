package ru.vsu.cs;

import ru.vsu.cs.formules.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class DrawPanel extends JPanel implements MouseMotionListener, MouseListener, MouseWheelListener {

    private ScreenConverter sc = new ScreenConverter(
            -2, 2, 4, 4, 800, 600);

    private Line xAxis = new Line(-1, 0, 1, 0);
    private Line yAxis = new Line(0, -1, 0, 1);

    private ArrayList<Line> allLines = new ArrayList<>();

    public DrawPanel() {
        this.addMouseMotionListener(this);
        this.addMouseListener(this);
        this.addMouseWheelListener(this);
    }

    @Override
    public void paint(Graphics g) {
        sc.setScreenW(getWidth());
        sc.setScreenH(getHeight());
        BufferedImage bi = new BufferedImage(getWidth(), getHeight(),
                BufferedImage.TYPE_INT_RGB);
        Graphics bi_g = bi.createGraphics();
        bi_g.setColor(Color.WHITE);
        bi_g.fillRect(0, 0, bi.getWidth(), bi.getHeight());
        bi_g.dispose();
        /**/
        PixelDrawer pd = new ru.vsu.cs.BufferedImagePixelDrawer(bi);
        LineDrawer ld = new ru.vsu.cs.DDALineDrawer(pd);
        FunctionOne f1 = new FunctionOne();
        FunctionTwo f2 = new FunctionTwo();
        FunctionThree f3 = new FunctionThree();
        FunctionFour f4 = new FunctionFour();
        FunctionFive f5 = new FunctionFive();
        FunctionSix f6 = new FunctionSix();
        FunctionSeven f7 = new FunctionSeven();
        ArrayList<RealPoint> points = f4.compute(-2, 2);
        for(int i = 0; i < points.size() - 1; i++) {
            RealPoint real1 = points.get(i);
            RealPoint real2 = points.get(i + 1);
            drawLine(ld, new Line(real1, real2));
        }
        drawAll(ld);
        /**/
        g.drawImage(bi, 0, 0, null);
    }

    private void drawAll(LineDrawer ld) {
        drawLine(ld, xAxis);
        drawLine(ld, yAxis);
        for (Line q: allLines) {
            drawLine(ld, q);
        }
        if (newLine != null) {
            drawLine(ld, newLine);
        }
    }

    private void drawLine(LineDrawer ld, Line l) {
        ld.drawLine(sc.r2s(l.getP1()), sc.r2s(l.getP2()));
    }

    private ScreenPoint prevPoint = null;

    @Override
    public void mouseDragged(MouseEvent mouseEvent) {
        ScreenPoint currentPoint = new ScreenPoint(mouseEvent.getX(), mouseEvent.getY());
        if (prevPoint != null) {
            ScreenPoint deltaScreen = new ScreenPoint(currentPoint.getX() - prevPoint.getX(),
                    currentPoint.getY() - prevPoint.getY());
            RealPoint deltaReal = sc.s2r(deltaScreen);
            double vectorX = deltaReal.getX() - sc.getCornerX();
            double vectorY = deltaReal.getY() - sc.getCornerY();

            sc.setCornerX(sc.getCornerX() - vectorX);
            sc.setCornerY(sc.getCornerY() - vectorY);
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
}
