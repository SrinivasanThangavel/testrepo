package com.stackroute.InventoryService.Order.Controller;

import com.stackroute.InventoryService.Order.Exception.OrderAlreadyExistsException;
import com.stackroute.InventoryService.Order.Exception.OrderNotFoundException;
import com.stackroute.InventoryService.Order.Model.Order;
import com.stackroute.InventoryService.Order.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/v1")
public class OrderController {

    private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }


    // all the methods are implemented here
    // implement all the methods here and use ResponseEntity to return the response
    // for all use try catch block and throw exception

    @PostMapping("/order")
    public ResponseEntity<?> addOrder(@RequestBody Order order) {
        try {
            return ResponseEntity.ok(orderService.addOrder(order));
        } catch (Exception | OrderAlreadyExistsException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/order/{id}")
    public ResponseEntity<?> getOrderById(@PathVariable String id) throws OrderAlreadyExistsException {
        try {
            return ResponseEntity.ok(orderService.getOrderById(id));
        } catch (Exception | OrderNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/order")
    public ResponseEntity<?> getAllOrders() {
        try {
            return ResponseEntity.ok(orderService.getAllOrders());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/order/{id}")
    public ResponseEntity<?> deleteOrderById(@PathVariable String id) {
        try {
            return ResponseEntity.ok(orderService.deleteOrderById(id));
        } catch (Exception | OrderNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/order/{id}")
    public ResponseEntity<?> updateOrderById(@PathVariable String id, @RequestBody Order order) {
        try {
            return ResponseEntity.ok(orderService.updateOrderById(id, order));
        } catch (Exception | OrderNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @DeleteMapping("/order")
    public ResponseEntity<?> deleteAllOrders() {
        try {
            return ResponseEntity.ok(orderService.deleteAllOrders());
        } catch (Exception | OrderNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/order/user/{userId}")
    public ResponseEntity<?> getAllOrdersByUserId(@PathVariable String userId) {
        try {
            return ResponseEntity.ok(orderService.getAllOrdersByUserId(userId));
        } catch (Exception | OrderNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @GetMapping("/order/user/{userEmailId}/{status}")
    public ResponseEntity<?> getAllOrdersByUserIdAndStatus(@PathVariable String userEmailId, @PathVariable String status) {
        try {
            return ResponseEntity.ok(orderService.getAllOrdersByUserIdAndStatus(userEmailId, status));
        } catch (Exception | OrderNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/order/status/{id}")
    public ResponseEntity<?> updateOrderStatusById(@PathVariable String id, @RequestBody Order order) {
        try {
            return ResponseEntity.ok(orderService.updateOrderStatusById(id, order));
        } catch (Exception | OrderNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }



}
