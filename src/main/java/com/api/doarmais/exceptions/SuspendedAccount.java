package com.api.doarmais.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class SuspendedAccount extends RuntimeException{

    public SuspendedAccount(String message) {
        super(message);
    }
    public SuspendedAccount(String message, Throwable cause) {
        super(message, cause);
    }

}
