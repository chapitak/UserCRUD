package com.example.demo.domain;

import lombok.Getter;

//CRUD
@Getter
public class User {
    private Long id;
    private String name;
    private String password;

    private User(Long id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    public static User of(Long id, String name, String password) {
        return new User(id, name, password);
    }
}
