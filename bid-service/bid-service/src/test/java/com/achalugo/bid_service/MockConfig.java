package com.achalugo.bid_service;

import com.achalugo.bid_service.dtos.Reponses.ProductResponse;
import com.achalugo.bid_service.services.ProductServiceClient;
import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.time.LocalDateTime;





@TestConfiguration
class MockConfig {

        @Bean
        public ProductServiceClient productServiceClient() {
            ProductServiceClient mock = Mockito.mock(ProductServiceClient.class);

            ProductResponse product = new ProductResponse();
            product.setId("product123");
            product.setCurrentBid(BigDecimal.valueOf(1000));
            product.setStartingPrice(BigDecimal.valueOf(1000));
            product.setStartTime(LocalDateTime.now().minusMinutes(10));
            product.setEndTime(LocalDateTime.now().plusMinutes(10));

            Mockito.when(mock.getProductById("product123")).thenReturn(product);

            return mock;
        }



}
