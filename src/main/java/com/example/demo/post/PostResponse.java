package com.example.demo.post;

import com.example.demo.like.LikeAction;
import com.example.demo.comment.Comment;
import com.example.demo.user.User;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
public class PostResponse {
    private Long id;
    private String contents;
    private User author;
    private int viewCount;
    private List<LikeAction> likes = new ArrayList<>();
    private List<Comment> comments = new ArrayList<>();
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    @Builder
    public PostResponse(Long id, String contents, User author, int viewCount, List<LikeAction> likes, List<Comment> comments, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.contents = contents;
        this.author = author;
        this.viewCount = viewCount;
        this.likes = likes;
        this.comments = comments;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}
