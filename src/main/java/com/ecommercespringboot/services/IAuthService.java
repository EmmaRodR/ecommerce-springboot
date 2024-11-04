package com.ecommercespringboot.services;

import com.ecommercespringboot.exceptions.especificExceptions.ElementAlreadyExistsException;
import com.ecommercespringboot.exceptions.especificExceptions.PasswordNotVerifyException;
import com.ecommercespringboot.exceptions.especificExceptions.UsernameNotFound;
import com.ecommercespringboot.models.dtos.auth.AuthenticationRequest;
import com.ecommercespringboot.models.dtos.auth.AuthenticationResponse;
import com.ecommercespringboot.models.dtos.auth.RegisterUserRequest;

public interface IAuthService {


    AuthenticationResponse login (AuthenticationRequest authenticationRequest) throws UsernameNotFound;

    AuthenticationResponse register (RegisterUserRequest userRequest) throws ElementAlreadyExistsException, PasswordNotVerifyException;





}
