package com.stackroute.InventoryService.Authentication.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.stackroute.InventoryService.Authentication.Exception.UserAlreadyExistsException;
import com.stackroute.InventoryService.Authentication.Exception.UserNotFoundException;
import com.stackroute.InventoryService.Authentication.JwtConfig.SecurityTokenGenerator;
import com.stackroute.InventoryService.Authentication.Model.User;
import com.stackroute.InventoryService.Authentication.Service.UserService;



@RestController
@RequestMapping
@CrossOrigin(origins = "*")
public class UserController {

    private UserService userService;

    private SecurityTokenGenerator securityTokenGenerator;

    @Autowired
    public UserController(UserService userService , SecurityTokenGenerator securityTokenGenerator) {
        this.userService = userService;
        this.securityTokenGenerator = securityTokenGenerator;
    }


    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) throws UserNotFoundException, UserAlreadyExistsException {
        // use try catch block to handle the exception
        try {
            return new ResponseEntity<>(userService.register(user), HttpStatus.CREATED);
        } catch (UserAlreadyExistsException e) {
            return new ResponseEntity<>("user already exists", HttpStatus.CONFLICT);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login( @RequestBody User user) throws UserNotFoundException {

        User user1 = userService.login(user);

        // try catch block to handle the exception
        try {
            if (user1.getEmailId().isEmpty() || user1.getPassword().isEmpty()){
                throw new UserNotFoundException();
            }
            else {
                return new ResponseEntity<>(securityTokenGenerator.generateToken(user1), HttpStatus.OK);
            }
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>("User Not Found", HttpStatus.NOT_FOUND);
        }
    }

    //write a controller method to get all users get the get all user metheod from service
    @GetMapping("/users")
    public ResponseEntity<?> getAllUsers() {
        // use try catch block to handle the exception
        try {
            return new ResponseEntity<>(userService.getAllUser(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Users Not Found", HttpStatus.NOT_FOUND);
        }
    }






}
