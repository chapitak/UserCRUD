package com.example.demo.user;

import lombok.NoArgsConstructor;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@NoArgsConstructor
public class PasswordEncryptor {


    public static String encrypt(String password) throws NoSuchAlgorithmException {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA_256");
            messageDigest.update(messageDigest.digest());;
            return bytesToHex(messageDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            throw new NoSuchAlgorithmException("there is no salgorithm");
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
