package ru.perm.v.spring.camel.api.excpt;

public class OrderDtoEmptyNameException extends Exception {

    public OrderDtoEmptyNameException() {
        super("Name in OrderDTO is empty.");
    }
}
