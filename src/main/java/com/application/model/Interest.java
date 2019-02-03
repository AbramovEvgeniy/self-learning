package com.application.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Interest implements Serializable {

    private String interestName;

    @Override
    public String toString() {
        return interestName;
    }
}
