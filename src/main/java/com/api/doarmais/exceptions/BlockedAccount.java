package com.api.doarmais.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class BlockedAccount extends RuntimeException{

    public BlockedAccount(String message) {
        super(message);
    }
    public BlockedAccount(String message, Throwable cause) {
        super(message, cause);
    }

}
