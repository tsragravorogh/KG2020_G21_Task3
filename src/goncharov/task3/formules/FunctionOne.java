package goncharov.task3.formules;

import goncharov.task3.IFunction;

public class FunctionOne implements IFunction {
    @Override
    public double getValue(double x) {
        return (Math.exp(Math.sin(x * 30)));
    }
}
