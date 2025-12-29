package com.achalugo.bid_service.dtos.Reponses;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PlaceBidResponse {
    private String bidId;
    private String userId;
    private BigDecimal amount;

}
