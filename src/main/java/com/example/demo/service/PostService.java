package com.example.demo.service;

import com.example.demo.controller.CreatePostRequest;
import com.example.demo.domain.Post;
import com.example.demo.domain.User;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

// TODO: Service -> Controller로 변환되는 값들은 가급적 DTO!
@Service
public class PostService {
    private UserService userService;
    private Map<Long, Post> posts = new HashMap<>();
    private Long nextId = 0L;

    public PostService(UserService userService) {
        this.userService = userService;
    }

    public Post create(CreatePostRequest createPostRequest) {
        User author = userService.getUsers().get(createPostRequest.getAuthorId());
        Post post = Post.builder()
                .id(nextId)
                .contents(createPostRequest.getContent())
                .author(author)
//                .createdAt(LocalDateTime.now())
//                .modifiedAt(LocalDateTime.now())
                .build();

        posts.put(post.getId(), post);
        nextId++;
        return post;
    }

    public Post get(Long id) {
        if (posts.containsKey(id)) {
            return posts.get(id);
        }
        throw new IllegalArgumentException();
    }

//    public Post update() {
//
//    }
//
//    public Post delete() {
//
//    }
}
