package ru.perm.v.spring.camel.api.service.impl;

import org.junit.jupiter.api.Test;
import ru.perm.v.spring.camel.api.dto.OrderDTO;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class ValidateOrderDtoTest {
    @Test
    void forNegativePrice() {
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        OrderDTO dto = new OrderDTO(1, "NAME1234", -100);

        Set<ConstraintViolation<OrderDTO>> violations = validator.validate(dto);

        assertFalse(violations.isEmpty());
        assertEquals(1, violations.size());

        List<ConstraintViolation<OrderDTO>> listViolations = new ArrayList<ConstraintViolation<OrderDTO>>(violations);
        assertEquals(1, listViolations.size());
        assertEquals("Price must be higher than 1", listViolations.get(0).getMessage());
    }

    @Test
    void errorForShortNameOrder() {
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        OrderDTO dto = new OrderDTO(1, "1234", 100);

        Set<ConstraintViolation<OrderDTO>> violations = validator.validate(dto);

        assertFalse(violations.isEmpty());
        assertEquals(1, violations.size());

        List<ConstraintViolation<OrderDTO>> listViolations = new ArrayList<ConstraintViolation<OrderDTO>>(violations);
        assertEquals(1, listViolations.size());
        assertEquals("The name must be longer than 5 characters", listViolations.get(0).getMessage());
    }

    @Test
    void errorForShortNameOrderAndNegativePrice() {
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        OrderDTO dto = new OrderDTO(1, "1234", -100);

        Set<ConstraintViolation<OrderDTO>> violations = validator.validate(dto);

        assertFalse(violations.isEmpty());
        assertEquals(2, violations.size());

        List<ConstraintViolation<OrderDTO>> listViolations = new ArrayList<ConstraintViolation<OrderDTO>>(violations);
        assertEquals(2, listViolations.size());
    }

}
