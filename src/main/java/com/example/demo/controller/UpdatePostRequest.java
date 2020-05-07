package com.example.demo.controller;

import com.example.demo.domain.User;
import lombok.Getter;

@Getter
public class UpdatePostRequest {
    private long id;
    private String contents;
    private User author;

    public UpdatePostRequest(long id, String contents, User author) {
        this.id = id;
        this.contents = contents;
        this.

    }
}
