package com.achalugo.bid_service.dtos.Reponses;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class GetBidResponse {
    private String bidId;
    private String bidderId;
    private String productId;
    private BigDecimal amount;
    private String timestamp;
}
