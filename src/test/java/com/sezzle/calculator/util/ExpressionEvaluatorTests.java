package com.sezzle.calculator.util;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class ExpressionEvaluatorTests {

    @Test
    public void testTwoOperandsExpressions() {
        String expression = "2+2";
        double d = ExpressionEvaluator.evaluate(expression).join();
        assertEquals(4.0, d, "Incorred Results");
    }

    @Test
    public void testMultipleOperandsExpressions() {
        String expression = "2+3+3";
        double d = ExpressionEvaluator.evaluate(expression).join();
        assertEquals(8.0, d, "Incorred Results");
    }

    @Test
    public void testBodmasExpression() {
        String expression = "2+(3*3)";
        double d = ExpressionEvaluator.evaluate(expression).join();
        assertEquals(11.0, d, "Incorred Results");
    }

    @Test
    public void testDecimalValuesExpression() {
        String expression = " 2/(3-7)";
        double d = ExpressionEvaluator.evaluate(expression).join();
        assertEquals(-0.5, d, "Incorred Results");
    }

    @Test
    public void testWhenExpressionIsInvalid() {
        String expression = "ten + ten";

        Exception exception = assertThrows(Exception.class, () -> {
            ExpressionEvaluator.evaluate(expression).join();
        }, "Exception Not Thrown.");
    }
}
