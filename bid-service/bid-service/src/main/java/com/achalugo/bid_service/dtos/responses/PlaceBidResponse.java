package com.achalugo.bid_service.dtos.Reponses;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class PlaceBidResponse {
    private String bidId;
    private String userId;
    private BigDecimal amount;
    private String timestamp;

}
