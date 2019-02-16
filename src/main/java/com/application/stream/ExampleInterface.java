package com.application.stream;

public class ExampleInterface implements First, Second {

    @Override
    public void message() {
        First.super.message();
    }
}
