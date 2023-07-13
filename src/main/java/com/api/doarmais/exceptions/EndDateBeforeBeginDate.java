package com.api.doarmais.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EndDateBeforeBeginDate extends RuntimeException {

  public EndDateBeforeBeginDate(String message) {
    super(message);
  }

  public EndDateBeforeBeginDate(String message, Throwable cause) {
    super(message, cause);
  }
}
