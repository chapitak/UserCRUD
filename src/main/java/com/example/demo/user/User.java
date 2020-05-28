package com.example.demo.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;

//CRUD
@Getter
@NoArgsConstructor
@Entity
@EntityListeners(value = { AuditingEntityListener.class })
public class User {
    @Id
    private Long id;
    private String email;
    private String password;
    private String name;
    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime modifiedAt;

    @Builder
    private User(Long id, String email, String password, String name) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public boolean matchPassword(String encryptedPasswordQuery) {
        return encryptedPasswordQuery.equals(PasswordEncryptor.encrypt(password));
    }
}
