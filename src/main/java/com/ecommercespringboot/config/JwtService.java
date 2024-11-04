package com.ecommercespringboot.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ecommercespringboot.exceptions.especificExceptions.TokenExpiredException;
import com.ecommercespringboot.exceptions.especificExceptions.TokenValidationException;
import com.ecommercespringboot.models.entities.User;

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

    public boolean isTokenValid(String token, User user) throws TokenExpiredException {

        try {
            Claims claims = extractAllClaims(token);
            String usernameInToken = claims.getSubject();

            //Verifica si el token pertenece al usuario y si no expiro.
            return usernameInToken.equals(user.getUsername()) && !isTokenExpired(token);

        } catch (ExpiredJwtException e) {
            throw new TokenExpiredException("Token has expired", e);
        } catch (JwtException e) {
            // Manejo general de excepciones JWT
            throw new TokenValidationException("Error en la validación del token.", e);
        } catch (Exception e) {
            // Manejo de cualquier otra excepción inesperada
            throw new TokenValidationException("Ocurrió un error inesperado al validar el token.", e);
        }
    
}



}
