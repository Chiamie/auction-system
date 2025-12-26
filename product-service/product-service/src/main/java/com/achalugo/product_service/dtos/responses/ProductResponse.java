package com.achalugo.product_service.dtos.responses;

import com.achalugo.product_service.models.Category;
import com.achalugo.product_service.models.Location;
import com.achalugo.product_service.models.Status;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class ProductResponse {
    private String id;
    private String name;
    private String description;
    private BigDecimal startingPrice;
    private BigDecimal currentBid;
    private Category category;
    private Status status;
    private Location location;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
