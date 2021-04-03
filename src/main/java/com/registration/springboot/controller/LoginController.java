package com.registration.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.registration.springboot.service.LoginService;

import javax.websocket.server.PathParam;
import java.io.IOException;
import java.util.Map;

;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v2")
public class LoginController {
    @Autowired
    private LoginService loginService;

    @GetMapping("/login")
    public Boolean authenticateUser(Map<String, Object> model, @PathParam("uname") String uname, @PathParam("password") String password) throws IOException {
        System.out.println("username : " + uname + " password : " + password);
        Boolean response = loginService.authenticateUser(uname, password);
        model.put("authenticate", response);
        return response;
    }
}