package com.achalugo.auction_service.exceptions;

public class AuctionNotFoundException extends RuntimeException {
  public AuctionNotFoundException(String message) {
    super(message);
  }
}
