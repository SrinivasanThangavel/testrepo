package com.stackroute.InventoryService.Order;

import com.stackroute.InventoryService.Order.Exception.OrderNotFoundException;
import com.stackroute.InventoryService.Order.Model.Order;
import com.stackroute.InventoryService.Order.Repository.OrderRepository;
import com.stackroute.InventoryService.Order.Service.OrderServiceImpl;
import com.stackroute.InventoryService.Product.Exception.ProductNotFoundException;
import com.stackroute.InventoryService.Product.Model.Product;
import com.stackroute.InventoryService.Product.Repository.ProductRepository;
import com.stackroute.InventoryService.Product.Service.ProductService;
import com.stackroute.InventoryService.Product.Service.ProductServiceImpl;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@SpringBootTest
public class OrderServiceTest {
    @Mock
    private OrderRepository orderRepoMock;

    @InjectMocks
    private OrderServiceImpl orderService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveOrderStock() {
        // Create a new OrderStock object
        Order orderStock = new Order();
        orderStock.setOrderId("1");
        orderStock.setProductName("Product 1");

        // Mock the repository's save method
        when(orderRepoMock.save(orderStock)).thenReturn(orderStock);

        // Save the object using the repository
        Order savedStock = orderRepoMock.save(orderStock);

        // Verify that the object is saved successfully
        Assertions.assertNotNull(savedStock);
        Assertions.assertEquals(orderStock.getOrderId(), savedStock.getOrderId());
        Assertions.assertEquals(orderStock.getProductName(), savedStock.getProductName());
    }

    @Test
    public void testGetOrderStockById() throws OrderNotFoundException {
        // Create a new OrderStock object
        Order orderStock = new Order();
        orderStock.setOrderId("1");
        orderStock.setProductName("Product 1");

        // Mock the repository's findById method
        when(orderRepoMock.findById("1")).thenReturn(java.util.Optional.of(orderStock));

        // Get the object using the repository
        Order savedStock = orderRepoMock.findById("1").get();

        // Verify that the object is retrieved successfully
        assert(savedStock != null);
        assert(savedStock.getOrderId().equals(orderStock.getOrderId()));
        assert(savedStock.getProductName().equals(orderStock.getProductName()));
    }

    @org.junit.jupiter.api.Test
    public void testDeleteOrderStockById() throws OrderNotFoundException {
        // Create a new OrderStock object
        Order orderStock = new Order();
        orderStock.setOrderId("1");
        orderStock.setProductName("Product 1");

        // Mock the repository's deleteById method
        doNothing().when(orderRepoMock).deleteById("1");

        // Delete the object using the repository
        orderRepoMock.deleteById("1");

        // Verify that the object is deleted successfully
        verify(orderRepoMock, times(1)).deleteById("1");
    }

    @org.junit.jupiter.api.Test
    public void testUpdateOrderStockById() throws OrderNotFoundException {
        // Create a new OrderStock object
        Order orderStock = new Order();
        orderStock.setOrderId("1");
        orderStock.setProductName("Product 1");

        // Mock the repository's findById method
        when(orderRepoMock.findById("1")).thenReturn(java.util.Optional.of(orderStock));

        // Mock the repository's save method
        when(orderRepoMock.save(orderStock)).thenReturn(orderStock);

        // Update the object using the repository
        Order updatedStock = orderRepoMock.save(orderStock);

        // Verify that the object is updated successfully
        assert (updatedStock != null);
        assert (updatedStock.getOrderId().equals(orderStock.getOrderId()));
        assert (updatedStock.getProductName().equals(orderStock.getProductName()));


    }

    @Test
    public void testGetAllOrderStock() {
        // Create a new OrderStock object
        Order orderStock = new Order();
        orderStock.setOrderId("1");
        orderStock.setProductName("Product 1");

        // Mock the repository's findAll method
        when(orderRepoMock.findAll()).thenReturn(java.util.Collections.singletonList(orderStock));

        // Get the object using the repository
        java.util.List<Order> savedStock = orderRepoMock.findAll();

        // Verify that the object is retrieved successfully
        assert(savedStock != null);
        assert(savedStock.get(0).getOrderId().equals(orderStock.getOrderId()));
        assert(savedStock.get(0).getProductName().equals(orderStock.getProductName()));
    }
}
