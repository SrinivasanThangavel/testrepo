package com.stackroute.InventoryService.Product.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT,reason = "Product already Exists")
public class ProductAlreadyExistsException extends Exception {
    public ProductAlreadyExistsException(String productAlreadyExists) {
    }
}
