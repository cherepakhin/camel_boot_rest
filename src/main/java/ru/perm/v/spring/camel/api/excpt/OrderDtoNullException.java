package ru.perm.v.spring.camel.api.excpt;

public class OrderDtoNullException extends Exception {

    public OrderDtoNullException() {
        super("OrderDTO for ADD is null.");
    }
}
