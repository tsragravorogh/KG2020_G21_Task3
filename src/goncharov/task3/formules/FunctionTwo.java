package goncharov.task3.formules;

import goncharov.task3.IFunction;

import java.util.ArrayList;
import java.util.List;

public class FunctionTwo implements IFunction {
    @Override
    public boolean isGap(double x) {
        return false;
    }

    @Override
    public ArrayList<ArrayList<Double>> getRange(double xLeft, double xRight) {
        ArrayList<ArrayList<Double>> rangesList = new ArrayList<>();
        ArrayList<Double> list = new ArrayList<>();
        for(double i = xLeft; i < xRight; i += (xRight - xLeft) / 500) {
            list.add(i);
        }
        rangesList.add(list);
        return rangesList;
    }

    @Override
    public double getValue(double x) {
        return Math.pow(x, 3) - Math.pow(x, 2);
    }

    @Override
    public int getVar() {
        return 0;
    }
}
