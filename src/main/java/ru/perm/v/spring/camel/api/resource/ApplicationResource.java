package ru.perm.v.spring.camel.api.resource;

import org.apache.camel.BeanInject;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import ru.perm.v.spring.camel.api.dto.OrderDTO;
import ru.perm.v.spring.camel.api.processor.OrderProcessorAddOrder;
import ru.perm.v.spring.camel.api.service.OrderService;

@Component
public class ApplicationResource extends RouteBuilder {

    Logger logger = LoggerFactory.getLogger(ApplicationResource.class);

    @BeanInject
    private OrderService orderService;

    @BeanInject
    private OrderProcessorAddOrder orderProcessorAddOrder;

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

        // use Stream API: () -> orderService.getOrders()
        rest().get("/get_orders")
                .produces(MediaType.APPLICATION_JSON_VALUE)
                .route()
                .setBody(() -> orderService.getOrders())
                .log("Get /getOrders")
                .endRest();

        rest().get("/getOrderByIdWithProcessor/{id}")
                .produces(MediaType.APPLICATION_JSON_VALUE)
                .route()
                .process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {
                        logger.info("getHeaders().keySet().toString(): {}", exchange.getMessage().getHeaders().keySet());
                        // [accept, accept-encoding, breadcrumbId, CamelHttpCharacterEncoding, CamelHttpMethod,
                        // CamelHttpPath, CamelHttpQuery, CamelHttpServletRequest, CamelHttpServletResponse,
                        // CamelHttpUri, CamelHttpUrl, CamelServletContextPath, connection, Content-Type, host, id, user-agent]

                        logger.info("id: {}", exchange.getMessage().getHeaders().get("id"));
                        //67 for http :9090/getOrderByIdWithProcessor/67
                    }
                })
                .log("Get /getOrderById ${header.id}")
                .endRest();

        rest().get("/getOrderById/{id}")
                .produces(MediaType.APPLICATION_JSON_VALUE)
                .route()
                .to("bean:orderServiceImpl?method=getOrderById(${header.id})")
                .log("Get /getOrderById ${header.id}")
                .endRest();

        // Use Camel method "to" and use "bean"
        rest().get("/getOrder/{id}")
                .consumes(MediaType.APPLICATION_JSON_VALUE)
                .produces(MediaType.APPLICATION_JSON_VALUE)
                .outType(OrderDTO.class)
                .route()
                .log("Header ${header.id}")
                .to("bean:orderServiceImpl?method=getOrderById(${header.id})")
                .endRest();
        //POST
        // Use Camel method "process"
        // test request: $ http POST :9090/addOrder < new_order.json
        // link POST on url TO orderProcessor
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
                .process(orderProcessorAddOrder)
                .endRest();
    }

}
