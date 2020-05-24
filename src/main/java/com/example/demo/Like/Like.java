package com.example.demo.Like;

import com.example.demo.post.Post;
import com.example.demo.user.User;

import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

public class Like {
    @Id
    private Long id;
    @ManyToOne
    private User author;
    @ManyToOne
    private Post post;
    private LocalDateTime createdAt;
}
