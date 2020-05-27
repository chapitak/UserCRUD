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
//    private Map<Long, Post> posts = new HashMap<>();
    private final PostRepository postRepository;
    private Long nextId = 0L;

    public PostService(UserService userService, PostRepository postRepository) {
        this.userService = userService;
        this.postRepository = postRepository;
    }

    public Post create(CreatePostRequest createPostRequest) {
        User author = userService.get(createPostRequest.getAuthorId());
        Post post = Post.builder()
                .id(nextId)
                .contents(createPostRequest.getContent())
                .author(author)
                .build();

        postRepository.save(post);
        nextId++;
        return post;
    }

    public Post read(Long id) {
        return postRepository.getOne(id);
    }

    public void update(UpdatePostRequest updatePostRequest) {
        Post newPost = Post.of(updatePostRequest.getId(), updatePostRequest.getContents(), updatePostRequest.getAuthor());
        postRepository.save(newPost);
    }

    public void delete(Long id) {
        postRepository.delete(postRepository.getOne(id));
    }

    private Post findById(Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 post 입니다."));
    }

    public void likePost(HttpSession httpSession, Long id) {
        User loginUser = (User) httpSession.getAttribute("LOGIN_USER");
        Post post = findById(id);
        post.addLike(loginUser);
        postRepository.save(post);
    }
}
