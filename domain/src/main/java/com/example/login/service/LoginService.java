package com.example.login.service;

import com.example.login.domain.Token;
import com.example.login.domain.User;
import com.example.login.exceptions.BadCredentialsException;
import com.example.login.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Strings.isNullOrEmpty;

@Service
public class LoginService {

    private static final String EXCEPTION_MSG = "Email or password invalid";
    private final UserRepository repository;
    private final TokenService tokenService;

    @Autowired
    public LoginService(UserRepository repository, TokenService tokenService) {
        this.repository = repository;
        this.tokenService = tokenService;
    }

    public Token login(String email, String password) throws BadCredentialsException {
        checkArgument(!isNullOrEmpty(email), "email cant be null");
        checkArgument(!isNullOrEmpty(password), "password cant be null");
        final User user = this.findOrFail(email);
        if (!user.isSamePassword(password)) {
            throw new BadCredentialsException(EXCEPTION_MSG);
        }
        return this.tokenService.generateToken(user);
    }

    private User findOrFail(String email) {
        return this.repository.findByEmail(email)
                .orElseThrow(() -> new BadCredentialsException(EXCEPTION_MSG));
    }

}
