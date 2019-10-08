package com.example.TravelBuddy.service;

import com.example.TravelBuddy.models.UserProfile;

public interface UserProfileService {

    public UserProfile createUserProfile(String username, UserProfile newProfile);

    public UserProfile getUserProfile(String username);
}