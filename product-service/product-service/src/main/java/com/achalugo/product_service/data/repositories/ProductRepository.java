package com.achalugo.product_service.data.repositories;

import com.achalugo.product_service.data.models.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product,String>, CustomProductRepository {


    boolean existsBySellerIdAndNameAndDescription(String attr0, String attr1, String attr2);
}
