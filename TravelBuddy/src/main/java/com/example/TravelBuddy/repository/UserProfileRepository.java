package com.example.TravelBuddy.repository;


import com.example.TravelBuddy.models.UserProfile;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UserProfileRepository extends CrudRepository<UserProfile, Long> {

//    @Query("from UserProfile up left join User u on u.username = ?1 and up.id = u.userProfile.id")
//    public UserProfile findProfileByUsername(String username);


    @Query("from UserProfile up inner join User u on u.username = ?1 and up.id = u.userProfile.id")
    public UserProfile findProfileByUsername(String username);

}