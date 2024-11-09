package ru.perm.v.spring.camel.api.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.perm.v.spring.camel.api.dto.OrderDTO;
import ru.perm.v.spring.camel.api.excpt.OrderDtoEmptyNameException;
import ru.perm.v.spring.camel.api.excpt.OrderDtoNegativePriceException;
import ru.perm.v.spring.camel.api.excpt.OrderDtoNotFoundException;
import ru.perm.v.spring.camel.api.excpt.OrderDtoNullException;
import ru.perm.v.spring.camel.api.service.OrderService;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    private List<OrderDTO> list = new ArrayList<>();

    // annotation @PostConstruct working ONLY with Spring Context. Additionally for constructor.
    // Activated after main construct, on construct Spring bean.
    @PostConstruct
    public void initDB() {
        list = new ArrayList<>();
        logger.info("initDB");
        list.add(new OrderDTO(67, "Mobile", 6700));
        list.add(new OrderDTO(68, "Book", 6800));
        list.add(new OrderDTO(69, "AC", 6900));
        list.add(new OrderDTO(70, "Shoes", 70000));
    }


    @Override
    public OrderDTO addOrder(OrderDTO order) throws OrderDtoNullException, OrderDtoNegativePriceException, OrderDtoEmptyNameException {
        if (order == null) {
            throw new OrderDtoNullException();
        }
        if(order.getName() == null || order.getName().isEmpty()) {
            throw  new OrderDtoEmptyNameException();
        }
        if (order.getPrice().floatValue() <= 0) {
            throw new OrderDtoNegativePriceException();
        }
        logger.info("addOrder {}", order);
        list.add(order);
        return order;
    }


    @Override
    public List<OrderDTO> getOrders() {
        logger.info("OrderService.getOrders()");
        return list;
    }

    @Override
    public OrderDTO getOrderById(int id) throws Exception {
        logger.info("OrderService.getOrderById({})", id);
        List<OrderDTO> filtered = list.stream().filter(order -> order.getId() == id).collect(Collectors.toList());
        if (filtered.isEmpty()) {
            throw new OrderDtoNotFoundException(id);
        } else {
            return filtered.get(0);
        }
    }

    @Override
    public String resetDB() {
        initDB();
        return "OK";
    }

    @Override
    public String deleteOrderById(int id) throws Exception {
        getOrderById(id); // throw if not found
        List<OrderDTO> filtered = list.stream().filter(orderDTO -> !orderDTO.getId().equals(id)).collect(Collectors.toList());
        list = filtered;
        return "OK";
    }
}
