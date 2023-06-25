package com.stackroute.InventoryService.Product.Service;

import com.stackroute.InventoryService.Product.Exception.ProductAlreadyExistsException;
import com.stackroute.InventoryService.Product.Exception.ProductNotFoundException;
import com.stackroute.InventoryService.Product.Model.Product;

import java.util.List;

public interface ProductService {


    public Product addProduct(Product product) throws ProductAlreadyExistsException;

    public List<Product> getAllProducts();

    public Product getProductById(String id) throws ProductNotFoundException;

//    Product getProductById(int productId) throws ProductNotFoundException;

    public Product getProductByName(String productName) throws ProductNotFoundException;

    public Product deleteProductById(String id) throws ProductNotFoundException;

    public Product updateProductById(String id, Product updatedProduct) throws ProductNotFoundException;

    public Product deleteAllProducts() throws ProductNotFoundException;




}
