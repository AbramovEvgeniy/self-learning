package com.application.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

@Data
public class Person implements Serializable {

    private String name;
    private Set<Interest> interests;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(name, person.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
