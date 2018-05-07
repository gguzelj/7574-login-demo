package com.example.login.service;

import com.example.login.domain.User;
import com.example.login.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Strings.isNullOrEmpty;
import static java.util.Objects.nonNull;

@Service
public class UserService {

    private final Logger LOG = LoggerFactory.getLogger(UserService.class);
    private final UserRepository repository;

    @Autowired
    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public List<User> findAllUsers() {
        return this.repository.findAll();
    }

    public User save(User user) {
        checkArgument(nonNull(user), "User can't be null");
        checkArgument(!isNullOrEmpty(user.getEmail()), "Email can't be null");
        checkArgument(!isNullOrEmpty(user.getPassword()), "Password can't be null");

        Optional<User> byEmail = this.repository.findByEmail(user.getEmail());
        if (byEmail.isPresent()) {
            LOG.error("User with email {} already exists", user.getEmail());
            throw new IllegalArgumentException("User with email " + user.getEmail() + " already exists");
        }

        return this.repository.save(user);
    }
}
