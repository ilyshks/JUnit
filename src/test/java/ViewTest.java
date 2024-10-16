import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import javax.swing.*;
import java.awt.event.ActionEvent;
import static org.mockito.Mockito.*;

import org.example.View;
import org.example.Presenter;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class ViewTest {

    private View view;
    private Presenter presenter;
    private JLabel resultLabel;
    private JTextField inputField1;
    private JTextField inputField2;

    @BeforeEach
    public void setUp() {
        view = new View();
        presenter = mock(Presenter.class);
        view.setPresenter(presenter);

        // Мокаем resultLabel, inputField1 и inputField2
        view.setInputField1(mock(JTextField.class));
        view.setInputField2(mock(JTextField.class));
        view.setResultLabel(mock(JLabel.class));

        inputField1 = view.getInputField1();
        inputField2 = view.getInputField2();
        resultLabel = view.getResultLabel();
    }

    @ParameterizedTest
    @CsvSource({ "10,20,30.0", "16.0,15.2,31.2", "-20,14,-6.0", "-100,330,230.0" })
    @DisplayName("Test GUI PlusButtonClicked")
    public void testOnPlusButtonClicked(String input1, String input2, String expected) {
        // Arrange
        when(inputField1.getText()).thenReturn(input1);
        when(inputField2.getText()).thenReturn(input2);

        // Устанавливаем ожидаемое поведение для метода
        doAnswer(invocation -> {
            view.printResult(Double.parseDouble(expected));
            return null;
        }).when(presenter).onPlusClicked();

        JButton addButton = view.getAddButton();
        ActionEvent event = new ActionEvent(addButton, ActionEvent.ACTION_PERFORMED, null);

        // Act
        addButton.getActionListeners()[0].actionPerformed(event);

        // Assert
        verify(presenter).onPlusClicked();
        verify(resultLabel).setText("Результат: " + expected);
    }

    @ParameterizedTest
    @CsvSource({ "a,20,некорректный формат ввода!", ",,заполните все поля!",
            "ab,cc,некорректный формат ввода!", "1,bbb,некорректный формат ввода!" })
    @DisplayName("Test GUI PlusButtonClickedError")
    public void testOnPlusButtonClickedError(String input1, String input2, String expected) {
        // Arrange
        when(inputField1.getText()).thenReturn(input1);
        when(inputField2.getText()).thenReturn(input2);

        // Устанавливаем ожидаемое поведение для метода
        doAnswer(invocation -> {
            view.displayError(expected);
            return null;
        }).when(presenter).onPlusClicked();

        JButton addButton = view.getAddButton();
        ActionEvent event = new ActionEvent(addButton, ActionEvent.ACTION_PERFORMED, null);

        // Act
        addButton.getActionListeners()[0].actionPerformed(event);

        // Assert
        verify(presenter).onPlusClicked();
        verify(resultLabel).setText("Результат: " + expected);
    }

    @ParameterizedTest
    @CsvSource({ "10,20,-10.0", "16.0,15.2,0.8", "-20,14,-34.0", "10,-33,43.0" })
    @DisplayName("Test GUI MinusButtonClicked")
    public void testOnMinusButtonClicked(String input1, String input2, String expected) {
        // Arrange
        when(inputField1.getText()).thenReturn(input1);
        when(inputField2.getText()).thenReturn(input2);

        // Устанавливаем ожидаемое поведение для метода
        doAnswer(invocation -> {
            view.printResult(Double.parseDouble(expected));
            return null;
        }).when(presenter).onMinusClicked();

        JButton subtractButton = view.getSubtractButton();
        ActionEvent event = new ActionEvent(subtractButton, ActionEvent.ACTION_PERFORMED, null);

        // Act
        subtractButton.getActionListeners()[0].actionPerformed(event);

        // Assert
        verify(presenter).onMinusClicked();
        verify(resultLabel).setText("Результат: " + expected);
    }

    @ParameterizedTest
    @CsvSource({ "a,20,некорректный формат ввода!", ",,заполните все поля!",
            "ab,cc,некорректный формат ввода!", "1,bbb,некорректный формат ввода!" })
    @DisplayName("Test GUI MinusButtonClickedError")
    public void testOnMinusButtonClickedError(String input1, String input2, String expected) {
        // Arrange
        when(inputField1.getText()).thenReturn(input1);
        when(inputField2.getText()).thenReturn(input2);

        // Устанавливаем ожидаемое поведение для метода
        doAnswer(invocation -> {
            view.displayError(expected);
            return null;
        }).when(presenter).onMinusClicked();

        JButton subtractButton = view.getSubtractButton();
        ActionEvent event = new ActionEvent(subtractButton, ActionEvent.ACTION_PERFORMED, null);

        // Act
        subtractButton.getActionListeners()[0].actionPerformed(event);

        // Assert
        verify(presenter).onMinusClicked();
        verify(resultLabel).setText("Результат: " + expected);
    }

    @ParameterizedTest
    @CsvSource({ "10,20,200.0", "1.0,15.2,15.2", "-20,14,-280.0", "1000,0,0.0" })
    @DisplayName("Test GUI MultiplyButtonClicked")
    public void testOnMultiplyButtonClicked(String input1, String input2, String expected) {
        // Arrange
        when(inputField1.getText()).thenReturn(input1);
        when(inputField2.getText()).thenReturn(input2);

        // Устанавливаем ожидаемое поведение для метода
        doAnswer(invocation -> {
            view.printResult(Double.parseDouble(expected));
            return null;
        }).when(presenter).onMultiplyClicked();

        JButton multiplyButton = view.getMultiplyButton();
        ActionEvent event = new ActionEvent(multiplyButton, ActionEvent.ACTION_PERFORMED, null);

        // Act
        multiplyButton.getActionListeners()[0].actionPerformed(event);

        // Assert
        verify(presenter).onMultiplyClicked();
        verify(resultLabel).setText("Результат: " + expected);
    }

    @ParameterizedTest
    @CsvSource({ "a,20,некорректный формат ввода!", ",,заполните все поля!",
            "ab,cc,некорректный формат ввода!", "1,bbb,некорректный формат ввода!" })
    @DisplayName("Test GUI MultiplyButtonClickedError")
    public void testOnMultiplyButtonClickedError(String input1, String input2, String expected) {
        // Arrange
        when(inputField1.getText()).thenReturn(input1);
        when(inputField2.getText()).thenReturn(input2);

        // Устанавливаем ожидаемое поведение для метода
        doAnswer(invocation -> {
            view.displayError(expected);
            return null;
        }).when(presenter).onMultiplyClicked();

        JButton multiplyButton = view.getMultiplyButton();
        ActionEvent event = new ActionEvent(multiplyButton, ActionEvent.ACTION_PERFORMED, null);

        // Act
        multiplyButton.getActionListeners()[0].actionPerformed(event);

        // Assert
        verify(presenter).onMultiplyClicked();
        verify(resultLabel).setText("Результат: " + expected);
    }

    @ParameterizedTest
    @CsvSource({ "10,20,0.5", "1.0,16,0.0625", "-28,14,-2.0", "1000,1000,1.0" })
    @DisplayName("Test GUI DivideButtonClicked")
    public void testOnDivideButtonClicked(String input1, String input2, String expected) {
        // Arrange
        when(inputField1.getText()).thenReturn(input1);
        when(inputField2.getText()).thenReturn(input2);

        // Устанавливаем ожидаемое поведение для метода
        doAnswer(invocation -> {
            view.printResult(Double.parseDouble(expected));
            return null;
        }).when(presenter).onDivideClicked();

        JButton divideButton = view.getDivideButton();
        ActionEvent event = new ActionEvent(divideButton, ActionEvent.ACTION_PERFORMED, null);

        // Act
        divideButton.getActionListeners()[0].actionPerformed(event);

        // Assert
        verify(presenter).onDivideClicked();
        verify(resultLabel).setText("Результат: " + expected);
    }

    @ParameterizedTest
    @CsvSource({ "a,20,некорректный формат ввода!", ",,заполните все поля!",
            "ab,cc,некорректный формат ввода!", "1,bbb,некорректный формат ввода!" })
    @DisplayName("Test GUI DivideButtonClickedError")
    public void testOnDivideButtonClickedError(String input1, String input2, String expected) {
        // Arrange
        when(inputField1.getText()).thenReturn(input1);
        when(inputField2.getText()).thenReturn(input2);

        // Устанавливаем ожидаемое поведение для метода
        doAnswer(invocation -> {
            view.displayError(expected);
            return null;
        }).when(presenter).onDivideClicked();

        JButton divideButton = view.getDivideButton();
        ActionEvent event = new ActionEvent(divideButton, ActionEvent.ACTION_PERFORMED, null);

        // Act
        divideButton.getActionListeners()[0].actionPerformed(event);

        // Assert
        verify(presenter).onDivideClicked();
        verify(resultLabel).setText("Результат: " + expected);
    }

    @ParameterizedTest
    @CsvSource({ "10,0.000000006,ошибка деления на ноль!", "10e-8, 10e-9, ошибка деления на ноль!",
            "10e-10,0.000000001,ошибка деления на ноль!", "10e7,0.000000009999,ошибка деления на ноль!" })
    @DisplayName("Test GUI DivideButtonClickedZeroError")
    public void testOnDivideButtonClickedZeroError(String input1, String input2, String expected) {
        // Arrange
        when(inputField1.getText()).thenReturn(input1);
        when(inputField2.getText()).thenReturn(input2);

        // Устанавливаем ожидаемое поведение для метода
        doAnswer(invocation -> {
            view.displayError(expected);
            return null;
        }).when(presenter).onDivideClicked();

        JButton divideButton = view.getDivideButton();
        ActionEvent event = new ActionEvent(divideButton, ActionEvent.ACTION_PERFORMED, null);

        // Act
        divideButton.getActionListeners()[0].actionPerformed(event);

        // Assert
        verify(presenter).onDivideClicked();
        verify(resultLabel).setText("Результат: " + expected);
    }

    @Test
    public void testPrintResult() {
        // Arrange
        double num = 42.0;

        // Act
        view.printResult(num);

        // Assert
        verify(resultLabel).setText("Результат: " + num);
    }

    @Test
    public void testDisplayError() {
        // Arrange
        String message = "Ошибка";

        // Act
        view.displayError(message);

        // Assert
        verify(resultLabel).setText("Результат: " + message);
    }

    @Test
    public void testGetFirstArgumentAsString() {
        // Arrange
        String expected = "10";
        when(inputField1.getText()).thenReturn(expected);

        // Act
        String actual = view.getFirstArgumentAsString();

        // Assert
        verify(inputField1).getText();
        assert actual.equals(expected);
    }

    @Test
    public void testGetSecondArgumentAsString() {
        // Arrange
        String expected = "20";
        when(inputField2.getText()).thenReturn(expected);

        // Act
        String actual = view.getSecondArgumentAsString();

        // Assert
        verify(inputField2).getText();
        assert actual.equals(expected);
    }

}