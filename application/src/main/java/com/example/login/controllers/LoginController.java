package com.example.login.controllers;

import com.example.login.domain.Token;
import com.example.login.dto.AuthenticationRequest;
import com.example.login.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("login")
public class LoginController {

    private final LoginService service;

    @Autowired
    public LoginController(LoginService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseBody
    public Token userAuthentication(@RequestBody AuthenticationRequest request) {
        return this.service.login(request.getEmail(), request.getPassword());
    }

}
