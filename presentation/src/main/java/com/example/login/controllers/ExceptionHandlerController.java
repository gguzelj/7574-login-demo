package com.example.login.controllers;

import com.example.login.dto.UnauthorizedException;
import com.example.login.dto.LoginError;
import com.example.login.exceptions.BadCredentialsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExceptionHandlerController {

    private final Logger LOG = LoggerFactory.getLogger(ExceptionHandlerController.class);
	
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<LoginError> handleExceptions(HttpServletRequest req, Exception ex) {
	    LOG.error(ex.getMessage(), ex);
		final LoginError error = new LoginError(HttpStatus.BAD_REQUEST.value(), ex.getMessage(), ex.getClass().getCanonicalName());
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(BadCredentialsException.class)
	public ResponseEntity<LoginError> BadCredentialsException(HttpServletRequest req, Exception ex) {
		final LoginError error = new LoginError(HttpStatus.FORBIDDEN.value(), ex.getMessage(), ex.getClass().getCanonicalName());
		return new ResponseEntity<>(error, HttpStatus.FORBIDDEN);
	}

	@ExceptionHandler(UnauthorizedException.class)
	public ResponseEntity<LoginError> UnauthorizedException(HttpServletRequest req, Exception ex) {
		final LoginError error = new LoginError(HttpStatus.UNAUTHORIZED.value(), ex.getMessage(), ex.getClass().getCanonicalName());
		return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
	}
	
}
