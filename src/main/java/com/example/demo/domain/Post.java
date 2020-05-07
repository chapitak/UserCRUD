package com.example.demo.domain;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Post {
    private Long id;
    private String contents;
    private int viewCount;
    private int likeCount;
    private User author;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    @Builder
    public Post(Long id, String contents, User author) {
        this.id = id;
        this.contents = contents;
        this.viewCount = 0;
        this.likeCount = 0;
        this.author = author;
        this.createdAt = LocalDateTime.now();
        this.modifiedAt = LocalDateTime.now();
    }

    public static Post of(Long id, String contents, User author) {
        return new Post(id, contents, author);
    }
}
