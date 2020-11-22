package goncharov.task3;

import java.awt.*;
import java.util.ArrayList;

public interface IFunction {
     /*Кроме x может потребоваться передача и других параметров, от которых зависит построение функции.
     Надо подумать и принять обоснованное решение о том, какие ещё параметры передавать на данном этапе*/

    double getValue(double x);

}
