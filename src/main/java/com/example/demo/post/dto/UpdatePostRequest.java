package com.example.demo.post.dto;

import lombok.Getter;

@Getter
public class UpdatePostRequest {
    private long id;
    private String contents;

    public UpdatePostRequest(long id, String contents) {
        this.id = id;
        this.contents = contents;
    }
}
