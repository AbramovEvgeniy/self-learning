package com.application.stream;

public interface First {

    default void message() {
        System.out.println(First.class.getCanonicalName());
    }
}
