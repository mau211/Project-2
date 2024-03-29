package com.example.TravelBuddy.controller;


import com.example.TravelBuddy.models.Post;
import com.example.TravelBuddy.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
//@RequestMapping("/post")
public class PostController {
    @Autowired
    PostService postService;

    @PostMapping("/post")
    public Post userCreatePost(@RequestBody Post post){
        return postService.createPost(post);
    }

    @GetMapping("post/list")
    public Iterable<Post> listPostToUser(){
        return postService.listPosts();
    }

    @DeleteMapping("post/{postId}")
    public HttpStatus deletePostById(@PathVariable Long postId) {
        return postService.deleteById(postId);
    }

}
