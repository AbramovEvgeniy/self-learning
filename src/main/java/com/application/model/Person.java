package com.application.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Set;

@Data
public class Person implements Serializable {

    private String name;
    private Set<Interest> interests;
}
