package com.api.doarmais.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class AddressAlreadyExists extends RuntimeException{

    public AddressAlreadyExists(String message) {
        super(message);
    }
    public AddressAlreadyExists(String message, Throwable cause) {
        super(message, cause);
    }

}
