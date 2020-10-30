package ru.vsu.cs;

import ru.vsu.cs.Formula;

import java.util.*;

public class MainTwo {

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);

        do {
            Formula f = new Formula();
            try {
                System.out.println("Input formula: ");
                f.prepare(scn.nextLine().replace(',', '.'));

                double v = f.execute(10., 20., 30.);
                System.out.println("Answer: " + v + '\n');
            } catch (Exception e) {
                System.out.println("You've made a mistake in input!"+'\n');
            }
        } while(true);

    }
}
