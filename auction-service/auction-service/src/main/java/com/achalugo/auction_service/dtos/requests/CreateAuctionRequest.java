package com.achalugo.auction_service.dtos.requests;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CreateAuctionRequest {
    private String productId;
    private String sellerId;
    private Long startingPrice;
    private LocalDateTime startTime;
    private LocalDateTime endTime;



}
