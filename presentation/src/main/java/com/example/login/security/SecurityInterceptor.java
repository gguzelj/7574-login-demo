package com.example.login.security;


import com.example.login.domain.Token;
import com.example.login.dto.UnauthorizedException;
import com.example.login.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.google.common.base.Preconditions.checkArgument;
import static java.util.Objects.isNull;

@Component
public class SecurityInterceptor extends HandlerInterceptorAdapter {

    private static final String BEARER = "bearer ";

    @Autowired
    private TokenService tokenService;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        final String requestedUri = this.transformUri(request.getRequestURI());
        if (requestedUri.equals("/users")) {
            this.analyzeToken(request.getHeader("Authorization"));
        }
        return super.preHandle(request, response, handler);
    }

    private void analyzeToken(String authorizationToken) {
        if (isNull(authorizationToken)) {
            throw new UnauthorizedException("No Authorization header provided");
        }
        checkArgument(authorizationToken.toLowerCase().startsWith(BEARER), "Invalid Authorization header.");
        authorizationToken = authorizationToken.substring(7);
        if (!this.tokenService.isValid(new Token(authorizationToken))) {
            throw new UnauthorizedException();
        }
    }

    /**
     * Remove queryParams and last slash
     */
    private String transformUri(String requestedURI) {
        //Remove query params
        String response = requestedURI.replaceAll("\\?.*", "");

        //Remove slash
        int lastCharIndex = response.length() - 1;
        return (response.lastIndexOf("/") == lastCharIndex) ? response.substring(0, lastCharIndex) : response;
    }

}

