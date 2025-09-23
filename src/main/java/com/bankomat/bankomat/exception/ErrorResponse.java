package com.bankomat.bankomat.exception;

public class ErrorResponse {
    private String message;        // Сообщение об ошибке
    private String errorCode;      // Код ошибки
    private String timestamp;      // Время ошибки
    
    // Конструктор
    public ErrorResponse(String message, String errorCode) {
        this.message = message;
        this.errorCode = errorCode;
        this.timestamp = java.time.LocalDateTime.now().toString();
    }
    
    // Геттеры (Spring нужны для создания JSON)
    public String getMessage() { return message; }
    public String getErrorCode() { return errorCode; }
    public String getTimestamp() { return timestamp; }
}