package com.ccsu.course.registration.service;

import com.ccsu.course.registration.exception.ResourceNotFoundException;
import com.ccsu.course.registration.repository.LoginRepository;
import com.ccsu.course.registration.entity.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginService {

    Boolean response = false;

    @Autowired
    private LoginRepository loginRepository;

    public Optional<Login> getUserDetails(String uname) throws ResourceNotFoundException {
       return loginRepository.findByUserName(uname);
    }
}