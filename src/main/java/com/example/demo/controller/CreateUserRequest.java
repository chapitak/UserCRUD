package com.example.demo.controller;

import lombok.Getter;
import lombok.Setter;

public class CreateUserRequest {
    private String name;
    private String password;

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public CreateUserRequest(String name, String password) {
        this.name = name;
        this.password = password;
    }
}
