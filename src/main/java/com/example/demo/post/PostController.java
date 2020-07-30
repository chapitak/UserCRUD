package com.example.demo.post;

import com.example.demo.post.dto.CreatePostRequest;
import com.example.demo.post.dto.UpdatePostRequest;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
public class PostController {
    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("posts")
    public PostResponse createPost(HttpSession httpSession, @RequestBody CreatePostRequest createPostRequest) {
        return postService.create(httpSession, createPostRequest);
    }

    @GetMapping("/posts/{id}")
    public PostResponse get(@PathVariable Long id) {
        return postService.get(id);
    }

    @PutMapping("posts")
    public void putPost(HttpSession httpSession, @RequestBody UpdatePostRequest updatePostRequest) {
        postService.update(httpSession, updatePostRequest);
    }

    @DeleteMapping("/posts/{id}")
    public void deletePost(@PathVariable Long id) {
        postService.delete(id);
    }

    @PostMapping("/posts/{id}/like")
    public void createLike(HttpSession httpSession, @PathVariable Long id) {
        postService.likePost(httpSession, id);
    }

}
