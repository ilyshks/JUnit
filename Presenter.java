package org.example;

public record Presenter(MyCalculator calculator, View view) implements ICalculatorPresenter {

    public boolean checkValues(String first, String second){
        if (first.isEmpty() || second.isEmpty()){
            view.displayError("заполните все поля!");
            return false;
        }
        try {
            double a = Double.parseDouble(first);
            double b = Double.parseDouble(second);
            return true;
        } catch (NumberFormatException exception){
            view.displayError("некорректный формат ввода!");
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
        if (checkValues(first, second)){
            try {
                double result = calculator.divide(Double.parseDouble(first), Double.parseDouble(second));
                view.printResult(result);
            } catch (ArithmeticException exception) {
                view.displayError("ошибка деления на ноль!");
            }
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
