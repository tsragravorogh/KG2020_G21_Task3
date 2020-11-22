package goncharov.task3;

public class ScreenConverter {
    private double realX, realY, realW, realH;
    private int screenW, screenH;

    public ScreenConverter(double realX, double realY, double realW, double realH, int screenW, int screenH) {
        this.realX = realX;
        this.realY = realY;
        this.realW = realW;
        this.realH = realH;
        this.screenW = screenW;
        this.screenH = screenH;
    }

    public ScreenPoint r2s(RealPoint p) {
        double x = (p.getX() - realX) * screenW / realW;
        double y = (realY - p.getY()) * screenH / realH;
        return new ScreenPoint((int) x, (int) y);
    }

    public RealPoint s2r(ScreenPoint p) {
        double x = p.getX() * realW / screenW + realX;
        double y = realY - p.getY() * realH / screenH;
        return new RealPoint(x, y);
    }

    public void setRealX(double realX) {
        this.realX = realX;
    }

    public void setRealY(double realY) {
        this.realY = realY;
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

    public double getRealX() {
        return realX;
    }

    public double getRealY() {
        return realY;
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
