package com.switchfully.order.service;

import com.switchfully.order.domain.ItemGroup;
import com.switchfully.order.domain.Order;
import com.switchfully.order.domain.exceptions.InvalidItemException;
import com.switchfully.order.repository.ItemRepository;
import com.switchfully.order.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@Service
public class OrderService {

    private OrderRepository orderRepository;
    private ItemService itemService;

    @Autowired
    public OrderService(OrderRepository orderRepository, ItemService itemService) {
        this.orderRepository = orderRepository;
        this.itemService = itemService;
    }


    public double getTotalPrice(List<ItemGroup> itemGroupList){
        return itemGroupList.stream()
                .mapToDouble(itemGroup -> itemService.getItemById(itemGroup.getItemId()).getPrice() * itemGroup.getAmount())
                .sum();
    }

    public Order assignTotalPriceToOrder(Order order){
        order.setTotalPrice(getTotalPrice(order.getItemGroupList()));
        return order;
    }

    public Order assignShippingDateToItemGroups(Order order){
        LocalDate shippingDate;
        for (ItemGroup itemGroup : order.getItemGroupList()) {
            if (itemService.getItemById(itemGroup.getItemId()) == null){
                throw new InvalidItemException("The item you are trying to add does not exist");
            }
            if (itemGroup.getAmount() <= itemService.getItemById(itemGroup.getItemId()).getAmount()){
                shippingDate = LocalDate.now().plusDays(1);
            }else {
                shippingDate = LocalDate.now().plusDays(7);
            }
            itemGroup.setShippingDate(shippingDate);
        }
        return order;
    }


    public Order saveOrder(Order orderToSave) {
        if(orderToSave == null){
            throw new NullPointerException("The order is empty and therefore can noy be saved");
        }
        return orderRepository.addOrder(orderToSave);
    }
}
