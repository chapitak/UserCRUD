package com.example.demo.post;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

//    Post create(Post post);
//
//    Post getById(Long id);
//
//    Post update(Post post);
//
//    boolean delete(Long id);
}
