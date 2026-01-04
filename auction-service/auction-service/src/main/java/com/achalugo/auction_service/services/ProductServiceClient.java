package com.achalugo.auction_service.services;

import com.achalugo.auction_service.dtos.responses.ProductResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;

@FeignClient(name = "product-service", url = "http://localhost:8080")
public interface ProductServiceClient {
    @GetMapping("/{productId}")
    @ResponseStatus(HttpStatus.OK)
    ProductResponse getProductById(@PathVariable String productId);
}
