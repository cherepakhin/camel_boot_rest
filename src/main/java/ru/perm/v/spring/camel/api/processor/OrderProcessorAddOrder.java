package ru.perm.v.spring.camel.api.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.hibernate.validator.internal.engine.ConstraintViolationImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import ru.perm.v.spring.camel.api.dto.OrderDTO;
import ru.perm.v.spring.camel.api.excpt.OrderDtoEmptyNameException;
import ru.perm.v.spring.camel.api.excpt.OrderDtoNegativePriceException;
import ru.perm.v.spring.camel.api.excpt.OrderDtoNullException;
import ru.perm.v.spring.camel.api.service.OrderService;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;


@Component
public class OrderProcessorAddOrder implements Processor {

    Logger logger = LoggerFactory.getLogger(OrderProcessorAddOrder.class);
    Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    private OrderService orderService;

    public OrderProcessorAddOrder(@Autowired OrderService service) {
        this.orderService = service;
    }

    @Override
    public void process(Exchange exchange) throws ResponseStatusException {
        logger.info("From processor exchange.toString()={}", exchange);
        // log: From processor exchange.toString()=Exchange[ID-vasi-note-1729924179587-0-1]

        logger.info("From processor exchange.getIn().getBody()={}", exchange.getIn().getBody());
        // log: From processor exchange.getIn().getBody()=OrderDTO(id=70, name=Shoes, price=70000.0)

        logger.info("From processor exchange.getContext()={}", exchange.getContext());
        // log: From processor exchange.getContext()=SpringCamelContext(camel-1) with spring id application

        //getIn().getBody(OrderDTO.class) - UNMARSHALL, CONVERTING to !!!OrderDTO.class!!!
        logger.info("From processor: {}", exchange.getIn().getBody(OrderDTO.class));
        //log: From processor:OrderDTO(id=70, name=Shoes, price=70000.0)

        logger.info("Headers: {}", exchange.getIn().getHeaders());
        // Headers:
        // accept=application/json, */*, accept-encoding=gzip, deflate,
        // breadcrumbId=ID-vasi-note-1729924800419-0-1,
        // CamelHttpCharacterEncoding=UTF-8,
        // CamelHttpMethod=POST,
        // CamelHttpPath=,
        // CamelHttpQuery=null,
        // CamelHttpServletRequest=org.apache.catalina.connector.RequestFacade@2916767b,
        // CamelHttpServletResponse=org.apache.catalina.connector.ResponseFacade@5a00e0a4,
        // CamelHttpUri=/addOrder,
        // CamelHttpUrl=http://localhost:9090/addOrder,
        // CamelServletContextPath=/addOrder,
        // connection=keep-alive,
        // content-length=54,
        // Content-Type=application/json,
        // host=localhost:9090,
        // user-agent=HTTPie/0.9.8}

        logger.info("CamelHttpUri={}", exchange.getIn().getHeaders().get("CamelHttpUri"));
        // CamelHttpUri=/addOrder

        logger.info("Content-Type={}", exchange.getIn().getHeaders().get("Content-Type"));
        // Content-Type=application/json

        OrderDTO dto = exchange.getIn().getBody(OrderDTO.class); // extract DTO (convert, unmarshal, ...)

        //validate
        Set<ConstraintViolation<OrderDTO>> violations = validator.validate(dto);
        if (!violations.isEmpty()) {
            logger.error("-------------------");
            StringBuilder errors = new StringBuilder();
            for (ConstraintViolation<OrderDTO> violation : violations) {
                logger.error(violation.getMessage());
                errors.append("\n").append(violation.getMessage());
            }
            throw new ResponseStatusException(HttpStatus.BAD_GATEWAY, errors.toString(), new Exception(errors.toString()));
        } else {
            try {
                orderService.addOrder(dto);
            } catch (OrderDtoNullException | OrderDtoNegativePriceException | OrderDtoEmptyNameException e) {
                throw new ResponseStatusException(HttpStatus.BAD_GATEWAY, e.getMessage(), e);
            }
        }

    }
}
