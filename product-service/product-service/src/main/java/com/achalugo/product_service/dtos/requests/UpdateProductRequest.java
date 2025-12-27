package com.achalugo.product_service.dtos.requests;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class UpdateProductRequest {

    @NotNull(message = "Product Id is required")
    private String productId;

    private String productName;
    private String productDescription;
    private String productCity;
    private String productState;
    private String productCountry;
    private String productCategory;

    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0")
    private BigDecimal productStartingPrice;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime productStartTime;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime productEndTime;

}
