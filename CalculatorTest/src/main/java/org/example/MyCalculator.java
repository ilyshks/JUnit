package org.example;

import java.lang.Math;
import java.lang.ArithmeticException;

public class MyCalculator implements ICalculator{

    @Override
    public double sum(double a, double b) {
        return a + b;
    }

    @Override
    public double subtract(double a, double b) {
        return a - b;
    }

    @Override
    public double multiply(double a, double b) {
        return a * b;
    }

    @Override
    public double divide(double a, double b) throws ArithmeticException{
        if (Math.abs(b) < 10e-8) throw new ArithmeticException("Zero division error");
        return a / b;
    }
}
