package goncharov.task3.formules;

import goncharov.task3.IFunction;

public class FunctionFour implements IFunction {
    @Override
    public double getValue(double x) {
        return Math.log(Math.pow(x, 2) + 1) / (Math.pow(x, 2) + 2);
    }
}
