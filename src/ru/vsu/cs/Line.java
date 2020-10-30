package ru.vsu.cs;

public class Line {
    private RealPoint p1, p2;

    public Line(RealPoint p1, RealPoint p2) {
        this.p1 = p1;
        this.p2 = p2;
    }

    public Line(double x1, double y1, double x2, double y2){
        this.p1 = new RealPoint(x1, y1);
        this.p2 = new RealPoint(x2, y2);
    }

    public void setP1(RealPoint p1) {
        this.p1 = p1;
    }

    public void setP2(RealPoint p2) {
        this.p2 = p2;
    }

    public RealPoint getP1() {
        return p1;
    }

    public RealPoint getP2() {
        return p2;
    }
}
