package com.application.utils;

public class FactorialFibonacci {

    public static int factorial(int n) {
        if (n == 1 || n == 0) {
            return 1;
        }
        return factorial(n - 1) * n;
    }

    public static int[] fibonacciWithoutRecursion(int n) {
        int[] result = new int[n];
        int i = 0;
        while (i != n) {
            if (i <= 1) {
                result[i] = 1;
            } else {
                result[i] = result[i - 1] + result[i - 2];
            }
            i++;
        }
        return result;
    }

    public static int fibonacciGetElement(int n) {
        if (n <= 2) {
            return 1;
        }
        return fibonacciGetElement(n - 1) + fibonacciGetElement(n - 2);
    }
}
