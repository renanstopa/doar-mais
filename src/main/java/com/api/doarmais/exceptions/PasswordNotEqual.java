package com.api.doarmais.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PasswordNotEqual extends RuntimeException {

  public PasswordNotEqual(String message) {
    super(message);
  }

  public PasswordNotEqual(String message, Throwable cause) {
    super(message, cause);
  }
}
