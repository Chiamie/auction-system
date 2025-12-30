package com.achalugo.auction_service.services;

import com.achalugo.auction_service.data.models.Auction;
import com.achalugo.auction_service.data.models.Status;
import com.achalugo.auction_service.data.repositories.AuctionRepository;
import com.achalugo.auction_service.dtos.responses.AuctionUpdateStatusResponse;
import com.achalugo.auction_service.dtos.responses.WinnerResponse;
import com.achalugo.auction_service.exceptions.AuctionNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AuctionServiceImpl implements AuctionService {

    @Autowired
    AuctionRepository  auctionRepository;

    public AuctionUpdateStatusResponse updateAuctionStatus(String auctionId) {
        Auction auction = getAuction(auctionId);
        Status currentAuctionStatus = getAuctionStatus(auction);
        if(auction.getStatus() != currentAuctionStatus){
           auction.setStatus(currentAuctionStatus);
           auctionRepository.save(auction);
       }
       return new AuctionUpdateStatusResponse();
    }

    public void updateCurrentBid(String auctionId, BigDecimal amount){
        Auction foundAuction = getAuction(auctionId);
        if(amount.compareTo(foundAuction.getCurrentBid()) > 0){
            foundAuction.setCurrentBid(amount);
            auctionRepository.save(foundAuction);
        }
    }

    public List<Auction> getAllAuctions(){
        return auctionRepository.findAll();
    }

    public WinnerResponse determineWinner(String auctionId){
        Auction auction = getAuction(auctionId);
        if(!auction.getStatus().equals(Status.ENDED))
            throw new IllegalStateException("Auction has not ended");
        

    }





    private Auction getAuction(String auctionId) {
        Optional<Auction> foundAuction = auctionRepository.findById(auctionId);
        if(foundAuction.isEmpty()){
            throw new AuctionNotFoundException("Auction not found");
        }
        return foundAuction.get();
    }

    private Status getAuctionStatus(Auction auction){
        LocalDateTime currentTime = LocalDateTime.now();
        if(currentTime.isBefore(auction.getStartTime())) return Status.UPCOMING;
        else if(currentTime.isAfter(auction.getEndTime())) return Status.ENDED;
        else return Status.ONGOING;
    }
}
