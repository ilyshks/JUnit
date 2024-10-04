package org.example;

import javax.swing.*;

public class View implements ICalculatorView{
    private final JTextField first;
    private final JTextField second;
    private final JLabel result;

    public View(JTextField first, JTextField second, JLabel result) {
        this.first = first;
        this.second = second;
        this.result = result;
    }

    @Override
    public void printResult(double num) {
        result.setText("Результат: " + num);
    }

    @Override
    public void displayError(String message) {
        result.setText("Результат: " + message);
    }

    @Override
    public String getFirstArgumentAsString() {
        return first.getText();
    }

    @Override
    public String getSecondArgumentAsString() {
        return second.getText();
    }
}
