package com.example.TravelBuddy.service;

import com.example.TravelBuddy.models.Comment;
import org.springframework.http.HttpStatus;

public interface CommentService {
    public Comment createComment(Comment newComment, String username, Long postId);

    public Iterable<Comment> listComments();

    public HttpStatus deleteById(Long commentId);

}
