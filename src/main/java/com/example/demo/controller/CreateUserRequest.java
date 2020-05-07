package com.example.demo.controller;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserRequest {
    private String name;
    private String password;

    public CreateUserRequest(String name, String password) {
        this.name = name;
        this.password = password;
    }
}
