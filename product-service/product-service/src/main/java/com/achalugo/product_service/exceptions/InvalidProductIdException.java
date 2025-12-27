package com.achalugo.product_service.exceptions;

public class InvalidProductIdException extends RuntimeException {
    public InvalidProductIdException(String message, String productId) {
        super(message);
    }
}
