package com.application.stream;

import org.junit.Test;

import static org.junit.Assert.*;

public class StreamPeekTest {

    public static int t() {
        try {
            throw new IllegalAccessException();
        } catch (Exception e) {
            return 2;
        } finally {
            return 3;
        }
    }

    @Test
    public void test() {
        System.out.println(StreamPeekTest.t());
    }
}