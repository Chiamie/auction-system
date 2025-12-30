package com.achalugo.auction_service.services;

import com.achalugo.auction_service.dtos.responses.HighestBidResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;



@FeignClient(name = "bid-service", url = "http://localhost:8080")
public interface BidServiceClient {
    @GetMapping("/bids/highest/{auctionId}")
    @ResponseStatus(HttpStatus.OK)
    HighestBidResponse getHighestBid(@PathVariable String auctionId);

}
