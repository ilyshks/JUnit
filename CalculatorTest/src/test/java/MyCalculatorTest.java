import org.example.MyCalculator;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;


public class MyCalculatorTest {
    private static MyCalculator calculator;

    @BeforeAll
    static void createObject(){
        calculator = new MyCalculator();
    }

    @ParameterizedTest
    @CsvSource({ "1,2,3", "1.0,15.2,16.2", "-200,148,-52", "1000.92,20000.0,21000.92" })
    @DisplayName("Test sum operation")
    void sumTest(double a, double b, double expect){
        Assertions.assertEquals(expect, calculator.sum(a, b), 10e-8);
    }

    @ParameterizedTest
    @CsvSource({ "1,2,-1", "17.0,15.2,1.8", "-200,148,-348", "1000.92,20000.0,-18999.08" })
    @DisplayName("Test subtract operation")
    void subtractTest(double a, double b, double expect){
        Assertions.assertEquals(expect, calculator.subtract(a, b), 10e-8);
    }

    @ParameterizedTest
    @CsvSource({ "1,2,2", "17.0,15.2,258.4", "-200,148,-29600", "1000.92,200.36,200544.3312" })
    @DisplayName("Test multiply operation")
    void multiplyTest(double a, double b, double expect){
        Assertions.assertEquals(expect, calculator.multiply(a, b), 10e-8);
    }

    @ParameterizedTest
    @CsvSource({ "1,2,0.5", "18.0,15.0,1.2", "-201.28,148,-1.36", "1000.92,10e-9,0" })
    @DisplayName("Test divide operation")
    void divideTest(double a, double b, double expect){
        if (Math.abs(b) < 10e-8){
            ArithmeticException thrown = Assertions.assertThrows(ArithmeticException.class, () -> {
                calculator.divide(a, b);
                }, "ArithmeticException was expected");

            Assertions.assertEquals("Zero division error", thrown.getMessage());
        }else{
            Assertions.assertEquals(expect, calculator.divide(a, b), 10e-8);
        }
    }
}
