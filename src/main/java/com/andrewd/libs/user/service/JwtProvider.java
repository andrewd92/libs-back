package com.andrewd.libs.user.service;

import java.util.Date;

import com.andrewd.libs.user.repository.UserRepository;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtProvider {

    private final UserRepository userRepository;

    @Value("${bsn.app.jwtSecret}")
    private String jwtSecret;

    @Value("${bsn.app.jwtExpiration}")
    private Integer jwtExpiration;

    public String generateJwtToken(String username) {
        Date date = new Date();

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(date)
                .setExpiration(new Date(date.getTime() + jwtExpiration * 1000))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (JwtException e) {
            log.error(String.format("Invalid JWT token -> Message: %s", e.getMessage()), e);
        }

        return false;
    }

    String getUserNameFromJwtToken(String token) {
        return Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody().getSubject();
    }
}
