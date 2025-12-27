package com.achalugo.product_service.controllers;


import com.achalugo.product_service.dtos.requests.ProductRequest;
import com.achalugo.product_service.dtos.requests.SearchRequest;
import com.achalugo.product_service.dtos.requests.UpdateProductRequest;
import com.achalugo.product_service.dtos.responses.ProductResponse;
import com.achalugo.product_service.models.Product;
import com.achalugo.product_service.services.ProductServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product")
public class ProductController {


    private final ProductServiceImpl productService;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@RequestBody ProductRequest productRequest) {
        productService.createProduct(productRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> getAllProducts() {
        return productService.getAllProducts();
    }

    @PatchMapping
    @ResponseStatus(HttpStatus.OK)
    public void updateProduct(@RequestBody UpdateProductRequest updateProductRequest) {
        productService.updateProduct(updateProductRequest);

    }

    @DeleteMapping("/{productId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable String productId) {
        productService.deleteProduct(productId);
    }

    @GetMapping("/{productId}")
    @ResponseStatus(HttpStatus.OK)
    public ProductResponse getProductById(@PathVariable String productId) {
        return productService.getProductById(productId);
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchProductsByCustomCriteria(@ModelAttribute SearchRequest searchRequest) {
        List<Product> results = productService.searchProductsByCustomCriteria(searchRequest);
        return ResponseEntity.ok(results);

    }


}
