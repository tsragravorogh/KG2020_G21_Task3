package goncharov.task3.formules;

import goncharov.task3.IFunction;

public class FunctionEight  implements IFunction {

    @Override
    public double getValue(double x) {
        return Math.pow(Math.exp(1.0), ((Math.sin(x) + Math.cos(x)) / (1 + Math.pow(x, 2))));
    }
}
