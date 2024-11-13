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