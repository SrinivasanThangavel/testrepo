package com.stackroute.InventoryService.Authentication.JwtConfig;


import com.stackroute.InventoryService.Authentication.Model.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
@Service
public class SecurityTokenGeneratorImpl implements SecurityTokenGenerator{

    @Override
    public Map<String, String> generateToken(User user) {
        Map<String, String> map = new HashMap<>();
        long currentTimeInMilli = System.currentTimeMillis();
        String jwtToken = Jwts.builder()
                .setSubject(user.getEmailId())
                .setIssuedAt(new Date(currentTimeInMilli))
                .signWith(SignatureAlgorithm.HS512, "mysecretkey")
//                .setExpiration(new Date(currentTimeInMilli+1200000))     //token will be valid for 20 minutes only.
                .compact();
        map.put("token", jwtToken);
        map.put("message", "User successfully logged in");
        map.put("emailId", user.getEmailId());
//        map.put("role", user.getRole().toString());                          // role will be stored in mongoDB.
        return map;
    }


}
