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

        rest().get("/hello-world").produces(MediaType.APPLICATION_JSON_VALUE)
                .route()
                .setBody(constant("Hello world")).log("Get /hello-world").endRest();

        rest().get("/getOrders").produces(MediaType.APPLICATION_JSON_VALUE)
                .route()
                .setBody(() -> orderService.getOrders())
                .log("Get /getOrders")
                .endRest();

        rest().get("/getOrder/{id}")
                .produces(MediaType.APPLICATION_JSON_VALUE)
                .outType(OrderDTO.class)
                .route()
                .log("Header ${header.id}")
                .to("bean:orderServiceImpl?method=getOrderById(${header.id})")
                .endRest();

        rest().post("/addOrder")
                .bindingMode(RestBindingMode.json) // ?? log
                .consumes(MediaType.APPLICATION_JSON_VALUE)
                .type(OrderDTO.class)
                .outType(OrderDTO.class)
                .route()
                .process(processor)
                .log("Post /addOrder")
                .endRest();
    }

}
