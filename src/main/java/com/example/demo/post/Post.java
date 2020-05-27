package com.example.demo.post;

import com.example.demo.Like.LikeAction;
import com.example.demo.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Post {
    @Id
    private Long id;
    private String contents;
    @ManyToOne
    private User author;
    private int viewCount;
    @OneToMany(mappedBy = "post")
    private List<LikeAction> likes;
    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime modifiedAt;

    @Builder
    public Post(Long id, String contents, User author) {
        this.id = id;
        this.contents = contents;
        this.author = author;
        this.viewCount = 0;
    }

    public static Post of(Long id, String contents, User author) {
        return new Post(id, contents, author);
    }

    public void addLike(User loginUser) {
        likes.add(new LikeAction(this, loginUser));

    }
}
