package goncharov.task3.formules;

import goncharov.task3.IFunction;

public class FunctionSeven implements IFunction {

    @Override
    public double getValue(double x) {
        return Math.pow(x, 4) - Math.abs(Math.pow(x, 3));
    }
}
