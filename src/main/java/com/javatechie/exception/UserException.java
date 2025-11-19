package com.javatechie.exception;

import org.springframework.stereotype.Component;

@Component
public class UserException extends Exception {

    public UserException() {
    }

    public UserException(String message) {
        super(message);
    }
}
