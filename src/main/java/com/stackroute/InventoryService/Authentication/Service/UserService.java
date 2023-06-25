package com.stackroute.InventoryService.Authentication.Service;

import com.stackroute.InventoryService.Authentication.Exception.UserAlreadyExistsException;
import com.stackroute.InventoryService.Authentication.Model.User;
import com.stackroute.InventoryService.Authentication.Exception.UserNotFoundException;

import java.util.List;

public interface UserService {

    User register(User user) throws UserAlreadyExistsException;
    User login(User user) throws UserNotFoundException;

    List<User> getAllUser();

}
