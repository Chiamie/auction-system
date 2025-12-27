package com.achalugo.product_service.dtos.requests;


import jakarta.validation.Valid;
import lombok.Data;

@Data

public class SearchRequest {
    private String name;
    private String productCategory;
    private String productStatus;
    private String city;
    private String state;
    private String country;
}
