import org.example.MyCalculator;
import org.example.Presenter;
import org.example.View;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class PresenterTest {

    private MyCalculator calculator;
    private View view;
    private Presenter presenter;

    @BeforeEach
    public void setUp() {
        calculator = Mockito.mock(MyCalculator.class);
        view = Mockito.mock(View.class);
        presenter = new Presenter(calculator, view);
    }

    @Test
    public void testCheckValues_BothFieldsEmpty() {
        // Arrange
        String first = "";
        String second = "";

        // Act
        boolean result = presenter.checkValues(first, second);

        // Assert
        assertFalse(result);
        verify(view).displayError("заполните все поля!");
    }

    @Test
    public void testCheckValues_OneFieldEmpty() {
        // Arrange
        String first = "10";
        String second = "";

        // Act
        boolean result = presenter.checkValues(first, second);

        // Assert
        assertFalse(result);
        verify(view).displayError("заполните все поля!");
    }

    @Test
    public void testCheckValues_InvalidNumberFormat() {
        // Arrange
        String first = "10";
        String second = "abc";

        // Act
        boolean result = presenter.checkValues(first, second);

        // Assert
        assertFalse(result);
        verify(view).displayError("некорректный формат ввода!");
    }

    @Test
    public void testCheckValues_ValidNumbers() {
        // Arrange
        String first = "10";
        String second = "20";

        // Act
        boolean result = presenter.checkValues(first, second);

        // Assert
        assertTrue(result);
        verifyNoInteractions(view);
    }

    @Test
    public void testOnPlusClicked_ValidInput() {
        // Arrange
        when(view.getFirstArgumentAsString()).thenReturn("10");
        when(view.getSecondArgumentAsString()).thenReturn("20");
        when(calculator.sum(10.0, 20.0)).thenReturn(30.0);

        // Act
        presenter.onPlusClicked();

        // Assert
        verify(view).printResult(30.0);
    }

    @Test
    public void testOnPlusClicked_InvalidInput() {
        // Arrange
        when(view.getFirstArgumentAsString()).thenReturn("10");
        when(view.getSecondArgumentAsString()).thenReturn("abc");

        // Act
        presenter.onPlusClicked();

        // Assert
        verify(view).displayError("некорректный формат ввода!");
    }

    @Test
    public void testOnMinusClicked_ValidInput() {
        // Arrange
        when(view.getFirstArgumentAsString()).thenReturn("30");
        when(view.getSecondArgumentAsString()).thenReturn("20");
        when(calculator.subtract(30.0, 20.0)).thenReturn(10.0);

        // Act
        presenter.onMinusClicked();

        // Assert
        verify(view).printResult(10.0);
    }

    @Test
    public void testOnMinusClicked_InvalidInput() {
        // Arrange
        when(view.getFirstArgumentAsString()).thenReturn("30");
        when(view.getSecondArgumentAsString()).thenReturn("abc");

        // Act
        presenter.onMinusClicked();

        // Assert
        verify(view).displayError("некорректный формат ввода!");
    }

    @Test
    public void testOnDivideClicked_ValidInput() {
        // Arrange
        when(view.getFirstArgumentAsString()).thenReturn("10");
        when(view.getSecondArgumentAsString()).thenReturn("2");
        when(calculator.divide(10.0, 2.0)).thenReturn(5.0);

        // Act
        presenter.onDivideClicked();

        // Assert
        verify(view).printResult(5.0);
    }

    @Test
    public void testOnDivideClicked_DivisionByZero() {
        // Arrange
        when(view.getFirstArgumentAsString()).thenReturn("10");
        when(view.getSecondArgumentAsString()).thenReturn("0");
        when(calculator.divide(10.0, 0.0)).thenThrow(new ArithmeticException());

        // Act
        presenter.onDivideClicked();

        // Assert
        verify(view).displayError("ошибка деления на ноль!");
    }

    @Test
    public void testOnMultiplyClicked_ValidInput() {
        // Arrange
        when(view.getFirstArgumentAsString()).thenReturn("5");
        when(view.getSecondArgumentAsString()).thenReturn("4");
        when(calculator.multiply(5.0, 4.0)).thenReturn(20.0);

        // Act
        presenter.onMultiplyClicked();

        // Assert
        verify(view).printResult(20.0);
    }

    @Test
    public void testOnMultiplyClicked_InvalidInput() {
        // Arrange
        when(view.getFirstArgumentAsString()).thenReturn("5");
        when(view.getSecondArgumentAsString()).thenReturn("abc");

        // Act
        presenter.onMultiplyClicked();

        // Assert
        verify(view).displayError("некорректный формат ввода!");
    }
}