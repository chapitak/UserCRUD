package com.example.demo.post;

import com.example.demo.Like.LikeAction;
import com.example.demo.comment.Comment;
import com.example.demo.user.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
@EntityListeners(value = { AuditingEntityListener.class })
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String contents;
    @NotNull
    @ManyToOne
    @JsonIdentityInfo()
    private User author;
    private int viewCount = 0;
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<LikeAction> likes = new ArrayList<>();
    @OneToMany(mappedBy = "post")
    private List<Comment> comments = new ArrayList<>();
    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime modifiedAt;

    @Builder
    public Post(Long id, String contents, User author) {
        this.id = id;
        this.contents = contents;
        this.author = author;
    }

    public static Post of(Long id, String contents, User author) {
        return new Post(id, contents, author);
    }

    public void addLike(User loginUser) {
        this.likes.add(LikeAction.builder()
        .loginUser(loginUser)
        .post(this)
        .build());
    }
}
