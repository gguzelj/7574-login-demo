package com.example.login.repository;

import com.example.login.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    List<User> findAll();

    Optional<User> findByEmail(String email);

    User save(User user);

}
