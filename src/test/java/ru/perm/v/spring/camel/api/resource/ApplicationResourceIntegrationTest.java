package ru.perm.v.spring.camel.api.resource;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import ru.perm.v.spring.camel.api.dto.OrderDTO;
import ru.perm.v.spring.camel.api.service.OrderService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Disabled
@EnableAutoConfiguration // use props from application main/resource/application.properties. If need test param - create test/resource/application.properties
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ApplicationResourceIntegrationTest {


    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    OrderService orderService;

    @Test
    void getOrdersFromPostConstruct() {
        List<OrderDTO> orders = orderService.getOrders();

        assertEquals(4, orders.size());
    }

    @Test
    void restHelloWorld() {
        String result = this.restTemplate.getForObject("http://localhost:" + port + "/hello-world", String.class);

        assertEquals("\"Hello world\"", result);
    }

    @Test
    void restResetDB() {
        String result = this.restTemplate.getForObject("http://localhost:" + port + "/reset_db", String.class);

        assertEquals("\"OK\"", result);
    }
}