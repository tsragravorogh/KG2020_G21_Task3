package goncharov.task3.formules;

import goncharov.task3.IFunction;

import java.util.ArrayList;
import java.util.List;

public class FunctionNine implements IFunction {



    @Override
    public ArrayList<ArrayList<Double>> getRange(double xLeft, double xRight) {
        ArrayList<ArrayList<Double>> rangesList = new ArrayList<>();
        ArrayList<Double> list1 = new ArrayList<>();
        ArrayList<Double> list2 = new ArrayList<>();
        for(double i = xLeft; i < xRight; i += (xRight - xLeft) / 500) {
            if (i < -1.5) list1.add(i);
            if (i > -1.5) list2.add(i);
        }
        rangesList.add(list1);
        rangesList.add(list2);
        return rangesList;
    }

    @Override
    public double getValue(double x) {
        return 1 / (x + 1.5) - 2.5;
    }

    @Override
    public int getVar() {
        return 9;
    }
}
