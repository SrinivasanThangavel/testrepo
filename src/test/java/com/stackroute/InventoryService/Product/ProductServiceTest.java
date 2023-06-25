package com.stackroute.InventoryService.Product;


import com.stackroute.InventoryService.Product.Exception.ProductAlreadyExistsException;
import com.stackroute.InventoryService.Product.Exception.ProductNotFoundException;
import com.stackroute.InventoryService.Product.Model.Product;
import com.stackroute.InventoryService.Product.Repository.ProductRepository;
import com.stackroute.InventoryService.Product.Service.ProductServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


public class ProductServiceTest {

    Product product;
    @Mock
    ProductRepository inventoryRepo;
    @InjectMocks
    ProductServiceImpl inventoryService;
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

    @Test
    public void createStockSuccess() throws ProductAlreadyExistsException {
        when(inventoryRepo.save((Product) any())).thenReturn(product);
        Product savedStock = inventoryService.addProduct(product);
        assertEquals(product, savedStock);
        verify(inventoryRepo, times(1)).save(product);
    }


    @Test
    public void getAllStocks() {
        inventoryRepo.save(product);
        when(inventoryRepo.findAll()).thenReturn(list);
        List<Product> stockList = inventoryService.getAllProducts();
        assertEquals(list, stockList);
        verify(inventoryRepo, times(1)).save(product);
        verify(inventoryRepo, times(1)).findAll();
    }

    @Test
    public void getStocksByIdSuccess() throws ProductNotFoundException {
        when(inventoryRepo.existsById(product.getProductId())).thenReturn(true);
        when(inventoryRepo.findById(product.getProductId())).thenReturn(Optional.of(product));
        Product stock = inventoryService.getProductById(product.getProductId());
        assertEquals(product, stock);
        verify(inventoryRepo, times(1)).existsById(product.getProductId());
        verify(inventoryRepo, times(1)).findById(product.getProductId());
    }

    @Test(expected = ProductNotFoundException.class)
    public void getStocksByIdFailure() throws ProductNotFoundException {
        when(inventoryRepo.existsById(product.getProductId())).thenReturn(false);
        when(inventoryRepo.findById(product.getProductId())).thenReturn(Optional.of(product));
        Product stock = inventoryService.getProductById(product.getProductId());
        assertEquals(product, stock);
        verify(inventoryRepo, times(1)).existsById(product.getProductId());
        verify(inventoryRepo, times(1)).findById(product.getProductId());

    }


    @Test
    public void deleteStockByIdSuccess() throws ProductNotFoundException{


        // Arrange
        when(inventoryRepo.existsById(product.getProductId())).thenReturn(true);
        when(inventoryRepo.findById(product.getProductId())).thenReturn(Optional.of(product));

        // Act
        Product stock = inventoryService.deleteProductById(product.getProductId());

        // Assert
        assertEquals(product, stock);
        verify(inventoryRepo, times(1)).existsById(product.getProductId());
        verify(inventoryRepo, times(1)).findById(product.getProductId());
        verify(inventoryRepo, times(1)).deleteById(product.getProductId());
    }

    @Test(expected = ProductNotFoundException.class)
    public void deleteStockByIdFailure() throws ProductNotFoundException {
        when(inventoryRepo.existsById(product.getProductId())).thenReturn(false);
        when(inventoryRepo.findById(product.getProductId())).thenReturn(Optional.of(product));
        Product stock = inventoryService.deleteProductById(product.getProductId());
        assertEquals(product, stock);
        verify(inventoryRepo, times(1)).existsById(product.getProductId());
        verify(inventoryRepo, times(1)).findById(product.getProductId());
    }



    @Test
    public void updateStockByIdSuccess() throws ProductNotFoundException {
        // Create a new Product object
        Product product = new Product();
        product.setProductId("1");
        product.setProductName("Apple");
        // Set other properties of the product

        // Arrange
        when(inventoryRepo.existsById(product.getProductId())).thenReturn(true);
        when(inventoryRepo.findById(product.getProductId())).thenReturn(Optional.of(product));
        when(inventoryRepo.save(any(Product.class))).thenReturn(product); // Stub the save method

        // Act
        Product stock = inventoryService.updateProductById(product.getProductId(), product);

        // Assert
        Assertions.assertEquals(product, stock);
        verify(inventoryRepo, times(1)).existsById(product.getProductId());
        verify(inventoryRepo, times(1)).findById(product.getProductId());
        verify(inventoryRepo, times(1)).save(any(Product.class)); // Verify save occurred
        verify(inventoryRepo, times(0)).deleteById(any()); // Verify no deletion occurred
    }

    @Test(expected = ProductNotFoundException.class)
    public void updateStockByIdFailure() throws ProductNotFoundException {
        when(inventoryRepo.existsById(product.getProductId())).thenReturn(false);
        when(inventoryRepo.findById(product.getProductId())).thenReturn(Optional.of(product));
        Product stock = inventoryService.updateProductById(product.getProductId(), product);
        assertEquals(product, stock);
        verify(inventoryRepo, times(1)).existsById(product.getProductId());
        verify(inventoryRepo, times(1)).findById(product.getProductId());
    }

}
