package com.achalugo.auction_service.data.repositories;

import com.achalugo.auction_service.data.models.Auction;
import com.achalugo.auction_service.data.models.Status;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface AuctionRepository extends MongoRepository<Auction, String> {
    List<Auction> findByStatus(Status status);


    List<Auction> findByStartTimeBefore(LocalDateTime startTimeBefore);
}

