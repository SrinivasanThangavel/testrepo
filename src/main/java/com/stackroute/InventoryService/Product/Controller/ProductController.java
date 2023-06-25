package com.stackroute.InventoryService.Product.Controller;

import com.stackroute.InventoryService.Product.Exception.ProductAlreadyExistsException;
import com.stackroute.InventoryService.Product.Exception.ProductNotFoundException;
import com.stackroute.InventoryService.Product.Model.Product;
import com.stackroute.InventoryService.Product.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class ProductController {


    private ProductService productService;
    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // write the code for addProduct() method form ProductService
    // write the code for getAllProducts() method form ProductService
    // write the code for getProductById() method form ProductService
    // write the code for deleteProductById() method form ProductService
    // write the code for updateProductById() method form ProductService
    // write the code for deleteAllProducts() method form ProductService

    //Use ResponseEntity to return the response back to the client
    //Use @RequestBody to get the product details from the request body
    // use try catch block to handle the exception

    @PostMapping("/product")
    public ResponseEntity<?> addProduct(@RequestBody Product product){
        try{
            return new ResponseEntity<Product>(productService.addProduct(product), HttpStatus.OK);
        }catch (ProductAlreadyExistsException e){
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/products")
    public ResponseEntity<?> getAllProducts(){
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<?> getProductById(@PathVariable String id){
        try{
            return new ResponseEntity<Product>(productService.getProductById(id), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<?> deleteProductById(@PathVariable String id){
        try{
            return new ResponseEntity<Product>(productService.deleteProductById(id), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/product/{id}")
    public ResponseEntity<?> updateProductById(@PathVariable String id, @RequestBody Product product){
        try{
            return new ResponseEntity<Product>(productService.updateProductById(id, product), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/products")
    public ResponseEntity<?> deleteAllProducts() throws ProductNotFoundException {
        return new ResponseEntity<>(productService.deleteAllProducts(), HttpStatus.OK);
    }









}
