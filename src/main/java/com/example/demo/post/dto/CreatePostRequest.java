package com.example.demo.post.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class CreatePostRequest {
    private String content;
    private Long authorId;

    public CreatePostRequest(String content, Long authorId) {
        this.content = content;
        this.authorId = authorId;
    }
}
