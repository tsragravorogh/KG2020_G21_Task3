package goncharov.task3.formules;

import goncharov.task3.IFunction;

import java.util.ArrayList;
import java.util.List;

public class FunctionThree implements IFunction {

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
        return Math.pow(x, 1/3) * Math.sin(x);
    }

    @Override
    public int getVar() {
        return 0;
    }
}
