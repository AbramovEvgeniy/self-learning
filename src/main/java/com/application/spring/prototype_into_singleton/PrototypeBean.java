package com.application.spring.prototype_into_singleton;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class PrototypeBean {

    private String currentDate = String.valueOf(Instant.now());
}
