package com.achalugo.product_service.dtos.requests;

import ch.qos.logback.core.status.Status;
import com.achalugo.product_service.models.Category;
import com.achalugo.product_service.models.Location;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class ProductRequest {
    private String productName;
    private String productDescription;
    private String productCity;
    private String productState;
    private String productCountry;
    private String productCategory;
    private BigDecimal productStartingPrice;
    private BigDecimal productCurrentBid;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime productStartTime;
    private String productStatus;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime productEndTime;

}
