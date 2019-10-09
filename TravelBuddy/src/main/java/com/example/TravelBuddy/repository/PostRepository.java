package com.example.TravelBuddy.repository;

import com.example.TravelBuddy.models.Post;
import com.example.TravelBuddy.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends CrudRepository<Post, Long> {
}
