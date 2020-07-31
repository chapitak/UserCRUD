package com.example.demo;

import com.example.demo.post.Post;
import com.example.demo.post.PostRepository;
import com.example.demo.post.PostService;
import com.example.demo.post.dto.CreatePostRequest;
import com.example.demo.user.User;
import com.example.demo.user.UserRepository;
import com.example.demo.user.UserService;
import com.example.demo.user.dto.CreateUserRequest;
import com.example.demo.user.dto.LoginRequest;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PostServiceTest {

    @Autowired
    private PostService postService;
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostRepository postRepository;

    @Autowired
    HttpSession httpSession;

    @Before
    public void setUp() {
        //    계정생성
        CreateUserRequest testUserCreateRequest = CreateUserRequest
                .builder()
                .email("test@dev.com")
                .name("테스트")
                .password("1234")
                .build();
        userService.create(testUserCreateRequest);
        //    로그인
        User user = userRepository.findByEmail("test@dev.com").orElseThrow(() -> new IllegalArgumentException());
        httpSession.setAttribute("LOGIN_USER", user);
        // 글쓰기
        Post post = Post.builder()
                .author(user)
                .contents("")
                .id(1L)
                .build();
        postRepository.save(post);
    }



    @Test
    @DisplayName("click Like")
    @Transactional    //TODO ???? eager로 수정하면
    void likePost() {
        //when
        postService.likePost(httpSession, 1L);

        //then
        assertThat(postRepository.findById(1L).orElseThrow(() -> new IllegalArgumentException()).getLikes().size()).isEqualTo(1);
    }

    @Test
    @DisplayName("click Like twice")
    @Transactional
    void likePostTwice() {
        //when
        postService.likePost(httpSession, 1L);
        postService.likePost(httpSession, 1L);

        //then
        assertThat(postRepository.getOne(1L).getLikes().size()).isEqualTo(0);
    }
}