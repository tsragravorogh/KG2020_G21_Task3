package ru.vsu.cs;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {
    private ru.vsu.cs.DrawPanel dp;

    public MainWindow() throws HeadlessException {
        dp = new ru.vsu.cs.DrawPanel();
        add(dp);
    }
}
