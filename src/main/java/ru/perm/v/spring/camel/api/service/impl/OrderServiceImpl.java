package ru.perm.v.spring.camel.api.service.impl;

import ru.perm.v.spring.camel.api.dto.Order;
import ru.perm.v.spring.camel.api.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.format;

@Service
public class OrderServiceImpl implements OrderService {
    Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);

    private List<Order> list = new ArrayList<>();

    @PostConstruct
    public void initDB() {
        list = new ArrayList<>();
        log.info("initDB");
        list.add(new Order(67, "Mobile", 6700));
        list.add(new Order(68, "Book", 6800));
        list.add(new Order(69, "AC", 6900));
        list.add(new Order(70, "Shoes", 70000));
    }


    @Override
    public Order addOrder(Order order) {
        log.info("addOrder {}", order);
        list.add(order);
        return order;
    }


    @Override
    public List<Order> getOrders() {
        log.info("OrderService.getOrders()");
        return list;
    }

    @Override
    public Order getOrderById(int id) throws Exception {
        log.info("OrderService.getOrderById({})", id);
        List<Order> filtered = list.stream().filter(order -> order.getId() == id).collect(Collectors.toList());
        if (filtered.isEmpty()) {
            throw new Exception(format("Order with id=%s NOT FOUND", id));
        } else {
            return filtered.get(0);
        }
    }


}
