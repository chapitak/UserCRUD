package com.example.demo.user.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateUserRequest {
    private Long id;
    private String name;
    private String password;

    public UpdateUserRequest(Long id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }
}
