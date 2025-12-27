package com.achalugo.product_service.services;

import com.achalugo.product_service.dtos.requests.ProductRequest;
import com.achalugo.product_service.dtos.requests.SearchRequest;
import com.achalugo.product_service.dtos.requests.UpdateProductRequest;
import com.achalugo.product_service.dtos.responses.CreateProductResponse;
import com.achalugo.product_service.dtos.responses.ProductResponse;
import com.achalugo.product_service.models.Product;

import java.util.List;

public interface ProductService {
    CreateProductResponse createProduct(ProductRequest productRequest);
    void updateProduct(UpdateProductRequest updateProductRequest);
    List<ProductResponse> getAllProducts();
    void deleteProduct(String productId);
    ProductResponse getProductById(String productId);
    List<Product> searchProductsByCustomCriteria(SearchRequest searchRequest);
}
