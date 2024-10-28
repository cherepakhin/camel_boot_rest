package ru.perm.v.spring.camel.api.service.impl;

import org.junit.jupiter.api.Test;
import ru.perm.v.spring.camel.api.dto.OrderDTO;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceImplTest {

    @Test
    void resetDB() {
        OrderServiceImpl service = new OrderServiceImpl();
        String result = service.resetDB();

        assertEquals("OK", result);

        List<OrderDTO> orders = service.getOrders();
        assertEquals(4, orders.size());
    }
}