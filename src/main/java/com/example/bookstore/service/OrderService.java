package com.example.bookstore.service;

import com.example.bookstore.dao.model.Order;
import com.example.bookstore.exception.ResourceNotFoundException;

import java.util.List;

public interface OrderService {

    List<Order> list();

    Order create(Order order);

    Order get(Integer orderId) throws ResourceNotFoundException;

    Order update(Order order, Integer orderId) throws ResourceNotFoundException;

    Order delete(Integer orderId) throws ResourceNotFoundException;
}
