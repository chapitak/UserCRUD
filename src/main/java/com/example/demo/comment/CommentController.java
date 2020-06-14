package com.example.demo.comment;

import com.example.demo.comment.dto.CommentResponse;
import com.example.demo.comment.dto.CreateCommentRequest;
import com.example.demo.comment.dto.UpdateCommentRequest;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class CommentController {
    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("comments")
    public CommentResponse createComment(HttpSession httpSession, @RequestBody CreateCommentRequest createCommentRequest) {
        return commentService.create(httpSession, createCommentRequest);
    }

    @PutMapping("comments")
    public void putComment(HttpSession httpSession, @RequestBody UpdateCommentRequest updateCommentRequest) {
        commentService.update(httpSession, updateCommentRequest);
    }

    @DeleteMapping("/comments/{id}")
    public void deleteComment(@PathVariable Long id) {
        commentService.delete(id);
    }

    @GetMapping("/posts/{postId}/comments")
    public List<Comment> getComments(@PathVariable Long postId) {
        return commentService.getComments(postId);
    }

}
