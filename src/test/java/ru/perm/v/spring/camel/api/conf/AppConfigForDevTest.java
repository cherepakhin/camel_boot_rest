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

// this for JUnit5
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {CamelRestDslApplication.class})

// DON`T DELETE COMMENT
// this for JUNIT4!!! this prject use JUnit5
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
//        classes = CamelRestDslApplication.class)
@ActiveProfiles("DEV")
class AppConfigForDevTest {
    @Autowired
    @Qualifier("defaultOrderDTO")
    OrderDTO orderDTO;

    @Test
    void checkBeanFromConfig() {
        assertEquals("DEV_NULL_ORDER", orderDTO.getName());
    }
}
