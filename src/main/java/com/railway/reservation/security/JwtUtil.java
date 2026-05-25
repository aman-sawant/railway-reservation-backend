package com.railway.reservation.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    private static final String SECRET_KEY =
            "bXlzZWNyZXRrZXlteXNlY3JldGtleTEyMzQ1Njc4OTA=";

    // Generate Token
    public String generateToken(
            String email
    ){

        return Jwts.builder()

                .setSubject(email)

                .setIssuedAt(new Date())

                .setExpiration(
                        new Date(
                                System.currentTimeMillis()
                                        + 1000 * 60 * 60
                        )
                )

                .signWith(
                        getSignKey(),
                        SignatureAlgorithm.HS256
                )

                .compact();

    }

    // Extract All Claims
    public Claims extractClaims(
            String token
    ){

        return Jwts.parserBuilder()

                .setSigningKey(
                        getSignKey()
                )

                .build()

                .parseClaimsJws(token)

                .getBody();

    }

    // Extract Email
    public String extractEmail(String token){

        return extractClaims(token).getSubject();
    }

    // Validate Token
    public boolean validateToken(String token, String email){

        String extractedEmail = extractEmail(token);
        return extractedEmail.equals(email)&&!isTokenExpired(token);

    }

    // Create Signing Key
    private Key getSignKey(){

        byte[] keyBytes =
                Decoders.BASE64.decode(
                        SECRET_KEY
                );

        return Keys.hmacShaKeyFor(
                keyBytes
        );

    }
    public boolean isTokenExpired(
            String token
    ){
        System.out.println(
                extractClaims(token)
                        .getExpiration()
        );
        return extractClaims(token)
                .getExpiration()
                .before(new Date());

    }

}