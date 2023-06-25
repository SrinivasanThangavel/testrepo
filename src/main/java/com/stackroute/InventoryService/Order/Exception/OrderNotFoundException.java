package com.stackroute.InventoryService.Order.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Order Not Found")
public class OrderNotFoundException extends Throwable {
    public OrderNotFoundException(String s) {
    }
}
