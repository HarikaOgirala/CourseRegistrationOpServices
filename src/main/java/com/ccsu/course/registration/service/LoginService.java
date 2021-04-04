package com.ccsu.course.registration.service;

import com.ccsu.course.registration.repository.LoginRepository;
import com.ccsu.course.registration.model.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

@Service
public class LoginService {

    Boolean response = false;

    @Autowired
    private LoginRepository loginRepository;
    private Login login;

    public Boolean authenticateUser(String uname, String password) throws IOException {/*
        Optional<Login> optionalLogin = loginRepository.findByUserNameAndPassword(uname,password);
        System.out.println(optionalLogin.isPresent());
        if(optionalLogin.isPresent())
            response = true;
        else
            response = false;
        return response;*/
        return true;
    }
}