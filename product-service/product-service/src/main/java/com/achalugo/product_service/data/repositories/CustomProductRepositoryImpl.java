package com.achalugo.product_service.data.repositories;

import com.achalugo.product_service.data.models.Category;
import com.achalugo.product_service.data.models.Location;
import com.achalugo.product_service.data.models.Product;
import com.achalugo.product_service.data.models.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;


@Repository

public class CustomProductRepositoryImpl implements CustomProductRepository {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<Product> searchProduct(String name, Category productCategory, Status productStatus, Location location) {
        Criteria criteria = new Criteria();
        List<Criteria> criteriaList = new ArrayList<>();
        if (name != null && !name.isEmpty())
            criteriaList.add(Criteria.where("name").regex(name, "i"));
        if (productCategory != null)
            criteriaList.add(Criteria.where("category").is(productCategory));

        if (productStatus != null)
            criteriaList.add(Criteria.where("status").is(productStatus));
        if (location != null)
            criteriaList.add(Criteria.where("location").is(location));
        if (!criteriaList.isEmpty())
//            criteria = criteriaList.get(0);
            criteria.andOperator(criteriaList.toArray(new Criteria[0]));

        Query query = new Query(criteria);
//        System.out.println("MongoDB query: " + query);
        return mongoTemplate.find(query, Product.class);


    }

}
