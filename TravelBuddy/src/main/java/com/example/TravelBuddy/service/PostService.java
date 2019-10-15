package com.example.TravelBuddy.service;

import com.example.TravelBuddy.models.Post;
import org.springframework.http.HttpStatus;

public interface PostService {
    public Post createPost(Post post);

    public Iterable<Post> listPosts();

    public HttpStatus deleteById(Long postId);

}
