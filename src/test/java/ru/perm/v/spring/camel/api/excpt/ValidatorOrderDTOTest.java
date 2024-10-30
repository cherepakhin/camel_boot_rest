package ru.perm.v.spring.camel.api.excpt;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.perm.v.spring.camel.api.dto.OrderDTO;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ValidatorOrderDTOTest {
    private static Validator validator;

    @BeforeAll
    public static void setupValidatorInstance() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    void nameIsNull() {
        OrderDTO orderDTO = new OrderDTO();
        List<ConstraintViolation<OrderDTO>> validations = validator.validate(orderDTO).stream().collect(Collectors.toList());

        assertEquals(1, validations.size());
        ConstraintViolation<OrderDTO> validation = validations.get(0);
        assertEquals("Name may not be null", validation.getMessage());
    }
}
