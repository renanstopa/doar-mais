package com.api.doarmais.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResetAlreadyExists extends RuntimeException{

    public ResetAlreadyExists(String message) {
        super(message);
    }
    public ResetAlreadyExists(String message, Throwable cause) {
        super(message, cause);
    }

}
