package com.futureX.UserApp;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/")
    public List<User> getUser() {
        return userRepository.findAll();
    }
    

    @RequestMapping("/{id}")
    public List<User> getUsersForACourse(@PathVariable("id") BigInteger id) {
        return userRepository.findBycourseid(id);
    }
    
    @RequestMapping("user/{id}")
    public List<User> getCoursestoUser(@PathVariable("id") BigInteger id) {
        return userRepository.findBycourseid(id);
    }
}
