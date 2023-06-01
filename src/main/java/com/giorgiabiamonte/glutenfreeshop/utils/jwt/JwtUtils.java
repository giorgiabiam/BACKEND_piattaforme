package com.giorgiabiamonte.glutenfreeshop.utils.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Component
public class JwtUtils {

    @Value("7200000")  // durata token in millisecondi
    private int jwtExpiration;
    @Value("SecretKey")
    private String secret;

    private Algorithm algorithm;
    private JWTVerifier verifier;

    @PostConstruct
    public void init(){
        this.algorithm =  Algorithm.HMAC256(secret);
        this.verifier = JWT.require(algorithm).build();
    }

    public String generateToken(String username, String ruolo){
        System.out.println("NEL GENERATE TOKEN "+username);
        return  JWT.create()
                .withIssuedAt(new Date())
                .withExpiresAt(Date.from(Instant.now().plus(jwtExpiration, ChronoUnit.MILLIS)))
                .withClaim("username", username)
                .withClaim("ruolo", ruolo)
                .sign(Algorithm.HMAC256(secret));
    }

    public boolean validateToken(String token){
        System.out.println("NEL VALIDATE TOKEN "+ token);
        try {
            DecodedJWT decodedJWT = verifier.verify(token);
            if (!(decodedJWT.getExpiresAt().after(new Date()))){
                System.out.println("token non valido");
                return false;
            }
            System.out.println("token valido");
            return true;
        } catch (JWTVerificationException e) {
            System.out.println("Invalid JWT signature: " + e.getMessage());
        }
        return false;
    }

    public String extractUsernameFromToken(String token) {
        DecodedJWT decodedJWT = verifier.verify(token);
        return decodedJWT.getClaim("username").asString();
    }


    public String extractRoleFromToken(String token){
        DecodedJWT decodedJWT = verifier.verify(token);
        return decodedJWT.getClaim("ruolo").asString();
    }

}
