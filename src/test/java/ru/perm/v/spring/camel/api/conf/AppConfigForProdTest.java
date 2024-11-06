package ru.perm.v.spring.camel.api.conf;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.perm.v.spring.camel.api.CamelRestDslApplication;
import ru.perm.v.spring.camel.api.dto.OrderDTO;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {CamelRestDslApplication.class})
// for junit4
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,classes = CamelRestDslApplication.class)
@ActiveProfiles("PROD")
class AppConfigForProdTest {
    @Autowired
    @Qualifier("defaultOrderDTO")
    OrderDTO orderDTO;

    @Test
    void checkBeanFromConfig() {
        assertEquals("PROD_NULL_ORDER", orderDTO.getName());
    }
}
