package com.example.login.controllers;


import com.example.login.domain.User;
import com.example.login.dto.UserDto;
import com.example.login.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("users")
public class UserController {

    private final UserService service;
    private final DefaultConversionService conversionService;

    @Autowired
    public UserController(UserService service, DefaultConversionService defaultConversionService) {
        this.service = service;
        this.conversionService = defaultConversionService;
    }

    @GetMapping
    @ResponseBody
    public List<UserDto> getUsers() {
        return this.service.findAllUsers().stream()
                .map(this::convert)
                .collect(toList());
    }

    @PostMapping
    public UserDto createUser(@RequestBody UserDto userDto) {
        return convert(this.service.save(convert(userDto)));
    }

    private User convert(UserDto userDto) {
        return this.conversionService.convert(userDto, User.class);
    }

    private UserDto convert(User user) {
        return this.conversionService.convert(user, UserDto.class);
    }

}
