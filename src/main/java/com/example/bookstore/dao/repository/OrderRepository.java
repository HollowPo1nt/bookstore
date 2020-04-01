package com.example.bookstore.dao.repository;

import com.example.bookstore.dao.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {

}
