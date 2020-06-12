package com.example.demo.comment.dto;

import lombok.Getter;

@Getter
public class UpdateCommentRequest {
    private long id;
    private String contents;

    public UpdateCommentRequest(long id, String contents) {
        this.id = id;
        this.contents = contents;
    }
}
