package com.stackroute.InventoryService.Product;


import com.stackroute.InventoryService.Product.Exception.ProductAlreadyExistsException;
import com.stackroute.InventoryService.Product.Exception.ProductNotFoundException;
import com.stackroute.InventoryService.Product.Model.Product;
import com.stackroute.InventoryService.Product.Repository.ProductRepository;
import com.stackroute.InventoryService.Product.Service.ProductService;
import com.stackroute.InventoryService.Product.Service.ProductServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertThrows;


import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ProductRepositoryTest {

    Product product;
    @Mock
    ProductRepository productRepository;
    @InjectMocks
    ProductServiceImpl productService;
    List<Product> list = null;



    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        product = new Product();
        product.setProductId("1");
        product.setProductName("Apple");
        product.setProductPrice(100.0);
        product.setProductQuantity(10.0);
        list = new ArrayList<>();
        list.add(product);
    }

    @Before
    public void setup() {
        productRepository = mock(ProductRepository.class);
        productService = new ProductServiceImpl(productRepository);
        product = new Product(/* initialize product with necessary data */);
    }


    @Test
    public void createProductSuccess() throws ProductAlreadyExistsException {
        when(productRepository.save((Product) any())).thenReturn(product);
        Product savedStock = productService.addProduct(product);
        assertEquals(product, savedStock);
        verify(productRepository, times(1)).save(product);
    }


    @Test
    public void getAllProducts() {
        productRepository.save(product);
        when(productRepository.findAll()).thenReturn(list);
        List<Product> stockList = productService.getAllProducts();
        assertEquals(list, stockList);
        verify(productRepository, times(1)).save(product);
        verify(productRepository, times(1)).findAll();
    }

    @Test
    public void getProductById() {
        // Arrange
        when(productRepository.findById("1")).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(ProductNotFoundException.class, () -> {
            productService.getProductById("1");
        });

        verify(productRepository, times(1)).findById("1");
    }



}
