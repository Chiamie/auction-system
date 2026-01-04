package com.achalugo.auction_service.dtos.requests;

import com.achalugo.auction_service.data.models.Status;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class CreateAuctionRequest {
    private String productId;
    private String sellerId;
    private Long startingPrice;
    private LocalDateTime startTime;
    private LocalDateTime endTime;



}
