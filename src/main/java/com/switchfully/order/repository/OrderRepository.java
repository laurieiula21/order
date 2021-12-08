package com.switchfully.order.repository;

import com.switchfully.order.domain.Order;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class OrderRepository {

    private Map<String, Order> orderList = new HashMap<>();

    public Order addOrder(Order orderToSave) {
        orderList.put(orderToSave.getId(), orderToSave);
        return orderList.get(orderToSave.getId());
    }
}
