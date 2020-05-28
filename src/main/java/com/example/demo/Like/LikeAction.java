package com.example.demo.Like;

import com.example.demo.post.Post;
import com.example.demo.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor
public class LikeAction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private User author;
    @ManyToOne
    @JoinColumn(name="POST_ID")
    private Post post;
    @CreatedDate
    private LocalDateTime createdAt;

    public LikeAction(Long id, Post post, User loginUser) {
        this.id = id;
        this.post = post;
        this.author = loginUser;
    }
}
