package com.example.demo.comment.dto;

import com.example.demo.post.Post;
import com.example.demo.user.User;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentResponse {
    private String contents;
    private User author;
    private Post post;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    @Builder
    public CommentResponse(String contents, User author, Post post, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.contents = contents;
        this.author = author;
        this.post = post;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}
