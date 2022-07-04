import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CalculatorTest {
    @Test
    void testPlus(){
        Calculator calculator =new Calculator();

        assertEquals(5, calculator.evaluate("2 + 3"));
    }
    @Test
    void testSubtract(){
        Calculator calculator =new Calculator();

        assertEquals(-7, calculator.evaluate("2 - 9"));
    }
    @Test
    void testMultiply(){
        Calculator calculator =new Calculator();

        assertEquals(21, calculator.evaluate("7 * 3"));
    }

    @Test
    void testDivide(){
        Calculator calculator =new Calculator();

        assertEquals(7, calculator.evaluate("22 / 3"));
    }
    @Test
    void testPower(){
        Calculator calculator =new Calculator();

        assertEquals(822.1111450195312, calculator.evaluate("7.2 ^ 3.4"));
    }

    @Test
    void testExpression(){
        Calculator calculator =new Calculator();

        assertEquals(3339.36865234375, calculator.evaluate("55.2 * ( 72.1 + 4.2 ) - 16.67 * ( 55.333 - 3 )"));
    }


}