package com.example.demo.controller;

import com.example.demo.service.PostService;

public class PostController {
    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }


}
