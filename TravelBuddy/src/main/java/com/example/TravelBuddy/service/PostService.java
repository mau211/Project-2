package com.example.TravelBuddy.service;

import com.example.TravelBuddy.models.Post;

public interface PostService {
    public Post createPost(Post post, String username);

    public Iterable<Post> listPosts();

    public String deletePostById(Long postId);

}
