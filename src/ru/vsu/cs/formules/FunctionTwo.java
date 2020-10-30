package ru.vsu.cs.formules;

import ru.vsu.cs.IFunction;
import ru.vsu.cs.RealPoint;

import java.util.ArrayList;

public class FunctionTwo implements IFunction {


    @Override
    public ArrayList<RealPoint> compute(double first, double second) {
        ArrayList<RealPoint> points  = new ArrayList<>();
        for(double i = first; i < second; i += 0.0001) {
            points.add(new RealPoint(i, getValue(i)));
        }
        return points;
    }

    private double getValue(double x) {
        return Math.pow(x, 3) - Math.pow(x, 2);
    }
}
