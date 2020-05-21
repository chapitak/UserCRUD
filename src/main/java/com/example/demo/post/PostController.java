package com.example.demo.post;

import com.example.demo.post.dto.CreatePostRequest;
import com.example.demo.post.dto.UpdatePostRequest;
import org.springframework.web.bind.annotation.*;

@RestController
public class PostController {
    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/posts/{id}")
    public Post get(@PathVariable Long id) {
        System.out.println("id=" + id);
        return postService.read(id);
    }

    @PostMapping("posts")
    public Post createPost(@RequestBody CreatePostRequest createPostRequest) {
        return postService.create(createPostRequest);
    }

    @PutMapping("posts")
    public void putPost(@RequestBody UpdatePostRequest updatePostRequest) {
        postService.update(updatePostRequest);
    }

    @DeleteMapping("/posts/{id}")
    public String deletePost(@PathVariable Long id) {
        postService.delete(id);
        return "delete";
    }
}
