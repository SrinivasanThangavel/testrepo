package com.stackroute.InventoryService.Product.Repository;

import com.stackroute.InventoryService.Product.Model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends MongoRepository<Product,String> {
    boolean existsByProductName(String productName);

    Product findByProductName(String productName);

    Optional<Product> findByProductId(int productId);
}
