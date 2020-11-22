package goncharov.task3;

import java.awt.*;

public interface LineDrawer {
    void drawLine (ScreenPoint p1, ScreenPoint p2, Color color);
    void setColor(Color c);

}
