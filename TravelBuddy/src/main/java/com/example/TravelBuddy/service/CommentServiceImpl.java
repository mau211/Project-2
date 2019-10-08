package com.example.TravelBuddy.service;

import com.example.TravelBuddy.models.Comment;
import com.example.TravelBuddy.models.Post;
import com.example.TravelBuddy.models.User;
import com.example.TravelBuddy.repository.CommentRepository;
import com.example.TravelBuddy.repository.PostRepository;
import com.example.TravelBuddy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    PostRepository postRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CommentRepository commentRepository;

    @Override
    public Iterable<Comment> listComments(){
        return commentRepository.findAll();
    }

    @Override
    public Comment createComment(Comment newComment, String username, Long postId){
        User user = userRepository.findByUsername(username);
        newComment.setUser(user);
        return commentRepository.save(newComment);
    }


    @Override
    public HttpStatus deleteById(Long commentId) {
        commentRepository.deleteById(commentId);
        return HttpStatus.OK;
    }
}
