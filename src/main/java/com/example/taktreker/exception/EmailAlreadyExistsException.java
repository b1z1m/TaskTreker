package com.example.taktreker.exception;

public class EmailAlreadyExistsException extends RuntimeException {
    public EmailAlreadyExistsException(String email) {
        super("Пользователь с почтой " + email + " уже существует");
    }
}
