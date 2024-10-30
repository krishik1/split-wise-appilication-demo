package com.split.wise.application.splitwiseapplication.service;

import com.split.wise.application.splitwiseapplication.model.User;
import com.split.wise.application.splitwiseapplication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public User signUp(String name, String email, String password) throws Exception {
        Optional<User> user = userRepository.findUserByEmail(email);
        if(user.isPresent()) {
            throw new Exception("User Already Exists .");
        }
        User userNew = new User();
        userNew.setName(name);
        userNew.setEmail(email);
        BCryptPasswordEncoder passwordEncoder =new BCryptPasswordEncoder();
        userNew.setPassword(passwordEncoder.encode(password));
        return userRepository.save(userNew);
    }

    @Override
    public User login(String email, String password) throws Exception {
        Optional<User> user = userRepository.findUserByEmail(email);
        if(user.isEmpty()) {
            throw new Exception("User with email does not exist .");
        }
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        User curruser = user.get();
        if(passwordEncoder.matches(password, curruser.getPassword())) {
            return user.get();
        }
        throw new Exception("Invalid Password .");
    }
}
