package com.javatechie.spring.camel.api.service;

import com.javatechie.spring.camel.api.dto.Order;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceTest {

    @Test
    void getOrderById() throws Exception {
        OrderService orderService = new OrderService();
        orderService.initDB();

        Order order = orderService.getOrderById(67);

        assertEquals(new Order(67, "Mobile", 6700), order);
    }
}