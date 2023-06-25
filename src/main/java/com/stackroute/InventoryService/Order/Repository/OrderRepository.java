package com.stackroute.InventoryService.Order.Repository;

import com.stackroute.InventoryService.Order.Model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface OrderRepository extends MongoRepository<Order,String> {
    Collection<Object> findAllByUserEmailId(String userId);

    Collection<Object> findAllByUserEmailIdAndOrderStatus(String userId, String status);
}
