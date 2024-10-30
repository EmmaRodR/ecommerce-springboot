package com.ecommercealimentacion.Ecommerce.Alimentacion.exceptions;

import com.ecommercealimentacion.Ecommerce.Alimentacion.exceptions.especificExceptions.ElementAlreadyExistsException;
import com.ecommercealimentacion.Ecommerce.Alimentacion.exceptions.especificExceptions.NoElementException;
import com.ecommercealimentacion.Ecommerce.Alimentacion.exceptions.especificExceptions.PasswordNotVerifyException;
import com.ecommercealimentacion.Ecommerce.Alimentacion.exceptions.especificExceptions.TokenExpiredException;
import com.ecommercealimentacion.Ecommerce.Alimentacion.exceptions.especificExceptions.UsernameNotFound;
import com.ecommercealimentacion.Ecommerce.Alimentacion.models.dtos.responses.ErrorResponseDto;

import jakarta.validation.UnexpectedTypeException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ElementAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDto> ElementAlreadyExistsException(ElementAlreadyExistsException ex) {

        ErrorResponseDto response = new ErrorResponseDto(HttpStatus.CONFLICT, ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

    @ExceptionHandler(NoElementException.class)
    public ResponseEntity<ErrorResponseDto> NoElementException(NoElementException ex) {

        ErrorResponseDto response = new ErrorResponseDto(HttpStatus.NOT_FOUND, ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(UsernameNotFound.class)
    public ResponseEntity<ErrorResponseDto> UsernameNotFound(UsernameNotFound ex) {

        ErrorResponseDto response = new ErrorResponseDto(HttpStatus.NOT_FOUND, ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(PasswordNotVerifyException.class)
    public ResponseEntity<ErrorResponseDto> PasswordNotMatch(PasswordNotVerifyException ex) {

        ErrorResponseDto response = new ErrorResponseDto(HttpStatus.CONFLICT, ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

    @ExceptionHandler(TokenExpiredException.class)
    public ResponseEntity<ErrorResponseDto> TokenExpiredException(TokenExpiredException ex) {

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
