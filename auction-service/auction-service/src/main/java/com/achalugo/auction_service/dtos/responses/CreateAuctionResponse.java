package com.achalugo.auction_service.dtos.responses;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class CreateAuctionResponse {
    private String auctionId;
    private LocalDateTime auctionStartTime;
    private LocalDateTime auctionEndTime;
    private BigDecimal auctionStartingPrice;
}
