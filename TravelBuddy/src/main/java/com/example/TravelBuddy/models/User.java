package com.example.TravelBuddy.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")

@Entity
@Table(name = "users")
public class User {
    ///creating columns
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    @Column
    private String password;
    ////stating relations
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="user_profile_id")
    private UserProfile userProfile;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Post> posts;



    @OneToMany(cascade = CascadeType.ALL)
    private List<Comment> comments;


    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }



    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public User() {}

    public void addPost(Post post){
        if(posts == null)
            posts = new ArrayList<>();
        posts.add(post);

    }

    public UserProfile getUserProfile() { return userProfile; }

    public void setUserProfile(UserProfile userProfile) { this.userProfile = userProfile; }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
