package com.achalugo.auction_service.dtos.responses;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class ProductResponse {
    private String id;
    private String name;
    private String description;
    private BigDecimal startingPrice;
    private String sellerId;
    private String category;
    private String status;
    private String location;
}
