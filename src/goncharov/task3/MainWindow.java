package goncharov.task3;

import goncharov.task3.formules.*;


import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

public class MainWindow extends JFrame implements MouseMotionListener {
    private DrawPanel dp;
    JTextField textArea = new JTextField();
    //JTextArea textArea = new JTextArea();
    ScreenConverter sc = new ScreenConverter(-6, 6, 12, 12, 1200, 800);

    public MainWindow() {
        dp = new DrawPanel();
        this.add(dp);
        JMenuBar jMenuBar = new JMenuBar();
        JMenu functionsMenu = new JMenu("Draw Function ↓");
        JMenuItem functionOne = new JMenuItem("y = e^sin(x*3)");
        JMenuItem functionTwo = new JMenuItem("y = x^3 - x^2");
        JMenuItem functionThree = new JMenuItem("y = x^(1/3) * sin(x)");
        JMenuItem functionFour = new JMenuItem("y = ln(x^2 + 1) / (x^2 + 2)");
        JMenuItem functionFive = new JMenuItem("y = (1 / (x^2 + 1))");
        JMenuItem functionSix = new JMenuItem("y = abs(x^4 - x^3 + x^2 - x)");
        JMenuItem functionSeven = new JMenuItem("y = x^4 - abs(x^3)");
        JMenuItem functionEight = new JMenuItem(" y = e^((sin(x) + cos(x)) / (x^2 + 1))");
        JMenuItem functionNine = new JMenuItem("y = 1 / (x + 1.5) - 2.5");
        JMenuItem functionTen = new JMenuItem("y = tg(x)");

        functionsMenu.add(functionOne);
        functionsMenu.add(functionTwo);
        functionsMenu.add(functionThree);
        functionsMenu.add(functionFour);
        functionsMenu.add(functionFive);
        functionsMenu.add(functionSix);
        functionsMenu.add(functionSeven);
        functionsMenu.add(functionEight);
        functionsMenu.add(functionNine);
        functionsMenu.add(functionTen);

        jMenuBar.add(functionsMenu);
        jMenuBar.add(textArea);
        setJMenuBar(jMenuBar);

        functionOne.addActionListener(actionEvent -> {
            dp.setFunction(new FunctionOne());
            dp.repaint();
        });

        functionTwo.addActionListener(actionEvent -> {
            dp.setFunction(new FunctionTwo());
            dp.repaint();
        });

        functionThree.addActionListener(actionEvent -> {
            dp.setFunction(new FunctionThree());
            dp.repaint();
        });

        functionFour.addActionListener(actionEvent -> {
            dp.setFunction(new FunctionFour());
            dp.repaint();
        });

        functionFive.addActionListener(actionEvent -> {
            dp.setFunction(new FunctionFive());
            dp.repaint();
        });

        functionSix.addActionListener(actionEvent -> {
            dp.setFunction(new FunctionSix());
            dp.repaint();
        });

        functionSeven.addActionListener(actionEvent -> {
            dp.setFunction(new FunctionSeven());
            dp.repaint();
        });

        functionEight.addActionListener(actionEvent -> {
            dp.setFunction(new FunctionEight());
            dp.repaint();
        });

        functionNine.addActionListener(actionEvent -> {
            dp.setFunction(new FunctionNine());
            dp.repaint();
        });

        functionTen.addActionListener(actionEvent -> {
            dp.setFunction(new FunctionTen());
            dp.repaint();
        });
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        System.out.println(e.getX()+ ", "+ getY());
        //System.out.println(dp.getMouseX());
        textArea.setText(String.valueOf(e.getX()) + ", "+ String.valueOf(e.getY()));
    }
}
