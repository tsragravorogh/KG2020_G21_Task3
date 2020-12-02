package goncharov.task3.formules;

import goncharov.task3.IFunction;

import java.util.ArrayList;
import java.util.List;

public class FunctionTen implements IFunction {

    @Override
    public ArrayList<ArrayList<Double>> getRange(double xLeft, double xRight) {
        ArrayList<ArrayList<Double>> rangesList = new ArrayList<>();
        ArrayList<Double> list = new ArrayList<>();
        double left = xLeft; double right;
        for(double i = xLeft + (xRight - xLeft) / 500; i < xRight; i += (xRight - xLeft) / 500) {
            right = i;
            if (getValue(right) > getValue(left)) {
                list.add(i);
            }
            else {
                rangesList.add(new ArrayList<>(list));
                list.clear();
            }

            left = i;
        }
        return rangesList;
    }

    @Override
    public double getValue(double x) {
        return Math.tan(x);
    }

    @Override
    public int getVar() {
        return 10;
    }
}
