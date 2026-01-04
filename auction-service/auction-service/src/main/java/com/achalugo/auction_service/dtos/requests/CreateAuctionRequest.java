package com.achalugo.auction_service.dtos.requests;

import com.achalugo.auction_service.data.models.Status;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class CreateAuctionRequest {
    private String productId;
    private Long startingPrice;
    private String startTime;
    private String endTime;
    private String status;


}
