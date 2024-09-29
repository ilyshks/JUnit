package org.example;

public record Presenter(MyCalculator calculator, View view) implements ICalculatorPresenter {

    public boolean checkValues(String first, String second){
        try {
            double a = Double.parseDouble(first);
            double b = Double.parseDouble(second);
            return true;
        }catch (NullPointerException | NumberFormatException exception){
            view.displayError(exception.getMessage());
            return false;
        }
    }

    @Override
    public void onPlusClicked() {
        String first = view.getFirstArgumentAsString();
        String second = view.getSecondArgumentAsString();
        if (checkValues(first, second)){
            double result = calculator.sum(Double.parseDouble(first), Double.parseDouble(second));
            view.printResult(result);
        }
    }

    @Override
    public void onMinusClicked() {
        String first = view.getFirstArgumentAsString();
        String second = view.getSecondArgumentAsString();
        if (checkValues(first, second)){
            double result = calculator.subtract(Double.parseDouble(first), Double.parseDouble(second));
            view.printResult(result);
        }
    }

    @Override
    public void onDivideClicked() {
        String first = view.getFirstArgumentAsString();
        String second = view.getSecondArgumentAsString();
        try {
            double result = calculator.divide(Double.parseDouble(first), Double.parseDouble(second));
            view.printResult(result);
        } catch (NullPointerException | NumberFormatException | ArithmeticException exception) {
            view.displayError(exception.getMessage());
        }
    }

    @Override
    public void onMultiplyClicked() {
        String first = view.getFirstArgumentAsString();
        String second = view.getSecondArgumentAsString();
        if (checkValues(first, second)){
            double result = calculator.multiply(Double.parseDouble(first), Double.parseDouble(second));
            view.printResult(result);
        }
    }
}
