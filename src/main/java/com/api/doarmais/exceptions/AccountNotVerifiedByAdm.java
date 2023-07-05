package com.api.doarmais.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class AccountNotVerifiedByAdm extends RuntimeException {

  public AccountNotVerifiedByAdm(String message) {
    super(message);
  }

  public AccountNotVerifiedByAdm(String message, Throwable cause) {
    super(message, cause);
  }
}
