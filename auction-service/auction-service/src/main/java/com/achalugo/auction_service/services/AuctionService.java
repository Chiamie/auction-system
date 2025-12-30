package com.achalugo.auction_service.services;

import com.achalugo.auction_service.data.models.Auction;
import com.achalugo.auction_service.dtos.responses.AuctionUpdateStatusResponse;
import com.achalugo.auction_service.dtos.responses.WinnerResponse;

import java.math.BigDecimal;
import java.util.List;

public interface AuctionService {

    AuctionUpdateStatusResponse updateAuctionStatus(String auctionId);
    void updateCurrentBid(String auctionId, BigDecimal amount);
    List<Auction> getAllAuctions();
    WinnerResponse determineWinner(String auctionId);


}
