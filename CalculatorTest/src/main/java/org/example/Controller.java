package org.example;

public class Controller implements ICalculatorPresenter{
    private Data data;
    private View view;
    private MyCalculator calculator;

    public Controller(Data data, View view, MyCalculator calculator) {
        this.data = data;
        this.view = view;
        this.calculator = calculator;
    }

    public Data getData() {
        return data;
    }

    public View getView() {
        return view;
    }

    @Override
    public void onPlusClicked() {
        view.printResult(calculator.sum(data.getFirstArgument(), data.getSecondArgument()));
    }

    @Override
    public void onMinusClicked() {
        view.printResult(calculator.subtract(data.getFirstArgument(), data.getSecondArgument()));
    }

    @Override
    public void onDivideClicked() {
        try {
            view.printResult(calculator.divide(data.getFirstArgument(), data.getSecondArgument()));
        }catch (ArithmeticException exception){
            view.displayError(exception.getMessage());
        }

    }

    @Override
    public void onMultiplyClicked() {
        view.printResult(calculator.multiply(data.getFirstArgument(), data.getSecondArgument()));
    }
}
