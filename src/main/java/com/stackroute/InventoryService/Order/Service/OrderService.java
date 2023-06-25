package com.stackroute.InventoryService.Order.Service;

import com.stackroute.InventoryService.Order.Exception.OrderAlreadyExistsException;
import com.stackroute.InventoryService.Order.Exception.OrderNotFoundException;
import com.stackroute.InventoryService.Order.Model.Order;

import java.util.Collection;
import java.util.List;

public interface OrderService {

    public Order addOrder(Order order) throws OrderAlreadyExistsException;

    public List<Order> getAllOrders();

    public Order getOrderById(String id) throws OrderNotFoundException;

    public Order deleteOrderById(String id) throws OrderNotFoundException;

    public Order updateOrderById(String id, Order order) throws OrderNotFoundException;

    Order updateOrderStatusById(String id, Order order) throws OrderNotFoundException;

    public Order deleteAllOrders() throws OrderNotFoundException;

    Collection<Object> getAllOrdersByUserId(String userId) throws OrderNotFoundException;

    public Collection<Object> getAllOrdersByUserIdAndStatus(String userEmailId, String status) throws OrderNotFoundException;



}
