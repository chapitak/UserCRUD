package com.example.demo.user.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserRequest {
    private String email;
    private String name;
    private String password;

    public CreateUserRequest(String email, String name, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
    }
}
