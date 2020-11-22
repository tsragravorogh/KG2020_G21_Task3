package goncharov.task3.formules;

import goncharov.task3.IFunction;

public class FunctionSix implements IFunction {

    @Override
    public double getValue(double x) {
        return Math.abs(Math.pow(x, 4) - Math.pow(x, 3) - Math.pow(x, 2) - x);
    }
}
