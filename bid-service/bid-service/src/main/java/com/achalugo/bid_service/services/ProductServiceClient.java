package com.achalugo.bid_service.services;

import com.achalugo.bid_service.dtos.Reponses.ProductResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;


@FeignClient(name = "product-service", url = "http://localhost:8080")
public interface ProductServiceClient {
    @GetMapping("/{productId}")
    @ResponseStatus(HttpStatus.OK)
    ProductResponse getProductById(@PathVariable String productId);

    @PatchMapping("/{productId}/highest-bid")
    ResponseEntity<?> updateCurrentBid(@PathVariable String productId, BigDecimal newHighestBid);

}
