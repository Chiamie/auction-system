package com.achalugo.bid_service.services;

import com.achalugo.bid_service.dtos.Reponses.PlaceBidResponse;
import com.achalugo.bid_service.dtos.Requests.PlaceBidRequest;

import java.math.BigDecimal;

public interface BidService {
    PlaceBidResponse placeBid(PlaceBidRequest placeBidRequest);

    BigDecimal getCurrentHighestBid(String productId);




}
