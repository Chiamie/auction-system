package com.achalugo.product_service.dtos.responses;

import lombok.Data;

@Data
public class CreateProductResponse {
    private String productId;
    private String productName;
    private String productDescription;
    private String productStartingPrice;
    private String productCategory;

}
