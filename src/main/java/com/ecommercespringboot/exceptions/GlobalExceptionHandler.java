package com.ecommercespringboot.exceptions;

import jakarta.validation.UnexpectedTypeException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.ecommercespringboot.exceptions.especificExceptions.PasswordNotVerifyException;
import com.ecommercespringboot.exceptions.especificExceptions.TokenValidationException;
import com.ecommercespringboot.models.dtos.responses.ErrorResponseDto;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(com.ecommercespringboot.exceptions.especificExceptions.ElementAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDto> ElementAlreadyExistsException(com.ecommercespringboot.exceptions.especificExceptions.ElementAlreadyExistsException ex) {

        ErrorResponseDto response = new ErrorResponseDto(HttpStatus.CONFLICT, ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

    @ExceptionHandler(com.ecommercespringboot.exceptions.especificExceptions.NoElementException.class)
    public ResponseEntity<ErrorResponseDto> NoElementException(com.ecommercespringboot.exceptions.especificExceptions.NoElementException ex) {

        ErrorResponseDto response = new ErrorResponseDto(HttpStatus.NOT_FOUND, ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(com.ecommercespringboot.exceptions.especificExceptions.UsernameNotFound.class)
    public ResponseEntity<ErrorResponseDto> UsernameNotFound(com.ecommercespringboot.exceptions.especificExceptions.UsernameNotFound ex) {

        ErrorResponseDto response = new ErrorResponseDto(HttpStatus.NOT_FOUND, ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(PasswordNotVerifyException.class)
    public ResponseEntity<ErrorResponseDto> PasswordNotMatch(PasswordNotVerifyException ex) {

        ErrorResponseDto response = new ErrorResponseDto(HttpStatus.CONFLICT, ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

    @ExceptionHandler(com.ecommercespringboot.exceptions.especificExceptions.TokenExpiredException.class)
    public ResponseEntity<ErrorResponseDto> TokenExpiredException(TokenValidationException ex) {

        ErrorResponseDto response = new ErrorResponseDto(HttpStatus.NON_AUTHORITATIVE_INFORMATION, ex.getMessage());
        return ResponseEntity.status(HttpStatus.NON_AUTHORITATIVE_INFORMATION).body(response);
    }

    @ExceptionHandler(UnexpectedTypeException.class)
    public ResponseEntity<ErrorResponseDto> UnexpectedTypeException(UnexpectedTypeException ex) {

        ErrorResponseDto response = new ErrorResponseDto(HttpStatus.BAD_REQUEST, ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(jakarta.validation.ConstraintViolationException.class)
    public ResponseEntity<ErrorResponseDto> ConstraintViolationException(
            jakarta.validation.ConstraintViolationException ex) {

        ErrorResponseDto response = new ErrorResponseDto(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

}
