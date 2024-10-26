package ru.perm.v.spring.camel.api.processor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.perm.v.spring.camel.api.dto.OrderDTO;
import ru.perm.v.spring.camel.api.service.OrderService;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderProcessor implements Processor {

    Logger log = LoggerFactory.getLogger(OrderProcessor.class);

    private OrderService service;

    public OrderProcessor(@Autowired OrderService service) {
        this.service = service;
    }

    @Override
    public void process(Exchange exchange) throws Exception {
        log.info(exchange.toString());
        service.addOrder(exchange.getIn().getBody(OrderDTO.class));
    }

}
