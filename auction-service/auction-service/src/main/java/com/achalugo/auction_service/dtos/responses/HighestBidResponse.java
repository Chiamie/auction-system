package com.achalugo.auction_service.dtos.responses;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class HighestBidResponse {
    private String bidId;
    private String bidderId;
    private String productId;
    private String auctionId;
    private BigDecimal amount;
    private LocalDateTime timestamp = LocalDateTime.now();
    private boolean winningBid;
}
