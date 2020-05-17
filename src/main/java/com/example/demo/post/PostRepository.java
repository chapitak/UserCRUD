package com.example.demo.post;

public interface PostRepository {

    Post create(Post post);

    Post getById(Long id);

    Post update(Post post);

    boolean delete(Long id);
}
