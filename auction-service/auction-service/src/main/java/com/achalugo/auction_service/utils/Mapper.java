package com.achalugo.auction_service.utils;

import com.achalugo.auction_service.data.models.Auction;
import com.achalugo.auction_service.data.models.Status;
import com.achalugo.auction_service.dtos.requests.CreateAuctionRequest;
import com.achalugo.auction_service.dtos.responses.CreateAuctionResponse;
import com.achalugo.auction_service.dtos.responses.CurrentHighestBidResponse;
import com.achalugo.auction_service.dtos.responses.ProductDetailsResponse;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Mapper {

    public static Auction map(CreateAuctionRequest createAuctionRequest) {

        Auction auction = new Auction();
        auction.setProductId(createAuctionRequest.getProductId());
        auction.setStatus(Status.UPCOMING);
        auction.setStartingPrice(BigDecimal.valueOf(createAuctionRequest.getStartingPrice()));
        auction.setStartTime(createAuctionRequest.getStartTime());
        auction.setEndTime(createAuctionRequest.getEndTime());
        return  auction;
    }

    public static CreateAuctionResponse map(Auction savedAuction) {
        CreateAuctionResponse createAuctionResponse = new CreateAuctionResponse();
        createAuctionResponse.setAuctionId(savedAuction.getId());
        createAuctionResponse.setAuctionStartingPrice(savedAuction.getStartingPrice());
        createAuctionResponse.setAuctionEndTime(savedAuction.getEndTime());
        createAuctionResponse.setAuctionStartTime(savedAuction.getStartTime());
        return createAuctionResponse;

    }

    public static ProductDetailsResponse map(String productName, String sellerId) {
        ProductDetailsResponse productDetailsResponse = new ProductDetailsResponse();
        productDetailsResponse.setProductName(productName);
        productDetailsResponse.setSellerId(sellerId);
        return productDetailsResponse;

    }

    public static CurrentHighestBidResponse map(BigDecimal currentHighestBid, Auction foundAuction) {
        CurrentHighestBidResponse currentHighestBidResponse = new CurrentHighestBidResponse();
        currentHighestBidResponse.setCurrentHighestBid(currentHighestBid);
        currentHighestBidResponse.setAuction(foundAuction);
        return currentHighestBidResponse;
    }

}
