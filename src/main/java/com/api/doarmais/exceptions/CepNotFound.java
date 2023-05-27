package com.api.doarmais.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CepNotFound extends RuntimeException{

    public CepNotFound(String message) {
        super(message);
    }
    public CepNotFound(String message, Throwable cause) {
        super(message, cause);
    }

}
