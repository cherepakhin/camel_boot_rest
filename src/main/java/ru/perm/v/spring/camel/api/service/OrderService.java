package ru.perm.v.spring.camel.api.service;

import ru.perm.v.spring.camel.api.dto.OrderDTO;
import ru.perm.v.spring.camel.api.excpt.OrderDtoEmptyNameException;
import ru.perm.v.spring.camel.api.excpt.OrderDtoNegativePriceException;
import ru.perm.v.spring.camel.api.excpt.OrderDtoNullException;

import java.util.List;

public interface OrderService {

    OrderDTO addOrder(OrderDTO order) throws OrderDtoNullException, OrderDtoNegativePriceException, OrderDtoEmptyNameException;

    List<OrderDTO> getOrders();

    OrderDTO getOrderById(int id) throws Exception;

    String resetDB();

    String deleteOrderById(int id) throws Exception;
}
