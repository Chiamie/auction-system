package com.achalugo.bid_service.dtos.Reponses;

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
        private String category;
        private String status;
        private String location;
        private LocalDateTime startTime;
        private LocalDateTime endTime;
}
