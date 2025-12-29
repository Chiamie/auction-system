package com.achalugo.bid_service.services;

import com.achalugo.bid_service.dtos.Reponses.PlaceBidResponse;
import com.achalugo.bid_service.dtos.Requests.PlaceBidRequest;

public interface BidService {
    PlaceBidResponse placeBid(PlaceBidRequest placeBidRequest);
}
