package com.javatechie.spring.camel.api.service;

import com.javatechie.spring.camel.api.dto.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    Logger log = LoggerFactory.getLogger(OrderService.class);

    private List<Order> list = new ArrayList<>();

    @PostConstruct
    public void initDB() {
        log.info(String.format("initDB"));
        list.add(new Order(67, "Mobile", 6700));
        list.add(new Order(68, "Book", 6800));
        list.add(new Order(69, "AC", 6900));
        list.add(new Order(70, "Shoes", 70000));
    }

    public Order addOrder(Order order) {
        log.info(String.format("addOrder %s", order));
        list.add(order);
        return order;
    }

    public List<Order> getOrders() {
        log.info("OrderService.getOrders()");
        return list;
    }

}
