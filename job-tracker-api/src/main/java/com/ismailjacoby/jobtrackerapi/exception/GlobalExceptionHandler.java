package com.ismailjacoby.jobtrackerapi.exception;

import com.ismailjacoby.jobtrackerapi.model.dto.ErrorResponseDTO;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    //  AccessDenied Exception
    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ErrorResponseDTO handleAccessDeniedException(AccessDeniedException e) {
        return new ErrorResponseDTO("Forbidden", e.getMessage(), HttpStatus.FORBIDDEN);
    }


    // BadCredentialsException (incorrect username or password)
    @ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorResponseDTO handleBadCredentialsException(BadCredentialsException e) {
        return new ErrorResponseDTO("Authentication failed", e.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    // DuplicateException
    @ExceptionHandler(DuplicateException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponseDTO handleDuplicateException(DuplicateException e) {
        return new ErrorResponseDTO("Duplicate Found", e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    // IllegalArgumentException
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponseDTO handleIllegalArgumentException(IllegalArgumentException e) {
        return new ErrorResponseDTO("Invalid Argument", e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    // MethodArgumentNotValidException (Validations)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponseDTO handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<String> errorMessages = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fieldError -> fieldError.getDefaultMessage())
                .collect(Collectors.toList());

        String message = String.join(", ", errorMessages);

        return new ErrorResponseDTO("Validation failed", message, HttpStatus.BAD_REQUEST);
    }

    // Password mismatch
    @ExceptionHandler(PasswordMismatchException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorResponseDTO handlePasswordMismatchException(PasswordMismatchException e) {
        return new ErrorResponseDTO("Password mismatch", e.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    // NotFoundException
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponseDTO handleNotFoundException(NotFoundException e) {
        return new ErrorResponseDTO("Not Found", e.getMessage(), HttpStatus.NOT_FOUND);
    }
}
