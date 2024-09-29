package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter arithmetic expression (for example, 1 + 1):");

        String[] arguments = scanner.nextLine().split(" ");
        String operation = arguments[1];

        View view = new View(arguments[0], arguments[2]);
        MyCalculator calculator = new MyCalculator();
        Presenter presenter = new Presenter(calculator, view);

        switch (operation) {
            case "+" -> presenter.onPlusClicked();
            case "-" -> presenter.onMinusClicked();
            case "*" -> presenter.onMultiplyClicked();
            case "/" -> presenter.onDivideClicked();
        }
    }
}