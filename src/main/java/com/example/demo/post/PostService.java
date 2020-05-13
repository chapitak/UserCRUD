package com.example.demo.post;

import com.example.demo.post.dto.CreatePostRequest;
import com.example.demo.post.dto.UpdatePostRequest;
import com.example.demo.user.User;
import com.example.demo.user.UserService;
import org.springframework.stereotype.Service;

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

    public Post read(Long id) {
        if (posts.containsKey(id)) {
            return posts.get(id);
        }
        throw new IllegalArgumentException();
    }

    public Post update(UpdatePostRequest updatePostRequest) {
        Post newPost = Post.of(updatePostRequest.getId(), updatePostRequest.getContents(), updatePostRequest.getAuthor());
        posts.put(updatePostRequest.getId(), newPost);

        return posts.get(updatePostRequest.getId());
    }

    public void delete(Long id) {
        posts.remove(id);

    }
}
