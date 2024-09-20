package org.example;

public class View implements ICalculatorView{
    private String first;
    private String second;

    public View(String a, String b) {
        first = a;
        second = b;
    }

    @Override
    public void printResult(double result) {
        System.out.println("Result: " + result);
    }

    @Override
    public void displayError(String message) {
        System.out.println("Error message: " + message);
    }

    @Override
    public String getFirstArgumentAsString() {
        return first;
    }

    @Override
    public String getSecondArgumentAsString() {
        return second;
    }
}
