package com.example.bolgsystem_lab11.ApiResponse;

public class ApiResponse {
    private String message;

    // Getter & setter
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ApiResponse(String message) {
        this.message = message;
    }
}