package com.javatechie.spring.camel.api.service;

import com.javatechie.spring.camel.api.dto.Order;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceTest {

    @Test
    void getOrderById() throws Exception {
        OrderService orderService = new OrderService();
        orderService.initDB();

        Order order = orderService.getOrderById(67);

        assertEquals(new Order(67, "Mobile", 6700), order);
    }

    @Test
    void initOrdersDB() throws Exception {
        OrderService orderService = new OrderService();
        orderService.initDB();
        orderService.initDB(); // 2 times for check clear DB

        List<Order> orders = orderService.getOrders();

        assertEquals(4, orders.size());

        assertEquals(new Order(67, "Mobile", 6700), orders.get(0));
        assertEquals(new Order(68, "Book", 6800), orders.get(1));
        assertEquals(new Order(69, "AC", 6900), orders.get(2));
        assertEquals(new Order(70, "Shoes", 70000), orders.get(3));
    }

    @Test
    void getOrders() throws Exception {
        OrderService orderService = new OrderService();
        orderService.initDB();

        List<Order> orders = orderService.getOrders();

        assertEquals(4, orders.size());

        assertEquals(new Order(67, "Mobile", 6700), orders.get(0));
        assertEquals(new Order(68, "Book", 6800), orders.get(1));
        assertEquals(new Order(69, "AC", 6900), orders.get(2));
        assertEquals(new Order(70, "Shoes", 70000), orders.get(3));
    }

}