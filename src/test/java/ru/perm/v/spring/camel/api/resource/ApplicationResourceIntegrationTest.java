package ru.perm.v.spring.camel.api.resource;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.perm.v.spring.camel.api.CamelRestDslApplication;
import ru.perm.v.spring.camel.api.dto.OrderDTO;
import ru.perm.v.spring.camel.api.service.OrderService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

//@Disabled
//@EnableAutoConfiguration // use props from application main/resource/application.properties.
// If need use test param - create test/resource/application.properties
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

//@ContextConfiguration
@ExtendWith(SpringExtension.class) // for use JUNIT5
@ContextConfiguration(classes = {CamelRestDslApplication.class})
@ActiveProfiles("dev")
@Tag("integration")
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

    @Test
    void orderAdd() {
        OrderDTO dto = new OrderDTO(100, "NAME_100", 100);
        int sizeOrdersBefore = new ArrayList<>(orderService.getOrders()).size();
        String result = this.restTemplate.postForObject("http://localhost:" + port + "/addOrder", dto, String.class);

        assertEquals("{\"id\":100,\"name\":\"NAME_100\",\"price\":100}", result);

        List<OrderDTO> ordersAfter = orderService.getOrders();

        assertEquals(sizeOrdersBefore + 1, ordersAfter.size());
        assertTrue(ordersAfter.contains(dto));
    }

    @Test
    void orderAddWithNegativePrice() {
        OrderDTO dto = new OrderDTO(100, "NAME_100", -100);
        int sizeOrdersBefore = new ArrayList<>(orderService.getOrders()).size();
        String result = this.restTemplate.postForObject("http://localhost:" + port + "/addOrder", dto, String.class);
        System.out.println(result);
        assertTrue(result.contains("Price must be higher than 1"));

        List<OrderDTO> ordersAfter = orderService.getOrders();

        assertEquals(sizeOrdersBefore , ordersAfter.size());
    }

    @Test
    void orderAddWithEmptyName() {
        OrderDTO dto = new OrderDTO(100, "", 100);
        int sizeOrdersBefore = new ArrayList<>(orderService.getOrders()).size();
        String result = this.restTemplate.postForObject("http://localhost:" + port + "/addOrder", dto, String.class);
        System.out.println(result);
        assertTrue(result.contains("The name must be longer than 5 characters"));

        List<OrderDTO> ordersAfter = orderService.getOrders();

        assertEquals(sizeOrdersBefore , ordersAfter.size());
    }

}