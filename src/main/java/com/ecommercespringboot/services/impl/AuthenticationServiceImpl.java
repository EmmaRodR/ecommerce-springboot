package com.ecommercespringboot.services.impl;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ecommercespringboot.config.JwtService;
import com.ecommercespringboot.exceptions.especificExceptions.ElementAlreadyExistsException;
import com.ecommercespringboot.exceptions.especificExceptions.PasswordNotVerifyException;
import com.ecommercespringboot.exceptions.especificExceptions.UsernameNotFound;
import com.ecommercespringboot.models.dtos.auth.AuthenticationRequest;
import com.ecommercespringboot.models.dtos.auth.AuthenticationResponse;
import com.ecommercespringboot.models.dtos.auth.RegisterUserRequest;
import com.ecommercespringboot.models.entities.User;
import com.ecommercespringboot.repositories.UserRepository;
import com.ecommercespringboot.services.IAuthService;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthenticationServiceImpl implements IAuthService {

    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private JwtService jwtService;
    private PasswordEncoder passwordEncoder;

    public AuthenticationServiceImpl(AuthenticationManager authenticationManager, UserRepository userRepository,
            JwtService jwtService, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
    }

    public AuthenticationResponse register(RegisterUserRequest request)
            throws ElementAlreadyExistsException, PasswordNotVerifyException {

        if (userRepository.existsByUsername(request.getUsername())) {
            throw new ElementAlreadyExistsException("User name already exists");
        }

        if (!request.getPassword().toLowerCase().equals(request.getPasswordVerify().toLowerCase())) {
            throw new PasswordNotVerifyException("Passwords not match");
        }

        var user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole());

        userRepository.save(user);
        var userId = userRepository.save(user).getId();

        return new AuthenticationResponse(userId, null, HttpStatus.CREATED.toString(), user.getUsername(),
                user.getEmail(),
                user.getRole());

    }

    public AuthenticationResponse login(AuthenticationRequest authenticationRequest) throws UsernameNotFound {

        if (!userRepository.existsByUsername(authenticationRequest.getUsername())) {
            throw new UsernameNotFound("User not found");
        }

        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                authenticationRequest.getUsername(), authenticationRequest.getPassword());

        authenticationManager.authenticate(authToken);

        User user = userRepository.findByusername(authenticationRequest.getUsername()).get();

        String jwt = jwtService.generateToken(user, generateExtraClaims(user));

        return new AuthenticationResponse(user.getId(), jwt, HttpStatus.OK.toString(), user.getUsername(),
                user.getEmail(), user.getRole());

    }

    private Map<String, Object> generateExtraClaims(User user) {

        Map<String, Object> extraClaims = new HashMap<>();

        extraClaims.put("name", user.getUsername());
        extraClaims.put("role", user.getRole());

        return extraClaims;

    }

}
