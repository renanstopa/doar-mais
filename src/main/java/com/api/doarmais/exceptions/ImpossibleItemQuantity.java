package com.api.doarmais.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ImpossibleItemQuantity extends RuntimeException {

  public ImpossibleItemQuantity(String message) {
    super(message);
  }

  public ImpossibleItemQuantity(String message, Throwable cause) {
    super(message, cause);
  }
}
