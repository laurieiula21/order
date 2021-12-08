package com.switchfully.order.api.controllers;

import com.switchfully.order.api.dtos.OrderDto;
import com.switchfully.order.api.mappers.OrderMapper;
import com.switchfully.order.domain.Customer;
import com.switchfully.order.domain.Order;
import com.switchfully.order.service.CustomerService;
import com.switchfully.order.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {


    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private CustomerService customerService;

    private Logger logger = LoggerFactory.getLogger(OrderController.class);

    @PostMapping(consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public OrderDto createOrder(@RequestHeader(required = false) String authorization, @RequestBody OrderDto orderDto){
        logger.info("Create order method is starting...");

        Customer customer = customerService.validateCustomerAuthorisation(authorization);

        Order order = orderService.assignShippingDateToItemGroups(orderMapper.mapDtoToOrder(customer, orderDto));

        Order pricedOrder = orderService.assignTotalPriceToOrder(order);

        Order savedOrder = orderService.saveOrder(pricedOrder);

        OrderDto savedOrderDto = orderMapper.mapOrderToDto(savedOrder);

        logger.info("Create order method executed successfully !");

        return savedOrderDto;
    }
}
