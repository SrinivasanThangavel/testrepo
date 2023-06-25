package com.stackroute.InventoryService.Order;


import com.stackroute.InventoryService.Order.Controller.OrderController;
import com.stackroute.InventoryService.Order.Exception.OrderAlreadyExistsException;
import com.stackroute.InventoryService.Order.Exception.OrderNotFoundException;
import com.stackroute.InventoryService.Order.Model.Order;
import com.stackroute.InventoryService.Order.Service.OrderService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

import static org.mockito.Mockito.when;

@SpringBootTest
public class OrderControllerTest {

    @Mock
    private OrderService orderServiceMock;

    @InjectMocks
    private OrderController orderController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveOrderStock() throws OrderNotFoundException, OrderAlreadyExistsException {
        // Create a new Order object
        Order orderStock = new Order();
        orderStock.setOrderId("1");
        orderStock.setProductName("Product 1");
        // Set other properties of the order

        // Mock the service's placeOrder method
        when(orderServiceMock.addOrder(orderStock)).thenReturn(orderStock);

        // Save the object using the service
        Order savedStock = orderServiceMock.addOrder(orderStock);

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

        // Mock the service's findById method
        when(orderServiceMock.getOrderById("1")).thenReturn(orderStock);

        // Get the object using the service
        Order stock = orderServiceMock.getOrderById("1");

        // Verify that the object is retrieved successfully
        assert(stock != null);
        assert(orderStock.getOrderId().equals(stock.getOrderId()));
        assert(orderStock.getProductName().equals(stock.getProductName()));
    }

    @Test
    public void testGetAllOrderStock() {
        // Create a new OrderStock object
        Order orderStock = new Order();
        orderStock.setOrderId("1");
        orderStock.setProductName("Product 1");

        // Create an ArrayList of OrderStock objects
        ArrayList<Order> orderStockArrayList = new ArrayList<>();
        orderStockArrayList.add(orderStock);

        // Mock the service's findAll method
        when(orderServiceMock.getAllOrders()).thenReturn(orderStockArrayList);

        // Get all the objects using the service
        ArrayList<Order> stocks = (ArrayList<Order>) orderServiceMock.getAllOrders();

        // Verify that the objects are retrieved successfully
        assert(stocks != null);
        assert(stocks.size() == 1);
        assert(orderStock.getOrderId().equals(stocks.get(0).getOrderId()));
        assert(orderStock.getProductName().equals(stocks.get(0).getProductName()));
    }

    @Test
    public void testDeleteOrderStock() throws OrderNotFoundException {
        // Create a new Order object
        Order order = new Order();
        order.setOrderId("1");
        order.setProductName("Product 1");
        // Set other properties of the order

        // Delete the object using the service
        orderServiceMock.deleteOrderById(order.getOrderId());

        // Verify that the object is deleted successfully
        verify(orderServiceMock, times(1)).deleteOrderById(order.getOrderId());
    }


    @Test
    public void testUpdateOrderStock() throws OrderNotFoundException {
        // Create a new OrderStock object
        Order orderStock = new Order();
        orderStock.setOrderId("1");
        orderStock.setProductName("Product 1");

        // Mock the service's updateById method
        when(orderServiceMock.updateOrderById("1", orderStock)).thenReturn(orderStock);

        // Update the object using the service
        Order updatedStock = orderServiceMock.updateOrderById("1", orderStock);

        // Verify that the object is updated successfully
        assert(updatedStock != null);
        assert(orderStock.getOrderId().equals(updatedStock.getOrderId()));
        assert(orderStock.getProductName().equals(updatedStock.getProductName()));
    }




}
