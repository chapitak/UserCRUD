package com.example.demo.post.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class CreatePostRequest {
    private String content;

    public CreatePostRequest(String content) {
        this.content = content;
    }
}
