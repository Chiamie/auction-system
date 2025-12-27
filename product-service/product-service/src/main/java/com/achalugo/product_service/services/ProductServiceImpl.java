package com.achalugo.product_service.services;

import com.achalugo.product_service.dtos.requests.ProductRequest;

import com.achalugo.product_service.dtos.requests.SearchRequest;
import com.achalugo.product_service.dtos.requests.UpdateProductRequest;
import com.achalugo.product_service.dtos.responses.CreateProductResponse;
import com.achalugo.product_service.dtos.responses.ProductResponse;
import com.achalugo.product_service.exceptions.DuplicateProductException;
import com.achalugo.product_service.exceptions.InvalidSearchParameterException;
import com.achalugo.product_service.models.Category;
import com.achalugo.product_service.models.Location;
import com.achalugo.product_service.models.Product;
import com.achalugo.product_service.models.Status;
import com.achalugo.product_service.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.achalugo.product_service.utils.Mapper.map;
import static com.achalugo.product_service.utils.Mapper.mapToCreateProduct;

@Service
//@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

    @Autowired
    private  ProductRepository productRepository;

//    public ProductServiceImpl(ProductRepository productRepository) {
//        this.productRepository = productRepository;
//    }

    public CreateProductResponse createProduct(ProductRequest productRequest) {
        boolean productExists = productRepository.existsBySellerIdAndNameAndDescription(
                productRequest.getSellerId(),
                productRequest.getProductName(),
                productRequest.getProductDescription()
        );
        if  (productExists) {
            throw new DuplicateProductException("You have already listed this product");
        }
        Product product = map(productRequest);

        Product savedProduct = productRepository.save(product);
        log.info("Product {} has been created", savedProduct);
        return mapToCreateProduct(savedProduct);

    }

    public List<ProductResponse> getAllProducts() {
        List<Product> products = productRepository.findAll();
        List<ProductResponse> productResponses = new ArrayList<>();
        for (Product product : products) {
            productResponses.add(map(product));
        }
        return productResponses;
    }

    public void updateProduct(UpdateProductRequest  updateProductRequest) {
        Optional<Product> product = productRepository.findById(updateProductRequest.getProductId());
        if (product.isEmpty()) {
            log.error("Product not found");
        }
        Product foundProduct = product.get();
        if (foundProduct.getStatus() == Status.UPCOMING) {
            Product updatedProduct = map(foundProduct, updateProductRequest);
            productRepository.save(updatedProduct);
            log.info("Product {} has been updated", updatedProduct);
        }
    }

    public void deleteProduct(String productId) {
        Optional<Product> product = productRepository.findById(productId);
        if (product.isEmpty()) {
            throw new IllegalArgumentException("Product not found");
        }
        Product foundProduct = product.get();
        if (foundProduct.getStatus() == Status.UPCOMING) {
            productRepository.delete(foundProduct);
            log.info("Product {} has been deleted", foundProduct.getId());
        } else
            throw new IllegalStateException("Only upcoming products can be deleted");

    }

    public ProductResponse getProductById(String productId) {
        Optional<Product> product = productRepository.findById(productId);
        if (product.isEmpty()) {
            throw new IllegalArgumentException("Product not found");
        }
        Product foundProduct = product.get();
        return map(foundProduct);
    }



    public List<Product> searchProductsByCustomCriteria (SearchRequest searchRequest) {

        Location location = new Location();
        location.setCity(searchRequest.getCity());
        location.setState(searchRequest.getState());
        location.setCountry(searchRequest.getCountry());

        Category productCategory = getProductCategory(searchRequest);
        Status productStatus = getStatus(searchRequest);

        return productRepository.searchProduct(searchRequest.getName(), productCategory, productStatus, location);


    }

    private static Category getProductCategory(SearchRequest searchRequest) {
       try {
           return Category.valueOf(searchRequest.getProductCategory().toUpperCase());
       } catch (IllegalArgumentException | NullPointerException e) {
           throw new InvalidSearchParameterException(e.getMessage());
       }

    }

    private static Status getStatus(SearchRequest searchRequest) {
        try {
            return Status.valueOf(searchRequest.getProductStatus().toUpperCase());
        } catch (IllegalArgumentException | NullPointerException e) {
            throw new InvalidSearchParameterException("Invalid product status: " + searchRequest.getProductStatus());
        }
    }




}
