package com.example.TravelBuddy.service;



import com.example.TravelBuddy.config.JwtUtil;
import com.example.TravelBuddy.models.Post;
import com.example.TravelBuddy.models.User;
import org.springframework.context.annotation.Bean;
import com.example.TravelBuddy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    JwtUtil jwtUtil;

    @Override
    public String login(User user){
        if(userRepository.login(user.getUsername(), user.getPassword()) != null){
            UserDetails userDetails = loadUserByUsername(user.getUsername());
            return jwtUtil.generateToken(userDetails);
        }
        return null;
    }
    @Autowired
    UserRepository userRepository;
//    @Autowired
//    UserRoleService userRoleService;
//    @Autowired
//    CourseService courseService;
//    @Autowired
//    CourseRepository courseRepository;
    @Override
    public User getUser(String username) {
        return userRepository.findByUsername(username);
    }
    @Override
    public Iterable<User> listUsers() {
        return userRepository.findAll();
    }
    @Override
    public User createUser(User newUser) {
//        UserRole userRole = userRoleService.getRole(newUser.getUserRole().getName());
//        newUser.setUserRole(userRole);
        return userRepository.save(newUser);
    }
    @Override
    public User login(String username, String password) {
        return userRepository.login(username, password);
    }
//    @Override
//    public User addCourse(String username, int courseId) {
//        Course course = courseRepository.findById(courseId).get();
//        User user = getUser(username);
//        user.addCourse(course);
//        return userRepository.save(user);
//    }
    @Override
    public HttpStatus deleteById(Long userId){
        userRepository.deleteById(userId);
        return HttpStatus.OK;
    }
    @Override
    public Post addPost(String username, long postId) {
        return null;
    }

    @Autowired
    @Qualifier("encoder")
    PasswordEncoder bCryptPasswordEncoder;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = getUser(username);
        if(user==null)
            throw new UsernameNotFoundException("User null");
        return new org.springframework.security.core.userdetails.User(user.getUsername(), bCryptPasswordEncoder.encode(user.getPassword()),
                true, true, true, true, new ArrayList<>());
    }
//    private List<GrantedAuthority> getGrantedAuthorities(User user){
//        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
//        authorities.add(new SimpleGrantedAuthority(user.getUserRole().getName()));
//        return authorities;


}
