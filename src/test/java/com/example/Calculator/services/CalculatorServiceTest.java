package com.example.Calculator.services;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CalculatorServiceTest {

    private final CalculatorService calculatorService = new CalculatorService();

    @Test
    public void testSum() {
        assertEquals(5.0, calculatorService.sum(2.0, 3.0));
    }

    @Test
    public void testSubtract() {
        assertEquals(1.0, calculatorService.subtract(3.0, 2.0));
    }

    @Test
    public void testMultiply() {
        assertEquals(6.0, calculatorService.multiply(2.0, 3.0));
    }

    @Test
    public void testDivide() {
        assertEquals(2.0, calculatorService.divide(6.0, 3.0));
    }

    @Test
    public void testDivideByZero() {
        assertThrows(IllegalArgumentException.class, () -> calculatorService.divide(1.0, 0.0));
    }
}
