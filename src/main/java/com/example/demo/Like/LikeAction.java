package com.example.demo.Like;

import com.example.demo.post.Post;
import com.example.demo.user.User;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
public class LikeAction {
    @Id
    private Long id;
    @ManyToOne
    private User author;
    @ManyToOne
    private Post post;
    private LocalDateTime createdAt;

    public LikeAction(Post post, User loginUser) {
        this.post = post;
        this.author = loginUser;
    }
}
