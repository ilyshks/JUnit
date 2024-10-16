package org.example;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;

import javax.swing.*;

public class CalculatorSteps {

    private CalculatorContext.MyCalculator calculator = new CalculatorContext.MyCalculator();
    private CalculatorContext.Presenter presenter;
    private CalculatorContext.View view;

    @Given("the first number is {double}")
    public void theFirstNumberIs(double firstNumber) {
        view = new CalculatorContext.View();
        view.setInputField1(new JTextField(String.valueOf(firstNumber)));
    }

    @Given("the second number is {double}")
    public void theSecondNumberIs(double secondNumber) {
        view.setInputField2(new JTextField(String.valueOf(secondNumber)));
    }

    @When("the user clicks the plus button")
    public void theUserClicksThePlusButton() {
        presenter = new CalculatorContext.Presenter(calculator, view);
        presenter.onPlusClicked();
    }

    @When("the user clicks the minus button")
    public void theUserClicksTheMinusButton() {
        presenter = new CalculatorContext.Presenter(calculator, view);
        presenter.onMinusClicked();
    }

    @When("the user clicks the multiply button")
    public void theUserClicksTheMultiplyButton() {
        presenter = new CalculatorContext.Presenter(calculator, view);
        presenter.onMultiplyClicked();
    }

    @When("the user clicks the divide button")
    public void theUserClicksTheDivideButton() {
        presenter = new CalculatorContext.Presenter(calculator, view);
        presenter.onDivideClicked();
    }

    @Then("the result should be {double}")
    public void theResultShouldBe(double expectedResult) {
        Assertions.assertEquals("Результат: " + expectedResult, view.getResultLabel().getText());
    }

    @Then("the result should be {string}")
    public void theResultShouldBe(String expectedResult) {
        Assertions.assertEquals("Результат: " + expectedResult, view.getResultLabel().getText());
    }

}