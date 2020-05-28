package com.example.demo.user;

import lombok.NoArgsConstructor;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@NoArgsConstructor
public class PasswordEncryptor {

    public static final String SHA_256 = "SHA-256";
    public static final String ENCRYPT_FAILURE = "there is no such algorithm";

    public static String encrypt(String password) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(SHA_256);
            messageDigest.update(messageDigest.digest());;
            return bytesToHex(messageDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalArgumentException(ENCRYPT_FAILURE);
        }
    }

    private static String bytesToHex(byte[] digest) {
        StringBuilder builder = new StringBuilder();
        for (byte b : digest) {
            builder.append(String.format("02x", b));
        }
        return builder.toString();
    }
}
