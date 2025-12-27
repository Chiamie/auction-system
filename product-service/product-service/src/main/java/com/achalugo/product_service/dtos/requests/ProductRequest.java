package com.achalugo.product_service.dtos.requests;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Data

public class ProductRequest {

    @NotBlank(message = "Name is required")
    private String productName;

    @NotBlank(message = "SellerId is required")
    private String sellerId;

    @NotBlank(message = "Description is required")
    private String productDescription;

    @NotBlank(message = "City is required")
    private String productCity;

    @NotBlank(message = "State is required")
    private String productState;

    @NotBlank(message = "Country is required")
    private String productCountry;

    @NotBlank(message = "Category is required")
    private String productCategory;

    @NotNull(message = "Starting price is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0")
    private BigDecimal productStartingPrice;


    @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
    @NotNull(message = "Start time is required")
    @Future(message = "Start time must be in the future")
    private LocalDateTime productStartTime;



    @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
    @NotNull(message = "Start time is required")
    @Future(message = "Start time must be in the future")
    private LocalDateTime productEndTime;

}

