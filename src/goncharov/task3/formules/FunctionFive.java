package goncharov.task3.formules;

import goncharov.task3.IFunction;

public class FunctionFive implements IFunction {

    @Override
    public double getValue(double x) {
        return 1 / (Math.pow(x, 2) + 1);
    }
}
