package com.api.doarmais.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EmailAccountNotVerified extends RuntimeException {

  public EmailAccountNotVerified(String message) {
    super(message);
  }

  public EmailAccountNotVerified(String message, Throwable cause) {
    super(message, cause);
  }
}
