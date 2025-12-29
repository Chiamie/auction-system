package com.achalugo.bid_service.data.models;

import lombok.Data;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Document("bids")
@Data
public class Bid {
    @Id
    private String id;
    private String bidderId;
    private String productId;
    private BigDecimal amount;
    private LocalDateTime timestamp = LocalDateTime.now();
    private boolean winningBid;
}
