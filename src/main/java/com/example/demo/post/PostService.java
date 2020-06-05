package com.example.demo.post;

import com.example.demo.post.dto.CreatePostRequest;
import com.example.demo.post.dto.UpdatePostRequest;
import com.example.demo.user.User;
import com.example.demo.user.UserService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

// TODO: Service -> Controller로 변환되는 값들은 가급적 DTO!
@Service
public class PostService {
    private UserService userService;
    private final PostRepository postRepository;

    public PostService(UserService userService, PostRepository postRepository) {
        this.userService = userService;
        this.postRepository = postRepository;
    }

    public Post create(HttpSession httpSession, CreatePostRequest createPostRequest) {
        User loginUser = (User) httpSession.getAttribute("LOGIN_USER");

        Post post = Post.builder()
                .contents(createPostRequest.getContent())
                .author(loginUser)
                .build();

        postRepository.save(post);
        return post;
    }

    public Post findById(Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 post 입니다."));
    }

    public void update(UpdatePostRequest updatePostRequest) {
        Post newPost = Post.of(updatePostRequest.getId(), updatePostRequest.getContents(), updatePostRequest.getAuthor());
        postRepository.save(newPost);
    }

    public void delete(Long id) {
        postRepository.delete(postRepository.getOne(id));
    }

    public void likePost(HttpSession httpSession, Long id) {
        User loginUser = (User) httpSession.getAttribute("LOGIN_USER");
        Post post = findById(id);
        post.addLike(loginUser);
        postRepository.save(post);
    }
}
