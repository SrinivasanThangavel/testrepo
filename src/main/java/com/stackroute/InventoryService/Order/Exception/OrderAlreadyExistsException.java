package com.stackroute.InventoryService.Order.Exception;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = org.springframework.http.HttpStatus.CONFLICT, reason = "Order Already Exists")
public class OrderAlreadyExistsException extends Throwable {
    public OrderAlreadyExistsException(String s) {
    }
}
