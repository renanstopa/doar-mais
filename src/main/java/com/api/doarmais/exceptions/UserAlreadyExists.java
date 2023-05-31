package com.api.doarmais.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserAlreadyExists extends RuntimeException{

    public UserAlreadyExists(String message) {
        super(message);
    }
    public UserAlreadyExists(String message, Throwable cause) {
        super(message, cause);
    }

}
