package com.achalugo.bid_service.exceptions;

public class AuctionNotFoundException extends RuntimeException {
  public AuctionNotFoundException(String message) {
    super(message);
  }
}
