package com.example.TravelBuddy.service;

import com.example.TravelBuddy.models.Comment;
import com.example.TravelBuddy.models.User;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    public User getUser(String username);

    public Iterable<User> listUsers();

    public String createUser(User newUser);

    public String login(String username, String password);

    public String login(User user);

    public HttpStatus deleteById(Long userId);

    public User addPost(String username, long postId);
//
//    public User addComment(Comment newComment,String username, long postId);

}

