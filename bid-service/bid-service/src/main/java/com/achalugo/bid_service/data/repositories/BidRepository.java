package com.achalugo.bid_service.data.repositories;

import com.achalugo.bid_service.data.models.Bid;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface BidRepository extends MongoRepository<Bid, String> {
    List<Bid> findBidsByProductId(String productId);
}
