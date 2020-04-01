package com.example.bookstore.controller;

import com.example.bookstore.dao.model.Book;
import com.example.bookstore.dao.model.Order;
import com.example.bookstore.dao.repository.OrderRepository;
import com.example.bookstore.exception.ResourceNotFoundException;
import com.example.bookstore.service.BookService;
import com.example.bookstore.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@Controller
@Slf4j
public class OrderController {

    private final OrderService orderService;
    private final BookService bookService;

    @Autowired
    public OrderController(OrderRepository orderRepository, OrderService orderService, BookService bookService) {
        this.orderService = orderService;
        this.bookService = bookService;
    }

    @GetMapping("/orders")
    public String getOrders(Model model) {
        List<Order> list = orderService.list();
        log.info("Get orders: {}", list);
        model.addAttribute("orders", list);
        return "orders";
    }

    @PostMapping("/orders")
    public String newOrder(@Valid Order order, Model model) {
        Order saved = orderService.create(order);
        log.info("Post order: {}", saved);
        return "redirect:/orders";
    }


}
