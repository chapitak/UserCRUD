package com.example.demo.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class PasswordEncryptorTest {
    @Test
    @DisplayName("암호가 다를 때")
    void testDifferentPassword() {
        //given
        String pass1 = PasswordEncryptor.encrypt("1234");
        String pass2 = PasswordEncryptor.encrypt("12345");

        //then
        assertThat(pass1).isNotEqualTo(pass2);
    }

    @Test
    @DisplayName("암호가 같을 때")
    void testSamePassword() {
        //given
        String pass1 = PasswordEncryptor.encrypt("1234");
        String pass2 = PasswordEncryptor.encrypt("1234");

        //then
        assertThat(pass1).isEqualTo(pass2);
    }
}