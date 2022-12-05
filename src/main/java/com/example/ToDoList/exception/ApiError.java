package com.example.ToDoList.exception;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class ApiError {

    private String message;
    private HttpStatus status;
    private LocalDateTime timeStamp;

    public ApiError(String message, HttpStatus notFound, LocalDateTime now) {
    }
}
