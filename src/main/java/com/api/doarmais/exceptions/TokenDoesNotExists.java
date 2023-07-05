package com.api.doarmais.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TokenDoesNotExists extends RuntimeException {

  public TokenDoesNotExists(String message) {
    super(message);
  }

  public TokenDoesNotExists(String message, Throwable cause) {
    super(message, cause);
  }
}
