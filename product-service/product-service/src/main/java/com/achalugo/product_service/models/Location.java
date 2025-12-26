package com.achalugo.product_service.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("location")

public class Location {
    @Id
    private String id;
    private String city;
    private String state;
    private String country;
}
