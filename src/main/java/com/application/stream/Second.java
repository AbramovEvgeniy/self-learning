package com.application.stream;

public interface Second {

    default void message() {
        System.out.println(First.class.getCanonicalName());
    }
}
