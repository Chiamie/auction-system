package com.achalugo.bid_service.dtos.Requests;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PlaceBidRequest {
    private String bidderId;
    private String productId;
    private BigDecimal bidAmount;
}
