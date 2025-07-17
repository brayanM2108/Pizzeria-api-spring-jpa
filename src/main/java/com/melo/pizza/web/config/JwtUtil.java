package com.melo.pizza.web.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
public class JwtUtil {

    private static final String SECRET = "M3LO_P1ZZ4"; // Placeholder for actual secret key, should be stored securely
    private static final Algorithm ALGORITHM = Algorithm.HMAC256(SECRET);

    public String createToken(String username) {
        // Logic to create JWT token
        return JWT.create()
                .withSubject(username)
                .withIssuer("melo-pizza")
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + TimeUnit.DAYS.toMillis(15)))
                .sign(ALGORITHM); // Placeholder for actual token generation logic
    }

    public boolean isValid(String jwt){

        try {
            JWT.require(ALGORITHM).build().verify(jwt);
            return true;
        }catch (JWTVerificationException e){
            return false;
        }

    }

    public String getUsername(String jwt){
        return JWT.require(ALGORITHM).build().verify(jwt).getSubject();
    }
}
