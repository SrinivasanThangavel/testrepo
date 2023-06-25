package com.stackroute.InventoryService.Authentication.Repository;


import com.stackroute.InventoryService.Authentication.Model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface  UserRepository extends MongoRepository<User, String> {

    User findByEmailId(String emailId);

    User findByEmailIdAndPassword(String emailId, String password);
}
