package com.split.wise.application.splitwiseapplication.controller;

import com.split.wise.application.splitwiseapplication.dto.UserLoginRequestDTO;
import com.split.wise.application.splitwiseapplication.dto.UserRegistrationRequestDTO;
import com.split.wise.application.splitwiseapplication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/signup")
    public ResponseEntity signUp(@RequestBody UserRegistrationRequestDTO requestDTO) throws Exception {
        validateUserRegistrationRequestDTO(requestDTO);
        return ResponseEntity.ok(userService.signUp(requestDTO.getName(), requestDTO.getPassword(), requestDTO.getEmail()));
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody UserLoginRequestDTO requestDTO) throws Exception {
        return ResponseEntity.ok(userService.login(requestDTO.getEmail(),requestDTO.getPassword()));
    }

    public void validateUserRegistrationRequestDTO(UserRegistrationRequestDTO requestDTO) throws Exception {
        if(! requestDTO.getEmail().matches("[a-z0-9]+@[a-z]+\\.[a-z]{2,}$")){
            throw new Exception("Enter valid email .");
        }
        if (! requestDTO.getPassword().matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$")) {
            throw new Exception("Create Strong Password.");
        }

        if(requestDTO.getName() == null || requestDTO.getPassword() ==null || requestDTO.getEmail()==null) {
            throw new Exception("Please enter the required fields .");
        }
    }
}
