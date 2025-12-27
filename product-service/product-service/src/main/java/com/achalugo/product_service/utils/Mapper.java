package com.achalugo.product_service.utils;

import com.achalugo.product_service.dtos.requests.ProductRequest;
import com.achalugo.product_service.dtos.requests.UpdateProductRequest;
import com.achalugo.product_service.dtos.responses.CreateProductResponse;
import com.achalugo.product_service.dtos.responses.ProductResponse;
import com.achalugo.product_service.models.Category;
import com.achalugo.product_service.models.Location;
import com.achalugo.product_service.models.Product;
import com.achalugo.product_service.models.Status;

public class Mapper {

    public static CreateProductResponse mapToCreateProduct(Product savedProduct){
        CreateProductResponse  createProductResponse = new CreateProductResponse();
        createProductResponse.setProductId(savedProduct.getId());
        createProductResponse.setProductName(savedProduct.getName());
        createProductResponse.setProductDescription(savedProduct.getDescription());
        createProductResponse.setProductCategory(savedProduct.getCategory() + "");
        createProductResponse.setProductStartingPrice(savedProduct.getStartingPrice() + "");
        return createProductResponse;
    }

    public static Product map(ProductRequest productRequest) {
        Product product = new Product();

        product.setName(productRequest.getProductName());
        product.setDescription(productRequest.getProductDescription());
        product.setSellerId(productRequest.getSellerId());

        Location location = new Location();
        location.setCity(productRequest.getProductCity());
        location.setState(productRequest.getProductState());
        location.setCountry(productRequest.getProductCountry());

        product.setLocation(location);
        product.setStartingPrice(productRequest.getProductStartingPrice());
        product.setCategory(Category.valueOf(productRequest.getProductCategory().toUpperCase()));

        product.setStartTime(productRequest.getProductStartTime());
        product.setEndTime(productRequest.getProductEndTime());

        return product;

    }

    public static ProductResponse map(Product product) {
        ProductResponse productResponse = new ProductResponse();
        productResponse.setId(product.getId());
        productResponse.setName(product.getName());
        productResponse.setDescription(product.getDescription());
        productResponse.setStartingPrice(product.getStartingPrice());

        productResponse.setCurrentBid(product.getCurrentBid());
        productResponse.setCategory(product.getCategory());
        productResponse.setStatus(product.getStatus());
        productResponse.setStartTime(product.getStartTime());
        productResponse.setEndTime(product.getEndTime());
        return productResponse;

    }

    public static Product map(Product foundProduct, UpdateProductRequest updateProductRequest) {

        foundProduct.setName(updateProductRequest.getProductName());
        foundProduct.setDescription(updateProductRequest.getProductDescription());
        foundProduct.setCategory(Category.valueOf(updateProductRequest.getProductCategory()));
        foundProduct.setStartingPrice(updateProductRequest.getProductStartingPrice());
        foundProduct.setStartTime(updateProductRequest.getProductStartTime());
        foundProduct.setEndTime(updateProductRequest.getProductEndTime());

        Location location = foundProduct.getLocation();
        location.setCity(updateProductRequest.getProductCity());
        location.setState(updateProductRequest.getProductState());
        location.setCountry(updateProductRequest.getProductCountry());

        foundProduct.setLocation(location);

        return foundProduct;
    }

}
