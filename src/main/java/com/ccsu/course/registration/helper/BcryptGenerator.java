package com.ccsu.course.registration.helper;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BcryptGenerator {

    public static void main(String[] args) {
        String password = "harika";
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(4);
        String hashedPassword = passwordEncoder.encode(password);
        System.out.println(hashedPassword);
    }
}
