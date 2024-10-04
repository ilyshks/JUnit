import org.example.Main;
import org.fest.swing.fixture.FrameFixture;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;


public class CalculatorGUITest {

    private static FrameFixture window;

    private static final String prefix = "Результат: ";

    @BeforeAll
    static void setUp() {
        // Создаем экземпляр калькулятора и отображаем его
        Main calculatorGUI = new Main();
        calculatorGUI.setVisible(true);
        // Создаем фикстуру для взаимодействия с окном калькулятора
        window = new FrameFixture(calculatorGUI);
        window.show(); // Показываем окно для тестирования
    }

    @AfterEach
    public void clearTextFields(){
        window.textBox("inputField1").setText("");
        window.textBox("inputField2").setText("");
    }

//    @AfterAll
//    public static void tearDown() {
//        // Закрываем окно после тестов
//        window.cleanUp();
//    }

    @ParameterizedTest
    @CsvSource({
            "9, 3, 12.0",
            "10, -2, 8.0",
            "a, 1, некорректный формат ввода!",
            "8, 0, 8.0"
    })
    public void testAddition(String input1, String input2, String expectedResult) {
        // Вводим числа в поля ввода
        window.textBox("inputField1").enterText(input1);
        window.textBox("inputField2").enterText(input2);

        // Нажимаем кнопку сложения
        window.button("+").click();

        // Проверяем результат
        window.label("resultLabel").requireText(prefix + expectedResult);
    }

    @ParameterizedTest
    @CsvSource({
            "9, 3, 6.0",
            "10, -2, 12.0",
            "a, 1, некорректный формат ввода!",
            "8, 0, 8.0"
    })
    public void testSubtraction(String input1, String input2, String expectedResult) {
        window.textBox("inputField1").enterText(input1);
        window.textBox("inputField2").enterText(input2);

        // Нажимаем кнопку сложения
        window.button("-").click();

        // Проверяем результат
        window.label("resultLabel").requireText(prefix + expectedResult);
    }

    @ParameterizedTest
    @CsvSource({
            "9, 3, 27.0",
            "10, -2, -20.0",
            "a, 1, некорректный формат ввода!",
            "8, 0, 0.0"
    })
    public void testMultiplication(String input1, String input2, String expectedResult) {
        window.textBox("inputField1").enterText(input1);
        window.textBox("inputField2").enterText(input2);

        // Нажимаем кнопку сложения
        window.button("*").click();

        // Проверяем результат
        window.label("resultLabel").requireText(prefix + expectedResult);
    }

    @ParameterizedTest
    @CsvSource({
            "9, 3, 3.0",
            "10, -2, -5.0",
            "a, 1, некорректный формат ввода!",
            "8, 0, ошибка деления на ноль!"
    })
    public void testDivision(String input1, String input2, String expectedResult) {
        window.textBox("inputField1").enterText(input1);
        window.textBox("inputField2").enterText(input2);

        // Нажимаем кнопку сложения
        window.button("/").click();

        // Проверяем результат
        window.label("resultLabel").requireText(prefix + expectedResult);
    }
}
