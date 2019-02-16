package com.application.utils;


import org.junit.Assert;
import org.junit.Test;

public class FactorialFibonacciTest {

    int[] fibonacciRow = new int[]{1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89};

    @Test
    public void testFactorial() {
        Assert.assertEquals(120, FactorialFibonacci.factorial(5));
        Assert.assertEquals(1, FactorialFibonacci.factorial(0));
    }

    @Test
    public void testFibonacciArray() {
        Assert.assertArrayEquals(fibonacciRow, FactorialFibonacci.fibonacciWithoutRecursion(11));
    }

    @Test
    public void testFibonacciGetElement() {
        for (int i = 0; i <= fibonacciRow.length - 1; i++) {
            Assert.assertEquals(fibonacciRow[i], FactorialFibonacci.fibonacciGetElement(i + 1));
        }
    }
}