package com.stackroute.InventoryService.Product.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
@ResponseStatus(code = HttpStatus.NOT_FOUND , reason = "Product Not found")
public class ProductNotFoundException extends Exception {
    public ProductNotFoundException(String message) {
        super(message);
    }

}