package com.stackroute.InventoryService.Authentication.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "User")
public class User {

    // create fields for user class
    @Id
    private String emailId;
    private String password;
    private String name;

    public User(String emailId, String password, String name) {
        this.emailId = emailId;
        this.password = password;
        this.name = name;
    }

    public User() {
    }

    public String getEmailId() {
        return emailId;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }
}
