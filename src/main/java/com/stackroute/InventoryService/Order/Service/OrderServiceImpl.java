package com.stackroute.InventoryService.Order.Service;

import com.stackroute.InventoryService.Order.Exception.OrderAlreadyExistsException;
import com.stackroute.InventoryService.Order.Exception.OrderNotFoundException;
import com.stackroute.InventoryService.Order.Model.Order;
import com.stackroute.InventoryService.Order.Repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

import static com.stackroute.InventoryService.Order.Model.OrderStatus.ORDERED;

@Service
public class OrderServiceImpl implements OrderService{

    private OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }

    @Override
    public Order addOrder(Order order) throws OrderAlreadyExistsException {
        if(orderRepository.existsById(order.getOrderId())){
            System.out.println("Order Already Exists !!!");
            throw new OrderAlreadyExistsException("Order Already Exists !!!");
        }else{
            order.setOrderStatus(ORDERED);
            order.setProductAvailability("Available");
            order.setProductCancellation("Not Cancelled");
            order.setProductReturnable("Not Returned");
            order.setOrderTotalPrice(order.getProductQuantity() * order.getProductPrice());
            orderRepository.save(order);
        }
        return orderRepository.save(order);
    }

    @Override
    public Order getOrderById(String id) throws OrderNotFoundException {

        if (orderRepository.existsById(id)) {
            return orderRepository.findById(id).get();
        } else {
            System.out.println("Product Not Found !!!");
            throw new OrderNotFoundException("Product Not Found !!!");
        }

    }

    @Override
    public Order deleteOrderById(String id) throws OrderNotFoundException {
        if(orderRepository.existsById(id)){
            orderRepository.deleteById(id);
        }else{
            System.out.println("Order Not Found !!!");
            throw new OrderNotFoundException("Order Not Found !!!");
        }
        return null;
    }

    @Override
    public Order updateOrderById(String id, Order order) throws OrderNotFoundException {

        Order order1 = orderRepository.findById(id).get();

            if(orderRepository.existsById(id)){

                order1.setProductName(order.getProductName());
                order1.setUserEmailId(order.getUserEmailId());
                order1.setProductQuantity(order.getProductQuantity());
                order1.setProductPrice(order.getProductPrice());
                order1.setOrderTotalPrice(order.getProductQuantity() * order.getProductPrice());

                orderRepository.save(order1);
            }else{
                System.out.println("Order Not Found !!!");
                throw new OrderNotFoundException("Order Not Found !!!");
            }
            return order;

    }

    @Override
    public Order updateOrderStatusById(String id, Order order) throws OrderNotFoundException {

        Order order1 = orderRepository.findById(id).get();

        if(orderRepository.existsById(id)){
            order1.setOrderStatus(order.getOrderStatus());
            orderRepository.save(order1);
        }else{
            System.out.println("Order Not Found !!!");
            throw new OrderNotFoundException("Order Not Found !!!");
        }
        return order;
    }

    @Override
    public Order deleteAllOrders() throws OrderNotFoundException {
        if(orderRepository.findAll().isEmpty()){
            System.out.println("Order Not Found !!!");
            throw new OrderNotFoundException("Order Not Found !!!");
        }else{
            orderRepository.deleteAll();
        }
        return null;
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
    @Override
    public Collection<Object> getAllOrdersByUserId(String userId) throws OrderNotFoundException {
        if(orderRepository.findAllByUserEmailId(userId).isEmpty()){
            System.out.println("Order Not Found !!!");
            throw new OrderNotFoundException("Order Not Found !!!");
        }else{
            return orderRepository.findAllByUserEmailId(userId);
        }
    }

    @Override
    public Collection<Object> getAllOrdersByUserIdAndStatus(String userEmailId, String status) throws OrderNotFoundException {
        if(orderRepository.findAllByUserEmailIdAndOrderStatus(userEmailId, status).isEmpty()){
            System.out.println("Order Not Found !!!");
            throw new OrderNotFoundException("Order Not Found !!!");
        }else{
            return orderRepository.findAllByUserEmailIdAndOrderStatus(userEmailId, status);
        }
    }


}
