package com.achalugo.auction_service.services.schedulers;

import com.achalugo.auction_service.data.models.Auction;
import com.achalugo.auction_service.data.models.Status;
import com.achalugo.auction_service.data.repositories.AuctionRepository;
import com.achalugo.auction_service.dtos.requests.AuctionUpdateStatusRequest;
import com.achalugo.auction_service.services.AuctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class ActionScheduler {
    @Autowired
    private AuctionService auctionService;

    @Autowired
    private AuctionRepository auctionRepository;

    @Scheduled(fixedRate = 60000)
    public void updateAuctionStatuses() {

        List<Auction> allAuctions = auctionService.getAllAuctions();
        for (Auction auction : allAuctions) {
            auctionService.updateAuctionStatus(auction.getId());
        }
    }

    @Scheduled(fixedRate = 60000)
    public void processEndedAuctions() {
        List<Auction> allEndedAuctions = auctionRepository.findByStatus(Status.ENDED);
        List<Auction> endedAuctionsWithoutWinner = new ArrayList<>();
        for (Auction auction : allEndedAuctions) {
            if (auction.getWinnerId() == null)
                endedAuctionsWithoutWinner.add(auction);
        }
        for (Auction auction : endedAuctionsWithoutWinner) {
            auctionService.determineWinner(auction.getId());
            auctionService.publishAuctionEnded(auction.getId());
        }

    }

    @Scheduled(fixedRate = 60000 )
    public void notifyAuctionsStartingSoon() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime soon = now.plusMinutes(10);


        List<Auction> allUpcomingAuctions = auctionRepository.findByStatus(Status.UPCOMING);
        List<Auction> upcomingAuctionsInTenMins = new ArrayList<>();
        for (Auction auction : allUpcomingAuctions) {
            if (auction.getStartTime().isAfter(now) && auction.getStartTime().isBefore(soon))
                upcomingAuctionsInTenMins.add(auction);
        }
        for (Auction auction : upcomingAuctionsInTenMins) {
            auctionService.publishAuctionStartingSoon(auction.getId());
        }


        

    }


}
