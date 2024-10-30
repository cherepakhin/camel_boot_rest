package ru.perm.v.spring.camel.api.excpt;

import static java.lang.String.format;

public class OrderDtoNotFoundException extends Exception {

    public OrderDtoNotFoundException(long id) {
        super(format("Order with id=%s NOT FOUND", id));
    }
}
