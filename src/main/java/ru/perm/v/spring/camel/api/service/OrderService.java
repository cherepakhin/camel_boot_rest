package ru.perm.v.spring.camel.api.service;

import ru.perm.v.spring.camel.api.dto.Order;

import java.util.List;

public interface OrderService {

    Order addOrder(Order order);

    List<Order> getOrders();

    Order getOrderById(int id) throws Exception;
}
