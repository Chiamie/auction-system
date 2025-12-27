package com.achalugo.product_service.services;

import com.achalugo.product_service.ProductServiceApplication;
import com.achalugo.product_service.data.models.Category;
import com.achalugo.product_service.data.models.Product;
import com.achalugo.product_service.data.models.Status;
import com.achalugo.product_service.dtos.requests.ProductRequest;
import com.achalugo.product_service.dtos.requests.SearchRequest;
import com.achalugo.product_service.dtos.requests.UpdateProductRequest;
import com.achalugo.product_service.dtos.responses.CreateProductResponse;
import com.achalugo.product_service.dtos.responses.ProductResponse;
import com.achalugo.product_service.data.repositories.ProductRepository;
import com.achalugo.product_service.exceptions.InvalidProductIdException;
import org.springframework.dao.DuplicateKeyException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

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
    void createProduct_countIsOne_deleteProduct_countIsZero() {
        Product productOne = new Product();
        productOne.setName("productName");
        productOne.setDescription("productDescription");
        productOne.setCategory(Category.ELECTRONICS);
        productOne.setStartingPrice(BigDecimal.valueOf(1.000));
        productOne.setSellerId("sellerOne");
        productOne.setStatus(Status.UPCOMING);
        Product createdProduct = productRepository.save(productOne);
        assertEquals(1, productRepository.count());

        productService.deleteProduct(createdProduct.getId());
        assertEquals(0, productRepository.count());
    }

    @Test
    void deleteProductWithWrongProductId_throwsException() {
        Product productOne = new Product();
        productOne.setName("productName");
        productOne.setDescription("productDescription");
        productOne.setCategory(Category.ELECTRONICS);
        productOne.setStartingPrice(BigDecimal.valueOf(1.000));
        productOne.setSellerId("sellerOne");
        productOne.setStatus(Status.UPCOMING);
        Product createdProduct = productRepository.save(productOne);
        assertEquals(1, productRepository.count());


        assertThrows(InvalidProductIdException.class, () -> productService.deleteProduct("Nne"));
    }

    @Test
    void createProduct_countIsOne_updateStatusToOngoing_countIsStillOne_deleteProduct_throwsException() {
        ProductRequest  productOne = new ProductRequest();
        productOne.setProductName("productName");
        productOne.setProductDescription("productDescription");
        productOne.setProductCategory("Electronics");
        productOne.setProductStartingPrice(BigDecimal.valueOf(1.000));
        productOne.setSellerId("sellerOne");

        CreateProductResponse createdProduct = productService.createProduct(productOne);
        assertEquals(1, productRepository.count());

        Product product = productRepository.findById(createdProduct.getProductId()).get();
        product.setStatus(Status.ONGOING);
        Product updatedProduct = productRepository.save(product);
        assertEquals(1, productRepository.count());

        assertThrows(IllegalStateException.class, () -> productService.deleteProduct(updatedProduct.getId()));
    }

    @Test
    void createProduct_countIsOne_getProductById_returnsCreatedProduct() {
        ProductRequest  productOne = new ProductRequest();
        productOne.setProductName("productName");
        productOne.setProductDescription("productDescription");
        productOne.setProductCategory("Electronics");
        productOne.setProductStartingPrice(BigDecimal.valueOf(1.000));
        productOne.setSellerId("sellerOne");
        productService.createProduct(productOne);

        ProductRequest  productTwo = new ProductRequest();
        productTwo.setProductName("productName2");
        productTwo.setProductDescription("productDescription2");
        productTwo.setProductCategory("Electronics");
        productTwo.setProductStartingPrice(BigDecimal.valueOf(1.000));
        productTwo.setSellerId("sellerOne2");

        CreateProductResponse createdProduct = productService.createProduct(productTwo);
        assertEquals(2, productRepository.count());

        ProductResponse productResponse = productService.getProductById(createdProduct.getProductId());
        assertEquals(createdProduct.getProductName(), productResponse.getName());
    }

    @Test
    void createProduct_countIsOne_getProductByIdUsingWrong_throwsException() {
        ProductRequest  productOne = new ProductRequest();
        productOne.setProductName("productName");
        productOne.setProductDescription("productDescription");
        productOne.setProductCategory("Electronics");
        productOne.setProductStartingPrice(BigDecimal.valueOf(1.000));
        productOne.setSellerId("sellerOne");
        productService.createProduct(productOne);

        assertEquals(1, productRepository.count());
        assertThrows(InvalidProductIdException.class, () -> productService.getProductById("Nne"));
    }

    @Test
    void createFiveProducts_countIsFive_searchProductsByLocation_returnsProductsInThatLocation() {
        ProductRequest  productOne = new ProductRequest();
        productOne.setProductName("productName");
        productOne.setProductDescription("productDescription");
        productOne.setProductCity("city");
        productOne.setProductState("state");
        productOne.setProductCountry("country");
        productOne.setSellerId("sellerTwo");
        productOne.setProductCategory("Electronics");
        productService.createProduct(productOne);

        ProductRequest  productTwo = new ProductRequest();
        productTwo.setProductName("productName2");
        productTwo.setProductDescription("productDescription2");
        productTwo.setProductCity("city2");
        productTwo.setProductState("state2");
        productTwo.setProductCountry("country2");
        productTwo.setSellerId("sellerTwo");
        productTwo.setProductCategory("Electronics");
        productService.createProduct(productTwo);

        ProductRequest  productThree = new ProductRequest();
        productThree.setProductName("productName3");
        productThree.setProductDescription("productDescription3");
        productThree.setProductCity("city2");
        productThree.setProductState("state2");
        productThree.setProductCountry("country2");
        productThree.setSellerId("sellerThree");
        productThree.setProductCategory("Electronics");
        productService.createProduct(productThree);

        ProductRequest  productFour = new ProductRequest();
        productFour.setProductName("productName4");
        productFour.setProductDescription("productDescription4");
        productFour.setProductCity("city2");
        productFour.setProductState("state2");
        productFour.setProductCountry("country2");
        productFour.setSellerId("sellerFour");
        productFour.setProductCategory("Electronics");
        productService.createProduct(productFour);


        SearchRequest searchRequest = new SearchRequest();
        searchRequest.setCity("city");
        searchRequest.setState("state");
        searchRequest.setCountry("country");

        List<Product> foundProductsByCategory = productService.searchProductsByCustomCriteria(searchRequest);
        assertEquals(4, productRepository.count());
        assertEquals(3, foundProductsByCategory.size());


    }

    @Test
    void createFiveProducts_countIsFive_searchProductsByCategory_returnsProductsOfThatCategory() {
        ProductRequest  productOne = new ProductRequest();
        productOne.setProductName("productName");
        productOne.setProductDescription("productDescription");
        productOne.setProductCategory("Electronics");
        productOne.setProductStartingPrice(BigDecimal.valueOf(1.000));
        productOne.setSellerId("sellerOne");
        productService.createProduct(productOne);

        ProductRequest  productTwo = new ProductRequest();
        productTwo.setProductName("productName2");
        productTwo.setProductDescription("productDescription2");
        productTwo.setProductCategory("Electronics");
        productTwo.setProductStartingPrice(BigDecimal.valueOf(1.000));
        productTwo.setSellerId("sellerTwo");
        productService.createProduct(productTwo);

        ProductRequest  productThree = new ProductRequest();
        productThree.setProductName("productName3");
        productThree.setProductDescription("productDescription3");
        productThree.setProductCategory("Vehicles");
        productThree.setProductStartingPrice(BigDecimal.valueOf(1.000));
        productThree.setSellerId("sellerThree");
        productService.createProduct(productThree);

        ProductRequest  productFour = new ProductRequest();
        productFour.setProductName("productName4");
        productFour.setProductDescription("productDescription4");
        productFour.setProductCategory("Electronics");
        productFour.setProductStartingPrice(BigDecimal.valueOf(1.000));
        productFour.setSellerId("sellerFour");
        productService.createProduct(productFour);

        ProductRequest  productFive = new ProductRequest();
        productFive.setProductName("productName5");
        productFive.setProductDescription("productDescription5");
        productFive.setProductCategory("Vehicles");
        productFive.setProductStartingPrice(BigDecimal.valueOf(1.000));
        productFive.setSellerId("sellerFour");
        productService.createProduct(productFive);

        SearchRequest searchRequest = new SearchRequest();
        searchRequest.setProductCategory("Electronics");

        List<Product> foundProductsByCategory = productService.searchProductsByCustomCriteria(searchRequest);
        assertEquals(5, productRepository.count());
        assertEquals(3, foundProductsByCategory.size());


    }

    @Test
    void searchProductsByCustomCriteria() {
    }
}