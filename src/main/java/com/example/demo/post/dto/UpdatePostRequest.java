package com.example.demo.post.dto;

import com.example.demo.user.User;
import lombok.Getter;

@Getter
public class UpdatePostRequest {
    private long id;
    private String contents;
    private User author;

    public UpdatePostRequest(long id, String contents, User author) {
        this.id = id;
        this.contents = contents;
        this.author = author;

    }
}
