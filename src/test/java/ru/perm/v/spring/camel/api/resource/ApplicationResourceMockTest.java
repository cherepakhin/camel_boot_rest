package ru.perm.v.spring.camel.api.resource;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import ru.perm.v.spring.camel.api.dto.OrderDTO;
import ru.perm.v.spring.camel.api.service.OrderService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import com.fasterxml.jackson.databind.type.TypeFactory;

@EnableAutoConfiguration
// use props from application main/resource/application.properties. If need test param - create test/resource/application.properties
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ApplicationResourceMockTest {
    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate restTemplate;
    @MockBean
    OrderService orderService;

    @Test
    void restHelloWorld() {
        String result = this.restTemplate.getForObject("http://localhost:" + port + "/hello-world", String.class);

        assertEquals("\"Hello world\"", result);
    }

    @Test
    void restResetDB() {
        when(orderService.resetDB()).thenReturn("TEST OK");
        String result = this.restTemplate.getForObject("http://localhost:" + port + "/reset_db", String.class);

        assertEquals("\"TEST OK\"", result);
    }
    @Test
    void getOrders() {
        OrderDTO orderDTO1 = new OrderDTO(1,"NAME_1", 10);
        OrderDTO orderDTO2 = new OrderDTO(2,"NAME_2", 20);
        List<OrderDTO> orders = List.of(orderDTO1, orderDTO2);
        when(orderService.getOrders()).thenReturn(orders);

        String json = this.restTemplate.getForObject("http://localhost:" + port + "/get_orders", String.class);
        ObjectMapper mapper = new ObjectMapper();
        List<OrderDTO> receivedOrders = new ArrayList<>();
        try {
            receivedOrders = mapper.readValue(json, TypeFactory.defaultInstance().constructCollectionType(List.class, OrderDTO.class));
        } catch (JsonProcessingException e) {
            fail();
        }

        assertEquals(2, receivedOrders.size());
        assertEquals(orderDTO1, receivedOrders.get(0));
        assertEquals(orderDTO2, receivedOrders.get(1));
    }
}