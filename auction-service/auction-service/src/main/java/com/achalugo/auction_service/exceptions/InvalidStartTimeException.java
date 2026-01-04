package com.achalugo.auction_service.exceptions;

public class InvalidStartTimeException extends RuntimeException {
    public InvalidStartTimeException(String message) {
        super(message);
    }
}
