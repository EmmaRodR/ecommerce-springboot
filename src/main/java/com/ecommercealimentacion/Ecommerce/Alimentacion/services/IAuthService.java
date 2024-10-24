package com.ecommercealimentacion.Ecommerce.Alimentacion.services;

import com.ecommercealimentacion.Ecommerce.Alimentacion.exceptions.especificExceptions.ElementAlreadyExistsException;
import com.ecommercealimentacion.Ecommerce.Alimentacion.exceptions.especificExceptions.PasswordNotVerifyException;
import com.ecommercealimentacion.Ecommerce.Alimentacion.exceptions.especificExceptions.UsernameNotFound;
import com.ecommercealimentacion.Ecommerce.Alimentacion.models.dtos.auth.AuthenticationRequest;
import com.ecommercealimentacion.Ecommerce.Alimentacion.models.dtos.auth.AuthenticationResponse;
import com.ecommercealimentacion.Ecommerce.Alimentacion.models.dtos.auth.RegisterUserRequest;

public interface IAuthService {


    AuthenticationResponse login (AuthenticationRequest authenticationRequest) throws UsernameNotFound;

    AuthenticationResponse register (RegisterUserRequest userRequest) throws ElementAlreadyExistsException, PasswordNotVerifyException;





}
