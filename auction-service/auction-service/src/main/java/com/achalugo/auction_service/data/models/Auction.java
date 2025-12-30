package com.achalugo.auction_service.data.models;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class Auction {
    @Id
    private String id;
    private String productId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Status status;
    private BigDecimal currentBid;
    private String winnerId;

}

