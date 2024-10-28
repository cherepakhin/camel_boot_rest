package ru.perm.v.spring.camel.api.service.impl;

import org.junit.jupiter.api.Test;
import ru.perm.v.spring.camel.api.dto.OrderDTO;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceImplTest {
    @Test
    void initDB() {
        OrderServiceImpl service = new OrderServiceImpl();

        service.initDB();
        List<OrderDTO> orders = service.getOrders();

        assertEquals(4, orders.size());
        assertEquals(new OrderDTO(67, "Mobile", 6700), orders.get(0));
        assertEquals(new OrderDTO(68, "Book", 6800), orders.get(1));
        assertEquals(new OrderDTO(69, "AC", 6900), orders.get(2));
        assertEquals(new OrderDTO(70, "Shoes", 70000), orders.get(3));
    }

    @Test
    void resetDB() {
        OrderServiceImpl service = new OrderServiceImpl();
        String result = service.resetDB();

        assertEquals("OK", result);

        List<OrderDTO> orders = service.getOrders();
        assertEquals(4, orders.size());
    }

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
    void addOrder() {
        OrderDTO orderDTO = new OrderDTO(100, "NAME_100", 200f);
        OrderServiceImpl service = new OrderServiceImpl();
        OrderDTO createdOrder;
        try {
            createdOrder = service.addOrder(orderDTO);
        } catch (Exception e) {
            fail();
            throw new RuntimeException(e);
        }

        assertEquals(orderDTO, createdOrder);
    }

    @Test
    void addOrderCheckLenghtOrders() throws Exception {
        OrderServiceImpl orderService = new OrderServiceImpl();
        int oldLength = orderService.getOrders().size();
        OrderDTO order = orderService.addOrder(new OrderDTO(6700, "Mobile10000", 10000));

        assertEquals(new OrderDTO(6700, "Mobile10000", 10000), order);

        int newLength = orderService.getOrders().size();
        assertEquals(oldLength + 1, newLength);
    }

    @Test
    void getOrders() {
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
    void checkExceptionForNullOrder() {
        OrderServiceImpl orderService = new OrderServiceImpl();
        String errorMessage = "";
        try {
            orderService.addOrder(null);
        } catch (Exception e) {
            errorMessage = e.getMessage();
        }

        assertEquals("OrderDTO for ADD is null.", errorMessage);
    }

}