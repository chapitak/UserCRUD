package com.example.demo.comment;

import com.example.demo.comment.dto.CommentResponse;
import com.example.demo.comment.dto.CreateCommentRequest;
import com.example.demo.comment.dto.UpdateCommentRequest;
import com.example.demo.post.Post;
import com.example.demo.post.PostRepository;
import com.example.demo.user.User;
import com.example.demo.user.UserService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private UserService userService;

    public CommentService(UserService userService, CommentRepository commentRepository, PostRepository postRepository) {
        this.userService = userService;
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    public CommentResponse create(HttpSession httpSession, CreateCommentRequest createCommentRequest) {
        User loginUser = (User) httpSession.getAttribute("LOGIN_USER");
        Post post = postRepository.findById(createCommentRequest.getPostId()).orElseThrow(() -> new IllegalArgumentException("해당 ID의 포스트가 없습니다"));

        Comment comment = Comment.builder()
                .contents(createCommentRequest.getContent())
                .author(loginUser)
                .post(post)
                .build();
        commentRepository.save(comment);
        return getCommentResponse(comment);
    }

    public void update(HttpSession httpSession, UpdateCommentRequest updateCommentRequest) {
        User loginUser = (User) httpSession.getAttribute("LOGIN_USER");

        Comment newComment = Comment.builder()
                .id(updateCommentRequest.getId())
                .contents(updateCommentRequest.getContents())
                .author(loginUser)
                .build();
        commentRepository.save(newComment);
    }

    public void delete(Long id) {
        commentRepository.delete(commentRepository.getOne(id));
    }

    public List<Comment> getComments(Long postId) {
        commentRepository.findByPost(postRepository.findById(postId));
        return null;// commentRepository.find;
    }

    private CommentResponse getCommentResponse(Comment comment) {

        return CommentResponse.builder()
                .contents(comment.getContents())
                .author(comment.getAuthor())
                .post(comment.getPost())
                .createdAt(comment.getCreatedAt())
                .modifiedAt(comment.getModifiedAt())
                .build();
    }
}
