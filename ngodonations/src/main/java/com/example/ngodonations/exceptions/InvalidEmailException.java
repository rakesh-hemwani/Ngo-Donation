package com.example.ngodonations.exceptions;

public class InvalidEmailException extends RuntimeException {

    public InvalidEmailException(String message, Throwable cause) {
        super(message, cause);
    }
}
