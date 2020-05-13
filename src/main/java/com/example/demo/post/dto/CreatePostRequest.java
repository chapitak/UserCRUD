package com.example.demo.post.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor // 인자없는 생성자만들어주세요래
@Getter
public class CreatePostRequest {
    private String content;
    private Long authorId;

    public CreatePostRequest(String content, Long authorId) {
        this.content = content;
        this.authorId = authorId;
    }
}
