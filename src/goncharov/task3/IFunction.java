package goncharov.task3;


import java.util.ArrayList;
import java.util.List;

public interface IFunction {
    boolean isGap(double x);
     /*Кроме x может потребоваться передача и других параметров, от которых зависит построение функции.
     Надо подумать и принять обоснованное решение о том, какие ещё параметры передавать на данном этапе*/
     ArrayList<ArrayList<Double>> getRange(double xLeft, double xRight);
    double getValue(double x);
    int getVar();
}
