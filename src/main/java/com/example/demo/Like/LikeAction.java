package com.example.demo.Like;

import com.example.demo.post.Post;
import com.example.demo.user.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor
@EntityListeners(value = { AuditingEntityListener.class })
public class LikeAction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @NotNull
    private User author;
    @ManyToOne
    @JsonBackReference
    private Post post;
    @CreatedDate
    private LocalDateTime createdAt;

    @Builder
    public LikeAction(Long id, Post post, User loginUser) {
        this.id = id;
        this.post = post;
        this.author = loginUser;
    }
}
