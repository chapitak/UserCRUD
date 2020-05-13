package com.example.demo.user.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UserResponse {
    private String email;
    private String name;

    @Builder
    public UserResponse(String email, String name) {
        this.email = email;
        this.name = name;
    }
}
