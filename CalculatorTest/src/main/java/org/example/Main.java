package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter arithmetic expression (for example, 1 + 1):");

        String[] arguments = scanner.nextLine().split(" ");
        double first = Double.parseDouble(arguments[0]);
        double second = Double.parseDouble(arguments[2]);
        String operation = arguments[1];

        Data data = new Data(first, second, operation);
        View view = new View(arguments[0], arguments[2]);
        MyCalculator calculator = new MyCalculator();
        Controller controller = new Controller(data, view, calculator);
        switch (operation) {
            case "+" -> controller.onPlusClicked();
            case "-" -> controller.onMinusClicked();
            case "*" -> controller.onMultiplyClicked();
            case "/" -> controller.onDivideClicked();
        }
    }
}