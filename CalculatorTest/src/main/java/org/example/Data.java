package org.example;

public class Data {
    private double firstArgument;
    private double secondArgument;
    private String operation;

    public Data(double firstArgument, double secondArgument, String operation) {
        this.firstArgument = firstArgument;
        this.secondArgument = secondArgument;
        this.operation = operation;
    }

    public double getFirstArgument() {
        return firstArgument;
    }

    public double getSecondArgument() {
        return secondArgument;
    }

    public String getOperation() {
        return operation;
    }
}
