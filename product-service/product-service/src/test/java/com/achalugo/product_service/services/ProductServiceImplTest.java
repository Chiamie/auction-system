package com.achalugo.product_service.services;

import com.achalugo.product_service.ProductServiceApplication;
import com.achalugo.product_service.dtos.requests.ProductRequest;
import com.achalugo.product_service.dtos.responses.CreateProductResponse;
import com.achalugo.product_service.exceptions.DuplicateProductException;
import com.achalugo.product_service.models.Category;
import com.achalugo.product_service.models.Location;
import com.achalugo.product_service.models.Product;
import com.achalugo.product_service.repositories.ProductRepository;
import org.springframework.dao.DuplicateKeyException;
import com.sun.tools.javac.Main;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = ProductServiceApplication.class)
class ProductServiceImplTest {
    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;


    @BeforeEach
    void setUp() {
        productRepository.deleteAll();
    }

    @Test
    void createProductCountIsOne() {
//        Product expectedProduct = new Product();
//        expectedProduct.setName("productName");
//        expectedProduct.setName("productName");
//        expectedProduct.setDescription("productDescription");
//        expectedProduct.setCategory(Category.ELECTRONICS);
//        expectedProduct.setStartingPrice(BigDecimal.valueOf(1.000));
//
//        Location location = new Location();
//        location.setCity("productCity");
//        location.setCountry("productCountry");
//        location.setState("productState");
//        expectedProduct.setLocation(location);

        ProductRequest  productRequest = new ProductRequest();
        productRequest.setProductName("productName");
        productRequest.setProductDescription("productDescription");
        productRequest.setProductCategory("Electronics");
        productRequest.setProductCity("productCity");
        productRequest.setProductCountry("productCountry");
        productRequest.setProductState("productState");
        productRequest.setProductStartingPrice(BigDecimal.valueOf(1.000));

        CreateProductResponse actualProduct = productService.createProduct(productRequest);

        assertEquals(1, productRepository.count());
    }

    @Test
    void createProductTwiceCountIsOne() {
        ProductRequest  productRequest = new ProductRequest();
        productRequest.setProductName("productName");
        productRequest.setProductDescription("productDescription");
        productRequest.setProductCategory("Electronics");
        productRequest.setProductCity("productCity");
        productRequest.setProductCountry("productCountry");
        productRequest.setProductState("productState");
        productRequest.setProductStartingPrice(BigDecimal.valueOf(1.000));
        productRequest.setSellerId("sellerId");
        productService.createProduct(productRequest);


        assertThrows(DuplicateProductException.class, ()->productService.createProduct(productRequest));

        assertEquals(1, productRepository.count());
    }

    @Test
    void getAllProducts() {
    }

    @Test
    void updateProduct() {
    }

    @Test
    void deleteProduct() {
    }

    @Test
    void getProductById() {
    }

    @Test
    void searchProductsByCustomCriteria() {
    }
}