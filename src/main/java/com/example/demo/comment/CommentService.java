package com.example.demo.comment;

import com.example.demo.comment.dto.CommentResponse;
import com.example.demo.comment.dto.CreateCommentRequest;
import com.example.demo.comment.dto.UpdateCommentRequest;
import com.example.demo.post.Post;
import com.example.demo.post.PostRepository;
import com.example.demo.user.User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    public CommentService(CommentRepository commentRepository, PostRepository postRepository) {
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
        Comment comment = commentRepository.findById(updateCommentRequest.getId()).orElseThrow(() -> new IllegalArgumentException("해당 comment가 없습니다"));
        validateUser(loginUser, comment);

        Comment newComment = Comment.builder()
                .id(updateCommentRequest.getId())
                .contents(updateCommentRequest.getContents())
                .author(loginUser)
                .build();
        commentRepository.save(newComment);
    }

    private void validateUser(User loginUser, Comment comment) {
        if (!loginUser.equals(commentRepository.findById(comment.getId()))) {
            throw new IllegalArgumentException("comment를 삭제할 권한이 없습니다");
        }
    }

    public void delete(HttpSession httpSession, Long id) {
        User loginUser = (User) httpSession.getAttribute("LOGIN_USER");
        validateUser(loginUser, commentRepository.getOne(id));
        commentRepository.delete(commentRepository.getOne(id));
    }

    public List<Comment> getComments(Long postId) {
        return commentRepository.findByPost(postRepository.findById(postId));
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
