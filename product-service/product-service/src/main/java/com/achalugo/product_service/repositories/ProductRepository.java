package com.achalugo.product_service.repositories;

import com.achalugo.product_service.models.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product,String>, CustomProductRepository {



}
