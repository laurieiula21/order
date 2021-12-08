package com.switchfully.order.api.mappers;

import com.switchfully.order.api.dtos.CustomerDto;
import com.switchfully.order.api.dtos.OrderDto;
import com.switchfully.order.domain.Customer;
import com.switchfully.order.domain.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {

    public Order mapDtoToOrder(Customer customer, OrderDto orderDto) {
        return new Order.OrderBuilder()
                .setCustomer(customer)
                .setItemGroupList(orderDto.getItemGroupList())
                .createOrder();
    }

    public OrderDto mapOrderToDto(Order savedOrder) {

        return new OrderDto.OrderDtoBuilder()
                .setId(savedOrder.getId())
                .setCustomer(savedOrder.getCustomer())
                .setItemGroupList(savedOrder.getItemGroupList())
                .setTotalPrice(savedOrder.getTotalPrice())
                .createOrderDto();

    }
}
