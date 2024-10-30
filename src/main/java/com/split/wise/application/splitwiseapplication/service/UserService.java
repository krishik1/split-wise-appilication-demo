package com.split.wise.application.splitwiseapplication.service;

import com.split.wise.application.splitwiseapplication.model.User;

public interface UserService {
    User signUp(String name,String email,String password) throws Exception;
    User login(String email,String password) throws Exception;
}
