package ru.perm.v.spring.camel.api.resource;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import ru.perm.v.spring.camel.api.dto.OrderDTO;
import ru.perm.v.spring.camel.api.service.OrderService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@EnableAutoConfiguration // use props from application main/resource/application.properties. If need test param - create test/resource/application.properties
@SpringBootTest
class ApplicationResourceTest {

    @Autowired
    OrderService orderService;

    // test @PostConstruct
    @Test
    void getOrdersFromPostConstruct() {
        List<OrderDTO> orders = orderService.getOrders();

        assertEquals(4, orders.size());
    }
}