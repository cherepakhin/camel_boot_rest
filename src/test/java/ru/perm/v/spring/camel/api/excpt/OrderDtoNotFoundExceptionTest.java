package ru.perm.v.spring.camel.api.excpt;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OrderDtoNotFoundExceptionTest {
    @Test
    void checkMessage() {
        assertEquals("Order with id=100 NOT FOUND", new OrderDtoNotFoundException(100).getMessage());
    }
}