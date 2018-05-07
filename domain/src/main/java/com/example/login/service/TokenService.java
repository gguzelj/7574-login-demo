package com.example.login.service;

import com.example.login.domain.Token;
import com.example.login.domain.User;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class TokenService {

    private static final long SECONDS_TO_EXPIRE = 3600;

    @Value("${demo.login.secret}")
    private String SECRET;

    public Token generateToken(User user) {
        final Map<String, Object> claims = new HashMap<>();
        final Date expiration = new Date(System.currentTimeMillis() + SECONDS_TO_EXPIRE * 1000);

        claims.put("id", user.getId());
        claims.put("email", user.getEmail());
        claims.put("iat", new Date());

        return new Token(Jwts.builder()
                .setClaims(claims)
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS256, SECRET)
                .compact());
    }

    public Boolean isValid(Token token) {
        try {
            Jwts.parser().setSigningKey(SECRET).parse(token.getValue());
        } catch (ExpiredJwtException | MalformedJwtException | SignatureException | IllegalArgumentException e) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }
}
