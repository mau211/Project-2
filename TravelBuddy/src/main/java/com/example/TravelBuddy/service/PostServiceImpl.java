package com.example.TravelBuddy.service;


import com.example.TravelBuddy.models.Post;
import com.example.TravelBuddy.models.User;
import com.example.TravelBuddy.repository.PostRepository;
import com.example.TravelBuddy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    PostRepository postRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public Iterable<Post> listPosts(){
        return postRepository.findAll();
    }

    @Override
    public Post createPost(Post post, String username){
        User user = userRepository.findByUsername(username);
        post.setUser(user);
                return postRepository.save(post);
    }

    @Override
    public HttpStatus deleteById(Long postId) {
        postRepository.deleteById(postId);
        return HttpStatus.OK;
    }

}




