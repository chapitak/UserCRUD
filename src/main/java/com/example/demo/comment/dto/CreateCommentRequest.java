package com.example.demo.comment.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CreateCommentRequest {
    private String content;
    private Long postId;
}
