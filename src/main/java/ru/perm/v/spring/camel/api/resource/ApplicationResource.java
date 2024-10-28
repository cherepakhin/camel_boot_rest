package ru.perm.v.spring.camel.api.resource;

import ru.perm.v.spring.camel.api.dto.OrderDTO;
import ru.perm.v.spring.camel.api.processor.OrderProcessor;
import ru.perm.v.spring.camel.api.service.OrderService;
import org.apache.camel.BeanInject;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

@Component
public class ApplicationResource extends RouteBuilder {

    //TODO: rename Order to OrderDTO

    @BeanInject
    private OrderService orderService;

    @BeanInject
    private OrderProcessor processor;

    @Override
    public void configure() throws Exception {
        restConfiguration().component("servlet").port(9090).host("localhost")
                .bindingMode(RestBindingMode.json);

        rest().get("/hello-world")
                .produces(MediaType.APPLICATION_JSON_VALUE)
                .route()
                .setBody(constant("Hello world")).log("Get /hello-world").endRest();
        rest().get("/reset_db")
                .route()
                .setBody(() -> orderService.resetDB()).endRest();

        rest().get("/getOrders")
                .produces(MediaType.APPLICATION_JSON_VALUE)
                .route()
                .setBody(() -> orderService.getOrders())
                .log("Get /getOrders")
                .endRest();

        rest().get("/getOrder/{id}")
                .consumes(MediaType.APPLICATION_JSON_VALUE)
                .produces(MediaType.APPLICATION_JSON_VALUE)
                .outType(OrderDTO.class)
                .route()
                .log("Header ${header.id}")
                .to("bean:orderServiceImpl?method=getOrderById(${header.id})")
                .endRest();

        // test request: $ http POST :9090/addOrder < new_order.json
        rest().bindingMode(RestBindingMode.json)
                .consumes(MediaType.APPLICATION_JSON_VALUE)
                .produces(MediaType.APPLICATION_JSON_VALUE)
                .post("/addOrder")
                .type(OrderDTO.class)
                .outType(OrderDTO.class)
                .route()
                .log("Log POST in ApplicationResource /addOrder body=${body}")
                .log("Extracted OrderDTO from body: body.ID=${body.id}, body.NAME=${body.name}, body.PRICE=${body.price}")
                // to processor sending Exchange with body (exchange.getIn().getBody(OrderDTO.class))
                // exchange = body + status + ...
                .process(processor)
                .endRest();
    }

}
