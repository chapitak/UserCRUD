package com.example.demo.controller;

import com.example.demo.domain.Post;
import com.example.demo.service.PostService;
import org.springframework.web.bind.annotation.*;

@RestController
public class PostController {
    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/users/{id}")
    public Post get(@PathVariable Long id) {
        System.out.println("id=" + id);
        return postService.read(id);
    }

    @PostMapping("users")   //@RequestBody 안붙여도 된다그러지않았나.. 이보시오
    public Post createPost(@RequestBody CreatePostRequest createPostRequest) {
        return postService.create(createPostRequest);
    }

//    @RequestMapping(value = "/users", method = RequestMethod.PUT)
    @PutMapping("users")
    public Post putPost(@RequestBody UpdatePostRequest updatePostRequest) {
        return postService.update(updatePostRequest);
    }

    @DeleteMapping("/users/{id}")
    //@RequestMapping(value = "/users", method = RequestMethod.DELETE)
    public String deletePost(@PathVariable Long id) {
        postService.delete(id);
        return "delete";
    }
}
