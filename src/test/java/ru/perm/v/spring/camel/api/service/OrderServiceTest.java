package ru.perm.v.spring.camel.api.service;

import ru.perm.v.spring.camel.api.dto.OrderDTO;
import ru.perm.v.spring.camel.api.service.impl.OrderServiceImpl;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceTest {

    @Test
    void getOrderById() throws Exception {
        OrderServiceImpl orderService = new OrderServiceImpl();
        orderService.initDB();

        OrderDTO order = orderService.getOrderById(67);

        assertEquals(new OrderDTO(67, "Mobile", 6700), order);
    }

    @Test
    void getOrderByNotExistId() throws Exception {
        OrderServiceImpl orderService = new OrderServiceImpl();
        orderService.initDB();
        boolean isError = false;
        try {
            orderService.getOrderById(-100);
        } catch (Exception e) {
            isError = true;
            assertEquals("Order with id=-100 NOT FOUND", e.getMessage());
        }

        assertTrue(isError);
    }

    @Test
    void initOrdersDB() throws Exception {
        OrderServiceImpl orderService = new OrderServiceImpl();
        orderService.initDB();
        orderService.initDB(); // 2 times for check clear DB

        List<OrderDTO> orders = orderService.getOrders();

        assertEquals(4, orders.size());

        assertEquals(new OrderDTO(67, "Mobile", 6700), orders.get(0));
        assertEquals(new OrderDTO(68, "Book", 6800), orders.get(1));
        assertEquals(new OrderDTO(69, "AC", 6900), orders.get(2));
        assertEquals(new OrderDTO(70, "Shoes", 70000), orders.get(3));
    }

    @Test
    void getOrders() throws Exception {
        OrderServiceImpl orderService = new OrderServiceImpl();
        orderService.initDB();

        List<OrderDTO> orders = orderService.getOrders();

        assertEquals(4, orders.size());

        assertEquals(new OrderDTO(67, "Mobile", 6700), orders.get(0));
        assertEquals(new OrderDTO(68, "Book", 6800), orders.get(1));
        assertEquals(new OrderDTO(69, "AC", 6900), orders.get(2));
        assertEquals(new OrderDTO(70, "Shoes", 70000), orders.get(3));
    }

    @Test
    void addOrder() throws Exception {
        OrderServiceImpl orderService = new OrderServiceImpl();
        int oldLength = orderService.getOrders().size();
        OrderDTO order = orderService.addOrder(new OrderDTO(6700, "Mobile10000", 10000));

        assertEquals(new OrderDTO(6700, "Mobile10000", 10000), order);

        int newLength = orderService.getOrders().size();
        assertEquals(oldLength + 1, newLength);
    }

}