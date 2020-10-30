package ru.vsu.cs;

public class ScreenConverter {
    private double cornerX, cornerY, realW, realH;
    private int screenW, screenH;

    public ScreenConverter(double cornerX, double cornerY, double realW, double realH, int screenW, int screenH) {
        this.cornerX = cornerX;
        this.cornerY = cornerY;
        this.realW = realW;
        this.realH = realH;
        this.screenW = screenW;
        this.screenH = screenH;
    }

    public ScreenPoint r2s(ru.vsu.cs.RealPoint p) {
        double x = (p.getX() - cornerX) * screenW / realW;
        double y = (cornerY - p.getY()) * screenH / realH;
        return new ScreenPoint((int) x, (int) y);
    }

    public ru.vsu.cs.RealPoint s2r(ScreenPoint p) {
        double x = p.getX() * realW / screenW + cornerX;
        double y = cornerY - p.getY() * realH / screenH;
        return new ru.vsu.cs.RealPoint(x, y);
    }

    public void setCornerX(double cornerX) {
        this.cornerX = cornerX;
    }

    public void setCornerY(double cornerY) {
        this.cornerY = cornerY;
    }

    public void setRealW(double realW) {
        this.realW = realW;
    }

    public void setRealH(double realH) {
        this.realH = realH;
    }

    public void setScreenW(int screenW) {
        this.screenW = screenW;
    }

    public void setScreenH(int screenH) {
        this.screenH = screenH;
    }

    public double getCornerX() {
        return cornerX;
    }

    public double getCornerY() {
        return cornerY;
    }

    public double getRealW() {
        return realW;
    }

    public double getRealH() {
        return realH;
    }

    public int getScreenW() {
        return screenW;
    }

    public int getScreenH() {
        return screenH;
    }
}
