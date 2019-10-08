package com.example.TravelBuddy.models;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "POST" )
public class Post {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String title;

    @Column
    private String description;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @OneToMany
    @JoinColumn(name="user_comment_id")
    private List<Comment> comment;

    public Post() {}

    public User getUser() { return user; }

    public void setUser(User user) { this.user = user; }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
