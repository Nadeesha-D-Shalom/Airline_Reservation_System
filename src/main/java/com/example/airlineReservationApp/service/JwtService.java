package com.example.airlineReservationApp.service;

import com.example.airlineReservationApp.model.Account;
<<<<<<< HEAD
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
=======
import io.jsonwebtoken.*;
>>>>>>> 4a26394 (Version 1.8.1)
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JwtService {

    private static final SecretKey SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);
<<<<<<< HEAD
    private static final long EXPIRATION_TIME = 1000L * 60 * 60 * 24; // 24h
=======
    private static final long EXPIRATION_TIME = 1000 * 60 * 60 * 24; // 24 hours
>>>>>>> 4a26394 (Version 1.8.1)

    public String generateToken(Account account) {
        return Jwts.builder()
                .setSubject(account.getEmail())
<<<<<<< HEAD
                .claim("role", account.getRole())
=======
                .claim("role", account.getRoleEnum())
>>>>>>> 4a26394 (Version 1.8.1)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SECRET_KEY)
                .compact();
    }

<<<<<<< HEAD
    public String extractEmail(String token) {
=======
    public Claims extractAllClaims(String token) {
>>>>>>> 4a26394 (Version 1.8.1)
        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
<<<<<<< HEAD
                .getBody()
                .getSubject();
    }

    public boolean validateToken(String token, String userEmail) {
        String extracted = extractEmail(token);
        return extracted.equals(userEmail);
=======
                .getBody();
>>>>>>> 4a26394 (Version 1.8.1)
    }

    public String extractRole(String token) {
        return (String) extractAllClaims(token).get("role");
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(SECRET_KEY)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }
}
