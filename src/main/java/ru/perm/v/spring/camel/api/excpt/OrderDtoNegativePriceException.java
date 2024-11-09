package ru.perm.v.spring.camel.api.excpt;

public class OrderDtoNegativePriceException extends Exception {

    public OrderDtoNegativePriceException() {
        super("Price in OrderDTO <= 0.");
    }
}
