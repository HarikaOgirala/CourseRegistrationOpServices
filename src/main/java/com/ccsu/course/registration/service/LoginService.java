package com.ccsu.course.registration.service;

import com.ccsu.course.registration.exception.ResourceNotFoundException;
import com.ccsu.course.registration.repository.LoginRepository;
import com.ccsu.course.registration.model.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

@Service
public class LoginService {

    Boolean response = false;

    @Autowired
    private LoginRepository loginRepository;

    public Optional<Login> getUserDetails(String uname) throws ResourceNotFoundException {
       return loginRepository.findByUserName(uname);
    }


    public void updateResetPasswordToken(String token, String email) throws ResourceNotFoundException {
        Login login = loginRepository.findByEmail(email);
        if (login != null) {
            login.setResetPasswordToken(token);
            loginRepository.save(login);
        } else {
            throw new ResourceNotFoundException("Could not find any account with this email " + email);
        }
    }

    public Login getByResetPasswordToken(String token) {
        return loginRepository.findByResetPasswordToken(token);
    }

    public void updatePassword(Login login, String newPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(newPassword);
        login.setPassword(encodedPassword);

        login.setResetPasswordToken(null);
        loginRepository.save(login);
    }
}