package com.example.Student_Course.Management.System.exception;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiError> handleNotFound(ResourceNotFoundException ex, HttpServletRequest req){
        ApiError err = new ApiError();
        err.status = HttpStatus.NOT_FOUND.value();
        err.error = HttpStatus.NOT_FOUND.getReasonPhrase();
        err.message = ex.getMessage();
        err.path = req.getRequestURI();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleValidation(MethodArgumentNotValidException ex, HttpServletRequest req){
        ApiError err = new ApiError();
        err.status = HttpStatus.BAD_REQUEST.value();
        err.error = HttpStatus.BAD_REQUEST.getReasonPhrase();
        err.message = "Validation failed";
        err.path = req.getRequestURI();
        err.details = ex.getBindingResult().getFieldErrors()
                .stream().map(f -> f.getField() + ": " + f.getDefaultMessage()).collect(Collectors.toList());
        return ResponseEntity.badRequest().body(err);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleOther(Exception ex, HttpServletRequest req){
        ApiError err = new ApiError();
        err.status = HttpStatus.INTERNAL_SERVER_ERROR.value();
        err.error = HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase();
        err.message = ex.getMessage();
        err.path = req.getRequestURI();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(err);
    }
}
