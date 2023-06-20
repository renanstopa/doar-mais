package com.api.doarmais.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class LinkAlreadyUsed extends RuntimeException{

    public LinkAlreadyUsed(String message) {
        super(message);
    }
    public LinkAlreadyUsed(String message, Throwable cause) {
        super(message, cause);
    }

}
