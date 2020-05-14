package com.example.demo.user;

import lombok.Builder;
import lombok.Getter;

import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;

//CRUD
@Getter
public class User {
    private Long id;
    private String email;
    private String password;
    private String name;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    @Builder
    private User(Long id, String email, String password, String name) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public boolean matchPassword(String encryptedPasswordQuery) throws NoSuchAlgorithmException {
        return encryptedPasswordQuery == PasswordEncryptor.encrypt(password);
    }
}
