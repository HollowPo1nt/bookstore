package com.example.bookstore.service;

import com.example.bookstore.dao.model.Order;
import com.example.bookstore.dao.repository.OrderRepository;
import com.example.bookstore.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<Order> list() {
        return orderRepository.findAll();
    }

    @Override
    public Order create(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Order get(Integer orderId) throws ResourceNotFoundException {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(()->orderNotFound(orderId));
        return order;
    }

    @Override
    public Order update(Order updated, Integer orderId) throws ResourceNotFoundException {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(()->orderNotFound(orderId));
        order.setUserFirstName(updated.getUserFirstName());
        order.setUserLastName(updated.getUserLastName());
        order.setAddress(updated.getAddress());
        order.setQuantity(updated.getQuantity());
        order.setBook(order.getBook());
        return orderRepository.save(order);
    }

    @Override
    public Order delete(Integer orderId) throws ResourceNotFoundException {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(()->orderNotFound(orderId));
        orderRepository.delete(order);
        return order;
    }

    ResourceNotFoundException orderNotFound(Integer id) {
        return new ResourceNotFoundException("Order with id = " + id + "NOT_FOUND");
    }
}
