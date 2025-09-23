package com.bankomat.bankomat.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.validation.FieldError;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    
    // Обрабатываем ошибки валидации
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        
        ErrorResponse error = new ErrorResponse("Ошибка валидации: " + errors.toString(), "VALIDATION_ERROR");
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
    
    // Обрабатываем ошибки, когда пользователь не найден
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserNotFound(UserNotFoundException ex) {
        ErrorResponse error = new ErrorResponse("Пользователь не найден", "USER_NOT_FOUND");
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

     // Обрабатываем ошибки, когда аккаунт не найден
     @ExceptionHandler(AccountNotFoundException.class)
     public ResponseEntity<ErrorResponse> handleAccountNotFound(AccountNotFoundException ex) {
         ErrorResponse error = new ErrorResponse("Аккаунт не найден", "ACCAUNT_NOT_FOUND");
         return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
     }
    
    // Обрабатываем ошибки, когда недостаточно денег
    @ExceptionHandler(InsufficientFundsException.class)
    public ResponseEntity<ErrorResponse> handleInsufficientFunds(InsufficientFundsException ex) {
        ErrorResponse error = new ErrorResponse("Недостаточно средств", "INSUFFICIENT_FUNDS");
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
    
    // Обрабатываем ВСЕ остальные ошибки
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleAllExceptions(Exception ex) {
        ErrorResponse error = new ErrorResponse("Произошла ошибка", "INTERNAL_ERROR");
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}