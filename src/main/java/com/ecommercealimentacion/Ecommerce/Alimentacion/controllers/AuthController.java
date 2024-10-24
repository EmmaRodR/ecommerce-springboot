package com.ecommercealimentacion.Ecommerce.Alimentacion.controllers;

import com.ecommercealimentacion.Ecommerce.Alimentacion.config.JwtService;
import com.ecommercealimentacion.Ecommerce.Alimentacion.exceptions.especificExceptions.ElementAlreadyExistsException;
import com.ecommercealimentacion.Ecommerce.Alimentacion.exceptions.especificExceptions.PasswordNotVerifyException;
import com.ecommercealimentacion.Ecommerce.Alimentacion.exceptions.especificExceptions.UsernameNotFound;
import com.ecommercealimentacion.Ecommerce.Alimentacion.models.dtos.auth.AuthenticationRequest;
import com.ecommercealimentacion.Ecommerce.Alimentacion.models.dtos.auth.AuthenticationResponse;
import com.ecommercealimentacion.Ecommerce.Alimentacion.models.dtos.auth.RegisterUserRequest;
import com.ecommercealimentacion.Ecommerce.Alimentacion.services.impl.AuthenticationServiceImpl;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/auth")
@Tag(name = "Authorization",description = "Register and Login")
public class AuthController {

    private final AuthenticationServiceImpl authenticationServiceImpl;

    private JwtService jwtService;

    public AuthController(AuthenticationServiceImpl authenticationServiceImpl,JwtService jwtService) {
        this.authenticationServiceImpl = authenticationServiceImpl;
        this.jwtService = jwtService;
    }

    @CrossOrigin(origins = "https://ecommerce-reactfrontend.onrender.com/")
    @PostMapping("/register")
    @Operation(summary = "Register", description = "Regist a user in the app")
    public ResponseEntity<AuthenticationResponse> register (@Valid @RequestBody RegisterUserRequest userRequest) throws ElementAlreadyExistsException, PasswordNotVerifyException {
        return ResponseEntity.created(null).body(authenticationServiceImpl.register(userRequest));

    }
    @CrossOrigin(origins = "https://ecommerce-reactfrontend.onrender.com/")
    @PostMapping("/authenticate")
    @Operation(summary = "Login", description = "Login user in the app")
     public ResponseEntity<AuthenticationResponse> authenticate (@RequestBody AuthenticationRequest authenticationRequest) throws UsernameNotFound {
         return ResponseEntity.ok(authenticationServiceImpl.login(authenticationRequest));

    }

    @CrossOrigin(origins = "https://ecommerce-reactfrontend.onrender.com/")
    @GetMapping("/guest-token")
     public ResponseEntity<String> createGuestToken ()   {

        String token = jwtService.generatedGuestToken();

         return ResponseEntity.ok(token);

    }






}
