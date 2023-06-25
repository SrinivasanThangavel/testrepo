package com.stackroute.InventoryService.Product.Service;

import com.stackroute.InventoryService.Product.Exception.ProductAlreadyExistsException;
import com.stackroute.InventoryService.Product.Exception.ProductNotFoundException;
import com.stackroute.InventoryService.Product.Model.Product;
import com.stackroute.InventoryService.Product.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{

    ProductRepository productRepository;
    @Autowired
    public ProductServiceImpl(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @Override
    public Product addProduct(Product product) throws ProductAlreadyExistsException {

        // use if else to check if product already exists
        if(productRepository.existsById(product.getProductId())){
            throw new ProductAlreadyExistsException("Product already exists");
        }else{
            productRepository.save(product);
        }
        return product;
    }

    @Override
    public List<Product> getAllProducts() {
          return productRepository.findAll();
    }

    @Override
    public Product getProductById(String id) throws ProductNotFoundException {
        if(productRepository.existsById(id)){
            return productRepository.findById(id).get();
        }else{
            throw new ProductNotFoundException("Product not found");
        }
    }
//    @Override
//    public Product getProductById(int productId) throws ProductNotFoundException {
//        Optional<Product> optionalProduct = productRepository.findById(productId);
//        if (optionalProduct.isPresent()) {
//            return optionalProduct.get();
//        } else {
//            throw new ProductNotFoundException("Product not found");
//        }
//    }

    @Override
    public Product getProductByName(String productName) throws ProductNotFoundException {
        if(productRepository.existsByProductName(productName)){
            return productRepository.findByProductName(productName);
        }else{
            throw new ProductNotFoundException("Product not found");
        }
    }

    @Override
    public Product deleteProductById(String id) throws ProductNotFoundException {
        if(productRepository.existsById(id)){
            Product product = productRepository.findById(id).get();
            productRepository.deleteById(id);
            return product;
        }else{
            throw new ProductNotFoundException("Product not found");
        }
    }

    public Product updateProductById(String productId, Product updatedProduct) throws ProductNotFoundException {
        // Check if the product exists in the inventory
        if (!productRepository.existsById(productId)) {
            throw new ProductNotFoundException("Product not found with ID: " + productId);
        }
        // Get the existing product from the repository
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (optionalProduct.isPresent()) {
            Product existingProduct = optionalProduct.get();

            // Update the properties of the existing product with the values from the updated product
            existingProduct.setProductName(updatedProduct.getProductName());
            // Update other properties of the product

            // Save the updated product back to the repository
            Product savedProduct = productRepository.save(existingProduct);
            return savedProduct; // Return the saved product
        }
        // Handle the case when the product exists but cannot be retrieved from the repository
        throw new ProductNotFoundException("Product not found with ID: " + productId);
    }

    @Override
    public Product deleteAllProducts() throws ProductNotFoundException {
        if(productRepository.findAll().isEmpty()){
            throw new ProductNotFoundException("Product not found");
        }else{
            productRepository.deleteAll();
        }
        return null;
    }


}
