package com.api.doarmais.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CantConfirmProposta extends RuntimeException {

  public CantConfirmProposta(String message) {
    super(message);
  }

  public CantConfirmProposta(String message, Throwable cause) {
    super(message, cause);
  }
}
