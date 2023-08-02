package com.api.doarmais.exceptions;

public class CantDeleteAddress extends RuntimeException {
  public CantDeleteAddress(String message) {
    super(message);
  }

  public CantDeleteAddress(String message, Throwable cause) {
    super(message, cause);
  }
}
