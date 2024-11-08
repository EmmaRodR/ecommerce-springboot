package com.ecommercespringboot.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.ecommercespringboot.exceptions.especificExceptions.ElementAlreadyExistsException;
import com.ecommercespringboot.exceptions.especificExceptions.PasswordNotVerifyException;
import com.ecommercespringboot.models.dtos.auth.AuthenticationResponse;
import com.ecommercespringboot.models.dtos.auth.RegisterUserRequest;
import com.ecommercespringboot.models.entities.User;
import com.ecommercespringboot.models.enums.Role;
import com.ecommercespringboot.repositories.UserRepository;
import com.ecommercespringboot.services.impl.AuthenticationServiceImpl;

@ExtendWith(MockitoExtension.class)
public class AuthenticationServiceTests {

  @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private AuthenticationServiceImpl authenticationService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        reset(userRepository); // Esto limpiará las invocaciones previas de los mocks

    }

    @Test
    void shouldThrowExceptionWhenUsernameAlreadyExists() {

        RegisterUserRequest request = new RegisterUserRequest("existingUser", "email@test.com", "password", "password",
                Role.ADMIN);
        when(userRepository.existsByUsername("existingUser")).thenReturn(true);

        assertThrows(ElementAlreadyExistsException.class, () -> authenticationService.register(request));
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void shouldThrowExceptionWhenPasswordsDoNotMatch() {

        RegisterUserRequest request = new RegisterUserRequest("newUser", "email@test.com", "password1", "password2",
                Role.ADMIN);

        when(userRepository.existsByUsername("newUser")).thenReturn(false);

        assertThrows(PasswordNotVerifyException.class, () -> authenticationService.register(request));
        verify(userRepository, never()).save(any(User.class));
    }
    

    @Test
    void shouldRegisterUserSuccessfully () throws ElementAlreadyExistsException, PasswordNotVerifyException {

        RegisterUserRequest request = new RegisterUserRequest("newUser", "email@test.com", "password", "password",
        Role.ADMIN);

        when(userRepository.existsByUsername(request.getUsername())).thenReturn(false);
        when(passwordEncoder.encode("password")).thenReturn("encodedPassword");

        User savedUser = new User();
        savedUser.setId(1L);
        savedUser.setUsername("newUser");
        savedUser.setEmail("email@test.com");
        savedUser.setPassword("encodedPassword");
        savedUser.setRole(Role.CUSTOMER);

        when(userRepository.save(any(User.class))).thenReturn(savedUser);

        AuthenticationResponse response = authenticationService.register(request);

        assertEquals(savedUser.getId(), response.getId());
        assertEquals(savedUser.getUsername(), response.getUsername());
        assertEquals(savedUser.getEmail(), response.getEmail());
        assertEquals(savedUser.getRole(), response.getRole());
        assertEquals(HttpStatus.CREATED.toString(), response.getStatus());
        verify(userRepository, times(1)).save(any(User.class));

    }
}
