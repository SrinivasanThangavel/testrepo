package com.stackroute.InventoryService.Authentication.Exception;

public class UserNotFoundException extends Exception{
    private String message;

    public UserNotFoundException() {
    }

    public UserNotFoundException(String message) {
        super(message);
        this.message = message;
    }
}
