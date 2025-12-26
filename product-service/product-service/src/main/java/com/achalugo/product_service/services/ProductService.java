package com.achalugo.product_service.services;

import com.achalugo.product_service.dtos.requests.ProductRequest;

import com.achalugo.product_service.dtos.responses.ProductResponse;
import com.achalugo.product_service.models.Product;
import com.achalugo.product_service.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

import static com.achalugo.product_service.utils.Mapper.map;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;

//    public ProductService(ProductRepository productRepository) {
//        this.productRepository = productRepository;
//    }

    public void createProduct(@RequestBody ProductRequest productRequest) {
        Product product = map(productRequest);
        productRepository.save(product);
        log.info("Product {} has been created", product);

    }

    public List<ProductResponse> getAllProducts() {
        List<Product> products = productRepository.findAll();
        List<ProductResponse> productResponses = new ArrayList<>();
        for (Product product : products) {
            productResponses.add(map(product));
        }
        return productResponses;
    }

}
