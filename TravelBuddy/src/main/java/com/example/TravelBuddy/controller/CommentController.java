package com.example.TravelBuddy.controller;


import com.example.TravelBuddy.models.Comment;
import com.example.TravelBuddy.models.Post;
import com.example.TravelBuddy.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class CommentController {

    @Autowired
    CommentService commentService;

    @PostMapping("/{username}/{postId}/comment")
    public Comment userCreateComment(@RequestBody Comment newComment, @PathVariable String username,@PathVariable Long postId){
        return commentService.createComment(newComment, username, postId);
    }

    @GetMapping("comment/list")
    public Iterable<Comment> listComments(){
        return commentService.listComments();
    }


    @DeleteMapping("comment/{commentId}")
    public HttpStatus deleteCommentById(@PathVariable Long commentId) {
        return commentService.deleteById(commentId);
    }


}
