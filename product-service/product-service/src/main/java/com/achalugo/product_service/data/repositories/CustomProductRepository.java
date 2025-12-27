package com.achalugo.product_service.repositories;

import com.achalugo.product_service.models.Category;
import com.achalugo.product_service.models.Location;
import com.achalugo.product_service.models.Product;
import com.achalugo.product_service.models.Status;

import java.util.List;

public interface CustomProductRepository {
    List<Product> searchProduct(String name, Category productCategory, Status productStatus, Location location);

}
