package com.example.TravelBuddy.repository;

import com.example.TravelBuddy.models.Comment;
import com.example.TravelBuddy.models.Post;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Long>{
}
