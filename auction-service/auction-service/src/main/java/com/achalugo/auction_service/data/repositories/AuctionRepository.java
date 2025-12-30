package com.achalugo.auction_service.data.repositories;

import com.achalugo.auction_service.data.models.Auction;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AuctionRepository extends MongoRepository<Auction, String> {
}
