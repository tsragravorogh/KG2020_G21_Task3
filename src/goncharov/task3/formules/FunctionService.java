package goncharov.task3.formules;

import goncharov.task3.*;

import java.awt.*;
import java.util.ArrayList;

public class FunctionService {

    public static void drawFunction(LineDrawer ld, ScreenConverter sc, IFunction f) throws Exception {
        ld.setColor(new Color(88, 203, 66));
        Double first = sc.getRealX();
        Double last = sc.getRealX() + sc.getRealW();

        ArrayList<ArrayList<RealPoint>> listPoints = new ArrayList<>();

        ArrayList<ArrayList<Double>> listRanges = f.getRange(first, last);

        for(int i = 0; i < listRanges.size(); i++) {
            ArrayList<RealPoint> points = new ArrayList<>();
            //listRanges.get(i).add()
            for(int j = 0; j < listRanges.get(i).size(); j++) {
                points.add(new RealPoint(listRanges.get(i).get(j), f.getValue(listRanges.get(i).get(j))));
            }
            listPoints.add(points);
        }

/*
добавить точку(нижний у видимого экрана) в начале каждого отрезка
и в конце отрезка(верхняя видимого экрана)
 */

        for (int i = 0; i < listRanges.size(); i++) {
            for(int j = 0; j < listRanges.get(i).size() - 1; j++) {
                if(j == 0 && f.getVar() == 10) {
                    ScreenPoint left = new ScreenPoint(sc.r2s(listPoints.get(i).get(j)).getX(), sc.getScreenH());
                    ScreenPoint right = new ScreenPoint(sc.r2s(listPoints.get(i).get(j)).getX(), sc.r2s(listPoints.get(i).get(j)).getY());

                    ld.drawLine(left, right, Color.black);
                }
                if(j == 0 && f.getVar() == 9 && i == 0) {
                    ScreenPoint left = new ScreenPoint(0, sc.r2s(listPoints.get(i).get(j)).getY());
                    ScreenPoint right = new ScreenPoint(sc.r2s(listPoints.get(i).get(j)).getX(), sc.r2s(listPoints.get(i).get(j)).getY());
                    ld.drawLine(left, right, Color.black);
                }
                if(j == 0 && f.getVar() == 9 && i == 1) {
                    ScreenPoint left = new ScreenPoint(sc.r2s(listPoints.get(i).get(j)).getX(), 0);
                    ScreenPoint right = new ScreenPoint(sc.r2s(listPoints.get(i).get(j)).getX(), sc.r2s(listPoints.get(i).get(j)).getY());
                    ld.drawLine(left, right, Color.black);
                }

                ScreenPoint left = new ScreenPoint(sc.r2s(listPoints.get(i).get(j)).getX(), sc.r2s(listPoints.get(i).get(j)).getY());
                ScreenPoint right = new ScreenPoint(sc.r2s(listPoints.get(i).get(j + 1)).getX(), sc.r2s(listPoints.get(i).get(j + 1)).getY());

                ld.drawLine(left, right, Color.black);
                if(j == listRanges.get(i).size() - 2 && f.getVar() == 10) {
                    left = right;
                    right = new ScreenPoint(sc.r2s(listPoints.get(i).get(j + 1)).getX(), (int)(sc.getRealY() - sc.getScreenH()));
                    ld.drawLine(left, right, Color.black);
                }
                if(j == listRanges.get(i).size() - 2 && f.getVar() == 9 && i == 0) {
                    left = right;
                    right = new ScreenPoint(sc.r2s(listPoints.get(i).get(j + 1)).getX(), sc.getScreenH());
                    ld.drawLine(left, right, Color.black);
                }
                if (j == listRanges.get(i).size() - 2 && f.getVar() == 9 && i == 1) {
                    left = right;
                    right = new ScreenPoint((int) (sc.getScreenW()+ sc.getRealX()), right.getY());
                    ld.drawLine(left, right, Color.black);
                }
            }
        }

//        for(int i = 0; i < points.size() - 1; i++) {
//            ScreenPoint left = new ScreenPoint(sc.r2s(points.get(i)).getX(), sc.r2s(points.get(i)).getY());
//            ScreenPoint right = new ScreenPoint(sc.r2s(points.get(i + 1)).getX(), sc.r2s(points.get(i + 1)).getY());
//            if(sc.s2r(left).getY() > sc.getRealY() - 2 * sc.getRealH() &&
//                    sc.s2r(left).getY() < sc.getRealY() + sc.getRealH() &&
//                    sc.s2r(right).getY() > sc.getRealY() - 2 * sc.getRealH() &&
//                    sc.s2r(right).getY() < sc.getRealY() + sc.getRealH()) {
//
//                ld.drawLine(left, right, Color.black);
//            }
//        }
    }
}
