package com.achalugo.product_service.exceptions;

public class DuplicateProductException extends RuntimeException {
  public DuplicateProductException(String message) {
    super(message);
  }
}
