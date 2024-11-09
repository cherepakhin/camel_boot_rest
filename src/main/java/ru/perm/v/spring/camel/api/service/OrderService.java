package ru.perm.v.spring.camel.api.service;

import ru.perm.v.spring.camel.api.dto.OrderDTO;
import ru.perm.v.spring.camel.api.excpt.OrderDtoNegativePriceException;
import ru.perm.v.spring.camel.api.excpt.OrderDtoNullException;

import java.util.List;

public interface OrderService {

    OrderDTO addOrder(OrderDTO order) throws OrderDtoNullException, OrderDtoNegativePriceException;

    List<OrderDTO> getOrders();

    OrderDTO getOrderById(int id) throws Exception;

    String resetDB();

    String deleteOrder(int id) throws Exception;
}
