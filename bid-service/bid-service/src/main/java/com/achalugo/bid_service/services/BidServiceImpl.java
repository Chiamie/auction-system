package com.achalugo.bid_service.services;

import com.achalugo.bid_service.data.models.Bid;
import com.achalugo.bid_service.data.repositories.BidRepository;
import com.achalugo.bid_service.dtos.Reponses.PlaceBidResponse;
import com.achalugo.bid_service.dtos.Reponses.ProductResponse;
import com.achalugo.bid_service.dtos.Requests.PlaceBidRequest;
import com.achalugo.bid_service.exceptions.InvaildBidAmountException;
import com.achalugo.bid_service.exceptions.ProductNotFoundException;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Objects;

import static com.achalugo.bid_service.utils.Mapper.map;

@Service
public class BidServiceImpl implements BidService {


    @Autowired
    private ProductServiceClient productServiceClient;

    @Autowired
    private BidRepository bidRepository;


    @Override
    public PlaceBidResponse placeBid(PlaceBidRequest placeBidRequest) {
        ProductResponse product = validateProduct(placeBidRequest);
        validateAuction(product);

        BigDecimal currentBid = product.getCurrentBid() != null ? product.getCurrentBid() : product.getStartingPrice();

        if (placeBidRequest.getBidAmount().compareTo(currentBid) <= 0)
            throw new InvaildBidAmountException("Bid must be higher than current Bid");

        Bid bid = new Bid();
        bid.setBidderId(placeBidRequest.getBidderId());
        bid.setProductId(product.getId());
        bid.setAmount(placeBidRequest.getBidAmount());

        productServiceClient.updateCurrentBid(product.getId(), bid.getAmount());

        Bid savedBid = bidRepository.save(bid);

        return  map(savedBid);
    }

    private static void validateAuction(ProductResponse product) {
        if (!Objects.equals(product.getStatus(), "Ongoing"))
            throw new IllegalStateException("Auction is not ongoing");
    }

    private ProductResponse validateProduct(PlaceBidRequest placeBidRequest) {
        try {
            return productServiceClient.getProductById(placeBidRequest.getProductId());
        } catch (FeignException.NotFound e) {
            throw new ProductNotFoundException("Product not found");
        }
    }


}
