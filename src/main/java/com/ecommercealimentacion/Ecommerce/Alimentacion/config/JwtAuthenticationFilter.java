package com.ecommercealimentacion.Ecommerce.Alimentacion.config;

import com.ecommercealimentacion.Ecommerce.Alimentacion.models.entities.User;
import com.ecommercealimentacion.Ecommerce.Alimentacion.repositories.UserRepository;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.io.PrintWriter;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private JwtService jwtService;

    private UserRepository userRepository;

    public JwtAuthenticationFilter(JwtService jwtService, UserRepository userRepository) {
        this.jwtService = jwtService;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // obtains header that contains jwt

        String authHeader = request.getHeader("Authorization"); // Bearer jwt

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {

            filterChain.doFilter(request, response);
            return;
        }

        // obtains jwt token
        String jwt = authHeader.split(" ")[1];

        try {

            // obtain subject/username in jwt
            String username = jwtService.extractUsername(jwt);

            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

                // authenticate object insider our security context
                User user = userRepository.findByusername(username).orElse(null);

                if (user != null && jwtService.isTokenValid(jwt, user)) {
                    // Create auth token
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            username, null, user.getAuthorities());

                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }

        } catch (ExpiredJwtException e) {
            handleExpiredToken(response, e);
            return;

        }

        // execute rest of filters
        filterChain.doFilter(request, response);

    }

    public void handleExpiredToken(HttpServletResponse response, ExpiredJwtException e
             ) throws IOException {
        
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");

        String responseBody = "{"
                + "\"statusCode\": 401,"
                + "\"error\": \"TOKEN EXPIRADO\","
                + "\"msg\": \"" + e.getMessage() + "\""
                + "}";

        PrintWriter out = response.getWriter();
        out.write(responseBody);
        out.flush();

    }
}

