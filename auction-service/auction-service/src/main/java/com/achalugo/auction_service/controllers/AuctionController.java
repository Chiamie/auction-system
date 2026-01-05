package com.achalugo.auction_service.controllers;


import com.achalugo.auction_service.data.models.Auction;
import com.achalugo.auction_service.dtos.requests.CreateAuctionRequest;
import com.achalugo.auction_service.dtos.responses.AuctionUpdateStatusResponse;
import com.achalugo.auction_service.dtos.responses.CreateAuctionResponse;
import com.achalugo.auction_service.services.AuctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/auction")
public class AuctionController {
    @Autowired
    AuctionService auctionService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateAuctionResponse createAuction(@RequestBody CreateAuctionRequest createAuctionRequest) {
        return auctionService.createAuction(createAuctionRequest);
    }

    @PatchMapping("auctions/{auctionId}/status")
    public AuctionUpdateStatusResponse updateAuctionStatus(@PathVariable String auctionId){
        return auctionService.updateAuctionStatus(auctionId);
    }

    @PostMapping("auctions/{auctionId}/highest-bid")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateCurrentBid(@PathVariable String auctionId, @RequestBody BigDecimal amount){
        auctionService.updateCurrentBid(auctionId, amount);
    }

    @GetMapping("/auctions")
    public List<Auction> getAllAuctions(){
        return auctionService.getAllAuctions();
    }


}
