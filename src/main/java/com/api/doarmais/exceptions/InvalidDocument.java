package com.api.doarmais.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class InvalidDocument extends RuntimeException {

  public InvalidDocument(String message) {
    super(message);
  }

  public InvalidDocument(String message, Throwable cause) {
    super(message, cause);
  }
}
