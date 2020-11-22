package goncharov.task3.formules;

import goncharov.task3.*;

import java.awt.*;
import java.util.ArrayList;

public class FunctionService {

    public static void drawFunction(LineDrawer ld, ScreenConverter sc, IFunction f) throws Exception {
        ld.setColor(new Color(88, 203, 66));
        Double first = sc.getRealX();
        Double last = sc.getRealX() + sc.getRealW();
        Double step = (last - first) / 500;
        ArrayList<RealPoint> points = new ArrayList<>();
        for(double i = first; i <= last; i += step) {
            if(Math.abs(f.getValue(i)) != Double.POSITIVE_INFINITY)
                points.add(new RealPoint(i, f.getValue(i)));
        }
        for(int i = 0; i < points.size() - 1; i++) {
            ScreenPoint left = new ScreenPoint(sc.r2s(points.get(i)).getX(), sc.r2s(points.get(i)).getY());
            ScreenPoint right = new ScreenPoint(sc.r2s(points.get(i + 1)).getX(), sc.r2s(points.get(i + 1)).getY());
            if(sc.s2r(left).getY() > sc.getRealY() - 2 * sc.getRealH() &&
                    sc.s2r(left).getY() < sc.getRealY() + sc.getRealH() &&
                    sc.s2r(right).getY() > sc.getRealY() - 2 * sc.getRealH() &&
                    sc.s2r(right).getY() < sc.getRealY() + sc.getRealH()) {
                ld.drawLine(left, right, Color.black);
            }
        }
    }
}
