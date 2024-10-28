package ru.perm.v.spring.camel.api.service.impl;

import ru.perm.v.spring.camel.api.dto.OrderDTO;
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

    private List<OrderDTO> list = new ArrayList<>();

    @PostConstruct
    public void initDB() {
        list = new ArrayList<>();
        log.info("initDB");
        list.add(new OrderDTO(67, "Mobile", 6700));
        list.add(new OrderDTO(68, "Book", 6800));
        list.add(new OrderDTO(69, "AC", 6900));
        list.add(new OrderDTO(70, "Shoes", 70000));
    }


    @Override
    public OrderDTO addOrder(OrderDTO order) throws Exception {
        //TODO: verify price < 0
        //TODO: verify name is empty
        if (order == null) throw new Exception("OrderDTO for ADD is null.");
        log.info("addOrder {}", order);
        list.add(order);
        return order;
    }


    @Override
    public List<OrderDTO> getOrders() {
        log.info("OrderService.getOrders()");
        return list;
    }

    @Override
    public OrderDTO getOrderById(int id) throws Exception {
        log.info("OrderService.getOrderById({})", id);
        List<OrderDTO> filtered = list.stream().filter(order -> order.getId() == id).collect(Collectors.toList());
        if (filtered.isEmpty()) {
            log.error(format("Order with id=%s NOT FOUND", id));
            throw new Exception(format("Order with id=%s NOT FOUND", id));
        } else {
            return filtered.get(0);
        }
    }

    @Override
    public String resetDB() {
        initDB();
        return "OK";
    }


}
