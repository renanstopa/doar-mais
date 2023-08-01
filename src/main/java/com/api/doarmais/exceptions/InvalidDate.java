package com.api.doarmais.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class InvalidDate extends RuntimeException {

  public InvalidDate(String message) {
    super(message);
  }

  public InvalidDate(String message, Throwable cause) {
    super(message, cause);
  }
}
