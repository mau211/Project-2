package com.example.TravelBuddy.service;

import com.example.TravelBuddy.models.Post;
import com.example.TravelBuddy.models.User;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    public User getUser(String username);

    public Iterable<User> listUsers();

    public User createUser(User newUser);

    public User login(String username, String password);

    public String login(User user);

    public HttpStatus deleteById(Long userId);

    public Post addPost(String username, long postId);
}

