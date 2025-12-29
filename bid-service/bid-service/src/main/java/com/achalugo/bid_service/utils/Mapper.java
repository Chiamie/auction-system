package com.achalugo.bid_service.utils;

import com.achalugo.bid_service.data.models.Bid;
import com.achalugo.bid_service.dtos.Reponses.PlaceBidResponse;

public class Mapper {

    public static PlaceBidResponse map(Bid savedBid){
        PlaceBidResponse placeBidResponse = new PlaceBidResponse();
        placeBidResponse.setBidId(savedBid.getId());
        placeBidResponse.setUserId(savedBid.getBidderId());
        placeBidResponse.setAmount(savedBid.getAmount());
        return placeBidResponse;
    };
}
