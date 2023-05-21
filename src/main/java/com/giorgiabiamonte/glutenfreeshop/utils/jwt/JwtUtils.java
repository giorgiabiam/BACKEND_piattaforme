package com.giorgiabiamonte.glutenfreeshop.utils.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Component
public class JwtUtils {

    @Value("7200000")  // TODO durata token in millisecondi
    private int jwtExpiration;
    @Value("SecretKey")
    private String secret;

    private JWTVerifier verifier;

    public String generateToken(String username){
        System.out.println("sono in jwt utils");

        return  JWT.create()
                .withIssuedAt(new Date())
                .withExpiresAt(Date.from(Instant.now().plus(jwtExpiration, ChronoUnit.MILLIS)))
                .withClaim("username", username)
                .sign(Algorithm.HMAC256(secret));
    }

    public boolean validateToken(String token){
        try {
            DecodedJWT decodedJWT = verifier.verify(token);
            if (!(decodedJWT.getExpiresAt().after(new Date())))
                return false;
            return true;
        } catch (JWTVerificationException e) {
            System.out.println("Invalid JWT signature: " + e.getMessage());
        }
        return false;
    }

    public String extractUsernameFromToken(String tokenReq) {
        //TODO
        return "";
//        DecodedJWT decodedJWT = verifier.verify(token);
//        return decodedJWT.getClaim("userId").asString();
    }
}
