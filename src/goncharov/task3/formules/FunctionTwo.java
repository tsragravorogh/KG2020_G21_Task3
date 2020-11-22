package goncharov.task3.formules;

import goncharov.task3.IFunction;

public class FunctionTwo implements IFunction {
    @Override
    public double getValue(double x) {
        return Math.pow(x, 3) - Math.pow(x, 2);
    }
}
