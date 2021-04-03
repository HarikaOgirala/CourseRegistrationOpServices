package com.registration.springboot.service;

import com.registration.springboot.model.Login;
import com.registration.springboot.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.Optional;

@Service
public class LoginService {

    Boolean response = false;

    @Autowired
    private LoginRepository loginRepository;
    private Login login;

    public Boolean authenticateUser(String uname, String password) throws IOException {
        Optional<Login> optionalLogin = loginRepository.findByUserNameAndPassword(uname,password);
        System.out.println(optionalLogin.isPresent());
        if(optionalLogin.isPresent())
            response = true;
        else
            response = false;
        return response;
    }
}