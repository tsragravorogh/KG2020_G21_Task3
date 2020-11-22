package goncharov.task3.formules;

import goncharov.task3.IFunction;

public class FunctionThree implements IFunction {

    @Override
    public double getValue(double x) {
        return Math.pow(x, 1/3) * Math.sin(x);
    }
}
