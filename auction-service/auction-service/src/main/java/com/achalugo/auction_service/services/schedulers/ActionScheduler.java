package com.achalugo.auction_service.services.schedulers;

import com.achalugo.auction_service.data.models.Auction;
import com.achalugo.auction_service.dtos.requests.AuctionUpdateStatusRequest;
import com.achalugo.auction_service.services.AuctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ActionScheduler {
    @Autowired
    private AuctionService auctionService;

    @Scheduled(fixedRate = 60000)
    public void updateAuctionStatuses() {

        List<Auction> allAuctions = auctionService.getAllAuctions();
        for (Auction auction : allAuctions) {
            auctionService.updateAuctionStatus(auction.getId());
        }
    }


}
