package com.achalugo.bid_service;

import com.achalugo.bid_service.data.models.Bid;
import com.achalugo.bid_service.data.repositories.BidRepository;
import com.achalugo.bid_service.dtos.Reponses.PlaceBidResponse;
import com.achalugo.bid_service.dtos.Reponses.ProductResponse;
import com.achalugo.bid_service.dtos.Requests.PlaceBidRequest;
import com.achalugo.bid_service.services.BidService;
import com.achalugo.bid_service.services.ProductServiceClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class BidServiceApplicationTests {
    @Autowired
    private BidService bidService;

    @MockitoBean
    private ProductServiceClient productServiceClient;

    @MockitoBean
    private BidRepository bidRepository;


    @Test
    void placeBid_countIsOne() {
        PlaceBidRequest  placeBidRequest = new PlaceBidRequest();
        placeBidRequest.setBidAmount(BigDecimal.valueOf(7000));
        placeBidRequest.setBidderId("user1");
        placeBidRequest.setProductId("product1");

        ProductResponse  productResponse = new ProductResponse();
        productResponse.setCurrentBid(BigDecimal.valueOf(5000));
        productResponse.setCategory("ELECTRONICS");
        productResponse.setName("iPhone11");
        productResponse.setDescription("product description");
        productResponse.setLocation("Enugu, Enugu, Nigeria");
        productResponse.setStartingPrice(BigDecimal.valueOf(100));
        productResponse.setStartTime(LocalDateTime.now().minusMinutes(10));
        productResponse.setEndTime(LocalDateTime.now().plusMinutes(10));
        productResponse.setStatus("Ongoing");
        productResponse.setId("product1");

        Mockito.when(productServiceClient.getProductById("product1")).thenReturn(productResponse);
        Mockito.when(bidRepository.save(Mockito.any(Bid.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        PlaceBidResponse actualResponse = bidService.placeBid(placeBidRequest);
        assertEquals(actualResponse.getAmount(), placeBidRequest.getBidAmount());


        Mockito.verify(productServiceClient).updateCurrentBid("product1", BigDecimal.valueOf(7000));
        Mockito.verify(bidRepository).save(Mockito.any(Bid.class));
    }

	@Test
	void contextLoads() {
	}

}




//@SpringBootTest
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
//class BidServiceApplicationTests {
//
//    @Autowired
//    private BidService bidService;
//
//    @Autowired
//    private BidRepository bidRepository;
//
//    @MockitoBean
//    private ProductServiceClient productServiceClient;
//
//    @Test
//    void placeBid_bidIsSaved_bidCountIncreases() {
//        ProductResponse product = new ProductResponse();
//        product.setId("product123");
//        product.setCurrentBid(BigDecimal.valueOf(1000));
//        product.setStartingPrice(BigDecimal.valueOf(1000));
//        product.setStartTime(LocalDateTime.now().minusMinutes(10));
//        product.setEndTime(LocalDateTime.now().plusMinutes(10));
//
//        Mockito.when(productServiceClient.getProductById("product123"))
//                .thenReturn(product);
//
//        long countBefore = bidRepository.count();
//
//        PlaceBidRequest request = new PlaceBidRequest();
//        request.setProductId("product123");
//        request.setBidderId("user1");
//        request.setBidAmount(BigDecimal.valueOf(2000));
//
//        PlaceBidResponse response = bidService.placeBid(request);
//
//        long countAfter = bidRepository.count();
//
//        assertEquals(countBefore + 1, countAfter);
//        assertEquals(request.getBidAmount(), response.getAmount());
//    }
//}



