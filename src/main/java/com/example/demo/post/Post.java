package com.example.demo.post;

import com.example.demo.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
public class Post {
    @Id
    private Long id;
    private String contents;
    private int viewCount;
    private int likeCount;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    @Builder
    public Post(Long id, String contents, User author) {
        this.id = id;
        this.contents = contents;
        this.viewCount = 0;
        this.likeCount = 0;
        this.createdAt = LocalDateTime.now();
        this.modifiedAt = LocalDateTime.now();
    }

    public static Post of(Long id, String contents, User author) {
        return new Post(id, contents, author);
    }
}
