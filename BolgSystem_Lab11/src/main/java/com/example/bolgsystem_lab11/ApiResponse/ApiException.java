package com.example.bolgsystem_lab11.ApiResponse;

public class ApiException extends RuntimeException {
    public ApiException(String message) {
        super(message);
    }
}