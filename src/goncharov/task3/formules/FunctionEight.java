package goncharov.task3.formules;

import goncharov.task3.IFunction;

import java.util.ArrayList;
import java.util.List;

public class FunctionEight  implements IFunction {

    @Override
    public ArrayList<ArrayList<Double>> getRange(double xLeft, double xRight) {
        ArrayList<Double> list = new ArrayList<>();
        for(double i = xLeft; i < xRight; i += (xRight - xLeft) / 500) {
            list.add(i);
        }
        ArrayList<ArrayList<Double>> lists = new ArrayList<>();
        lists.add(list);
         return lists;
    }

    @Override
    public double getValue(double x) {
        return Math.pow(Math.exp(1.0), ((Math.sin(x) + Math.cos(x)) / (1 + Math.pow(x, 2))));
    }

    @Override
    public int getVar() {
        return 0;
    }
}
