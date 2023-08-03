package com.api.doarmais.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class WrongDate extends RuntimeException {

  public WrongDate(String message) {
    super(message);
  }

  public WrongDate(String message, Throwable cause) {
    super(message, cause);
  }
}
