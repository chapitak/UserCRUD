package com.example.demo.post;

import com.example.demo.post.dto.CreatePostRequest;
import com.example.demo.post.dto.UpdatePostRequest;
import com.example.demo.user.User;
import com.example.demo.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

// TODO: Service -> Controller로 변환되는 값들은 가급적 DTO!
@Service
public class PostService {
    private UserService userService;
    private final PostRepository postRepository;
    Logger log  = LoggerFactory.getLogger(PostService.class);

    public PostService(UserService userService, PostRepository postRepository) {
        this.userService = userService;
        this.postRepository = postRepository;
    }

    public PostResponse create(HttpSession httpSession, CreatePostRequest createPostRequest) {
        User loginUser = (User) httpSession.getAttribute("LOGIN_USER");

        Post post = Post.builder()
                .contents(createPostRequest.getContent())
                .author(loginUser)
                .build();

        postRepository.save(post);

        PostResponse postResponse = getPostResponse(post);
        return postResponse;
    }

    public Post findById(Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 post 입니다."));
    }

    public PostResponse update(HttpSession httpSession, UpdatePostRequest updatePostRequest) {
        User loginUser = (User) httpSession.getAttribute("LOGIN_USER");

        Post newPost = Post.builder()
                .id(updatePostRequest.getId())
                .contents(updatePostRequest.getContents())
                .author(loginUser)
                .build();
        postRepository.save(newPost);
        return getPostResponse(newPost);
    }

    public PostResponse delete(Long id) {
        Post deletedPost = postRepository.getOne(id);
        postRepository.delete(postRepository.getOne(id));

        return getPostResponse(deletedPost);
    }

    @Transactional
    public void likePost(HttpSession httpSession, Long id) {
        User loginUser = (User) httpSession.getAttribute("LOGIN_USER");
        Post post = findById(id);
        post.addLike(loginUser);
        postRepository.save(post);
        log.info("{}", post.getLikes());
    }

    public PostResponse get(Long id) {
        Post post =  postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 post 입니다."));

        PostResponse postResponse = getPostResponse(post);
        return postResponse;
    }

    public PostResponse getPostResponse(Post post) {
        PostResponse postResponse = PostResponse.builder()
                .id(post.getId())
                .contents(post.getContents())
                .author(post.getAuthor())
                .viewCount(post.getViewCount())
                .likes(post.getLikes())
                .comments(post.getComments())
                .createdAt(post.getCreatedAt())
                .modifiedAt(post.getModifiedAt())
                .build();

        return postResponse;
    }
}
