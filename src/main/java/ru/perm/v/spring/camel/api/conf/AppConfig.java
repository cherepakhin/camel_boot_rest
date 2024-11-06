package ru.perm.v.spring.camel.api.conf;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import ru.perm.v.spring.camel.api.dto.OrderDTO;

@Configuration
// указать активный профиль, используя System Property -Dspring.profiles.active = DEV
public class AppConfig {
    @Bean("defaultOrderDTO")
    @Profile("DEV")
    public OrderDTO devDefaultOrderDTO() {
        return new OrderDTO(-1,"DEV_NULL_ORDER", 0);
    }

    @Bean("defaultOrderDTO")
    @Profile("PROD")
    public OrderDTO prodDefaultOrderDTO() {
        return new OrderDTO(-1,"PROD_NULL_ORDER", 0);
    }
}
