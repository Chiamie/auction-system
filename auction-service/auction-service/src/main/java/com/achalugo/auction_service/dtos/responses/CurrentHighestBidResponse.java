package com.achalugo.auction_service.dtos.responses;


import com.achalugo.auction_service.data.models.Auction;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CurrentHighestBidResponse {
    private BigDecimal currentHighestBid;
    private Auction auction;
}
