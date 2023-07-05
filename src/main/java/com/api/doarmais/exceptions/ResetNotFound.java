package com.api.doarmais.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResetNotFound extends RuntimeException {

  public ResetNotFound(String message) {
    super(message);
  }

  public ResetNotFound(String message, Throwable cause) {
    super(message, cause);
  }
}
