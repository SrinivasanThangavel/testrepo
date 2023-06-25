package com.stackroute.InventoryService.Authentication.JwtConfig;

import com.stackroute.InventoryService.Authentication.Model.User;

import java.util.Map;

public interface SecurityTokenGenerator {
    Map<String, String> generateToken(User user);
}
