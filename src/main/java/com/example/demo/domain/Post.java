package com.example.demo.domain;

import lombok.Builder;

import java.time.LocalDateTime;

public class Post {
    private Long id;
    private String contents;
    private int likeCount;
    private User author;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    @Builder
    public Post(Long id, String contents, User author) {
        this.id = id;
        this.contents = contents;
        this.likeCount = 0;
        this.author = author;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}
