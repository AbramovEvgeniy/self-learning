package com.application.onedome;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginEvent {

    private String loginEmail;
    private long loginDate;
}
