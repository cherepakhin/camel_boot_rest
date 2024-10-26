package ru.perm.v.spring.camel.api.service;

import ru.perm.v.spring.camel.api.dto.OrderDTO;

import java.util.List;

public interface OrderService {

    OrderDTO addOrder(OrderDTO order);

    List<OrderDTO> getOrders();

    OrderDTO getOrderById(int id) throws Exception;
}
