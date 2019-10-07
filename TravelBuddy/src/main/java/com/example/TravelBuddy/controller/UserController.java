package com.example.TravelBuddy.controller;


import com.example.TravelBuddy.models.User;
import com.example.TravelBuddy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    UserService userService;

//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/admin/u/list")
    public Iterable<User> listUsers() {
        return userService. listUsers();
    }

    @PostMapping("/signup")
    public User createUser(@RequestBody User newUser) {
        return userService.createUser(newUser);
    }

    @GetMapping("/login/{username}/{password}")
    public User login(@PathVariable String username, @PathVariable String password){
        return userService.login(username, password);
    }

    @PutMapping("/user/{username}/{postId}")
    public User addPost(@PathVariable String username, @PathVariable long postId){
        return userService.addPost(username, postId);
    }

    @DeleteMapping("/user/{userId}")
    public HttpStatus deleteUserById(@PathVariable Long userId) {
        return userService.deleteById(userId);
    }

    @GetMapping("/hello")
    public String helloWorld() {
        return "Hello World!!";
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        return ResponseEntity.ok(new JwtResponse(userService.login(user)));
    }

}