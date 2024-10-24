package com.javatechie.spring.camel.api.resource;

import org.apache.camel.BeanInject;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import com.javatechie.spring.camel.api.dto.Order;
import com.javatechie.spring.camel.api.processor.OrderProcessor;
import com.javatechie.spring.camel.api.service.OrderService;

@Component
public class ApplicationResource extends RouteBuilder {

	@Autowired
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
				.outType(Order.class)
				.route()
				.log("Header ${header.id}")
				.to("bean:orderService?method=getOrderById(${header.id})")
				.endRest();

		rest().post("/addOrder")
				.consumes(MediaType.APPLICATION_JSON_VALUE)
				.type(Order.class)
				.outType(Order.class)
				.route()
				.process(processor)
				.log("Post /addOrder")
				.endRest();
	}

}
