package com.achalugo.product_service.services;

import com.achalugo.product_service.ProductServiceApplication;
import com.achalugo.product_service.data.models.Category;
import com.achalugo.product_service.data.models.Product;
import com.achalugo.product_service.data.models.Status;
import com.achalugo.product_service.dtos.requests.ProductRequest;
import com.achalugo.product_service.dtos.requests.UpdateProductRequest;
import com.achalugo.product_service.dtos.responses.CreateProductResponse;
import com.achalugo.product_service.dtos.responses.ProductResponse;
import com.achalugo.product_service.data.repositories.ProductRepository;
import org.springframework.dao.DuplicateKeyException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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


//        assertThrows(DuplicateProductException.class, ()->productService.createProduct(productRequest));
//
        assertThrows(DuplicateKeyException.class, () -> productService.createProduct(productRequest));
        assertEquals(1, productRepository.count());
    }

    @Test
    void createTwoDifferentProducts_CountIsTwo_GetAllProductsReturnsTheTwoProducts() {

        ProductRequest  productOne = new ProductRequest();
        productOne.setProductName("productName");
        productOne.setProductDescription("productDescription");
        productOne.setProductCategory("Electronics");
        productOne.setProductStartingPrice(BigDecimal.valueOf(1.000));
        productOne.setSellerId("sellerOne");
        productService.createProduct(productOne);

        ProductRequest  productTwo = new ProductRequest();
        productTwo.setProductName("productName");
        productTwo.setProductDescription("productDescription7");
        productTwo.setProductCategory("Electronics");
        productTwo.setProductStartingPrice(BigDecimal.valueOf(1.000));
        productTwo.setSellerId("sellerTwo");
        productService.createProduct(productTwo);


        List<ProductResponse> actualProducts = productService.getAllProducts();
        List<Product> expectedProducts = productRepository.findAll();
        assertEquals(2, productRepository.count());
        assertEquals(expectedProducts.size(), actualProducts.size());
        assertEquals( expectedProducts.get(0).getDescription(), actualProducts.get(0).getDescription());



    }

    @Test
    void createProduct_countIsOne_updateProduct_productHasUpdatedFields_countIsStillOne() {
        Product productOne = new Product();
        productOne.setName("productName");
        productOne.setDescription("productDescription");
        productOne.setCategory(Category.ELECTRONICS);
        productOne.setStartingPrice(BigDecimal.valueOf(1.000));
        productOne.setSellerId("sellerOne");
        productOne.setStatus(Status.UPCOMING);
        Product createdProduct = productRepository.save(productOne);

        assertEquals(1, productRepository.count());

        UpdateProductRequest updateProductRequest = new UpdateProductRequest();
        updateProductRequest.setProductId(createdProduct.getId());
        updateProductRequest.setProductName("productName2");
        updateProductRequest.setProductDescription("productDescription2");
        updateProductRequest.setProductCategory("Electronics");
        productService.updateProduct(updateProductRequest);

        assertEquals(1, productRepository.count());
        Product actualProduct = productRepository.findById(createdProduct.getId()).get();


//        System.out.println(productRepository.findById(createdProduct.getId()));
        assertEquals(updateProductRequest.getProductName(),  actualProduct.getName());

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