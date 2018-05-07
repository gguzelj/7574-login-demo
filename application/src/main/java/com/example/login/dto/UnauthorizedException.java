package com.example.login.dto;

public class UnauthorizedException extends RuntimeException {

    public UnauthorizedException() {}

    public UnauthorizedException(String message) {
        super(message);
    }
}