package com.achalugo.product_service.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Document("Products")
@CompoundIndex(name = "unique_value_per_seller", def = "{'sellerId': 1, 'name': 1, 'description': 1}", unique = true)
@Data
public class Product {
    @Id
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
    private String sellerId;

}
