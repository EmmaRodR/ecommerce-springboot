package com.ecommercealimentacion.Ecommerce.Alimentacion.config;

import com.ecommercealimentacion.Ecommerce.Alimentacion.models.entities.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService {

    @Value("${security.jwt.expiration-minutes}")
    private Long EXPIRATION_MINUTES;


    @Value("${security.jwt.secret-key}")
    private String SECRET_KEY;


    public String generateToken (User user, Map<String,Object> extraClaims) {


        Date issuedAt = new Date(System.currentTimeMillis());
        Date expiration = new Date(issuedAt.getTime() + (EXPIRATION_MINUTES * 60 * 1000));


        return Jwts.builder()
                .claims(extraClaims)
                .subject(user.getUsername())
                .issuedAt(issuedAt)
                .expiration(expiration)
                .signWith(generateKey())
                .compact();

    }

    public String generatedGuestToken() {

        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("role", "guest");

        Date issuedAt = new Date(System.currentTimeMillis());
        Date expiration = new Date(issuedAt.getTime() + (EXPIRATION_MINUTES * 60 * 1000));

        return Jwts.builder()
                .claims(extraClaims)
                .subject("guest_" + generateGuestId())
                .issuedAt(issuedAt)
                .expiration(expiration)
                .signWith(generateKey())
                .compact();
    }

    private String generateGuestId() {
        return String.valueOf(System.currentTimeMillis()); // Un simple ID de invitado temporal
    }

    public SecretKey generateKey () {
        byte[] secreateAsBytes = Decoders.BASE64.decode(SECRET_KEY);

        return Keys.hmacShaKeyFor(secreateAsBytes);

    }

    public String extractUsername(String jwt) {

        return extractAllClaims(jwt).getSubject();
    }

    private Claims extractAllClaims(String jwt) {
        return Jwts.parser()
                .verifyWith(generateKey())
                .build()
                .parseSignedClaims(jwt).getPayload();
    }

    public boolean isTokenExpired (String token) {
        Date expirationDate = extractExpiration(token);
        return expirationDate.before(new Date());
    }

    public Date extractExpiration(String token) {
        return extractAllClaims(token).getExpiration();
    }

    public boolean isTokenValid(String token, User user) {

        try {
            Claims claims = extractAllClaims(token);

            String usernameInToken = claims.getSubject();

            return usernameInToken.equals(user.getUsername()) && !isTokenExpired(token);

        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }



}
