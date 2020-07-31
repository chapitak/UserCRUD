package com.example.demo.comment;

import com.example.demo.post.Post;
import com.example.demo.user.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
@EntityListeners(value = {AuditingEntityListener.class})
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String contents;
    @NotNull
    @ManyToOne
    private User author;
    @ManyToOne
    @JsonBackReference
    private Post post;
    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime modifiedAt;

    @Builder
    public Comment(Long id, String contents, User author, Post post) {
        this.id = id;
        this.contents = contents;
        this.author = author;
        this.post = post;
    }
}
