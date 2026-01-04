package com.achalugo.auction_service.services;


import com.achalugo.auction_service.dtos.requests.CreateAuctionRequest;
import com.achalugo.sharedevents.events.AuctionEndedEvent;

import com.achalugo.auction_service.data.models.Auction;
import com.achalugo.auction_service.data.models.Status;
import com.achalugo.auction_service.data.repositories.AuctionRepository;
import com.achalugo.auction_service.dtos.responses.*;
import com.achalugo.auction_service.exceptions.AuctionNotFoundException;
import com.achalugo.sharedevents.events.AuctionStartingSoonEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static com.achalugo.auction_service.utils.Mapper.map;


@Service
public class AuctionServiceImpl implements AuctionService {

    @Autowired
    private AuctionRepository  auctionRepository;

    @Autowired
    private BidServiceClient  bidServiceClient;

    @Autowired
    private ProductServiceClient  productServiceClient;

    @Autowired
    private ApplicationEventPublisher eventPublisher;



    public CreateAuctionResponse createAuction(CreateAuctionRequest createAuctionRequest){
        Auction auction = map(createAuctionRequest);
        Auction savedAuction = auctionRepository.save(auction);
        return map(savedAuction);
    }


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
        CurrentHighestBidResponse currentHighestBidResponse = getCurrentHighestBid(auctionId);
        Auction auction = currentHighestBidResponse.getAuction();
        if(amount.compareTo(currentHighestBidResponse.getCurrentHighestBid()) > 0){
            auction.setCurrentBid(amount);
            auctionRepository.save(auction);
        }
    }

    private CurrentHighestBidResponse getCurrentHighestBid(String auctionId) {
        Auction foundAuction = getAuction(auctionId);
        BigDecimal currentHighestBid = foundAuction.getCurrentBid() != null ? foundAuction.getCurrentBid() : foundAuction.getStartingPrice();
        return map(currentHighestBid, foundAuction);
    }

    public List<Auction> getAllAuctions(){
        return auctionRepository.findAll();
    }

    public WinnerResponse determineWinner(String auctionId){
        Auction auction = getAuction(auctionId);
        hasEnded(auction);

        HighestBidResponse highestBid = bidServiceClient.getHighestBid(auctionId);
        validate(highestBid);
        auction.setWinnerId(highestBid.getBidderId());
        auctionRepository.save(auction);

        return new WinnerResponse();
    }

    public ProductDetailsResponse determineProductDetails (String auctionId){
        Auction auction = getAuction(auctionId);
        hasEnded(auction);
        ProductResponse product = productServiceClient.getProductById(auction.getProductId());
        String productName = product.getName();
        String sellerId = product.getSellerId();

        return map(productName, sellerId);
    }

    public void publishAuctionEnded(String auctionId){
        Auction auction = getAuction(auctionId);
        String winnerId = auction.getWinnerId();
        ProductDetailsResponse productDetailsResponse = determineProductDetails(auctionId);

        String sellerId = productDetailsResponse.getSellerId();
        String productName = productDetailsResponse.getProductName();

        AuctionEndedEvent event = new AuctionEndedEvent();
        event.setAuctionId(auctionId);
        event.setWinnerId(winnerId);
        event.setSellerId(sellerId);
        event.setProductName(productName);

        eventPublisher.publishEvent(event);
    }

    public void publishAuctionStartingSoon(String auctionId){
        Auction auction = getAuction(auctionId);
        String productId = auction.getProductId();
        ProductDetailsResponse productDetailsResponse = determineProductDetails(auctionId);

        String sellerId = productDetailsResponse.getSellerId();
        String productName = productDetailsResponse.getProductName();

        AuctionStartingSoonEvent event = new AuctionStartingSoonEvent();
        event.setAuctionId(auctionId);
        event.setProductName(productName);
        event.setSellerId(sellerId);

        eventPublisher.publishEvent(event);
    }














    private static void validate(HighestBidResponse highestBid) {
        if (highestBid == null || highestBid.getBidderId() == null) {
            throw new RuntimeException("No bids placed for this auction");
        }
    }

    private static void hasEnded(Auction auction) {
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
