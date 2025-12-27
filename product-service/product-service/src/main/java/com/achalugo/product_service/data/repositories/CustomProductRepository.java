package com.achalugo.product_service.data.repositories;

import com.achalugo.product_service.data.models.Category;
import com.achalugo.product_service.data.models.Location;
import com.achalugo.product_service.data.models.Product;
import com.achalugo.product_service.data.models.Status;

import java.util.List;

public interface CustomProductRepository {
    List<Product> searchProduct(String name, Category productCategory, Status productStatus, Location location);

}
