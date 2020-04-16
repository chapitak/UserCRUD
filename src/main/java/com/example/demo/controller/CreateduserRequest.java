package com.example.demo.controller;

import lombok.Getter;
import lombok.Setter;

public class CreateduserRequest {
    private String name;
    private String password;

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public CreateduserRequest(String name, String password) {
        this.name = name;
        this.password = password;
    }
}
