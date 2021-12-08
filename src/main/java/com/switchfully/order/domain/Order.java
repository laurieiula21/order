package com.switchfully.order.domain;

import com.switchfully.order.domain.exceptions.InvalidFieldValueException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Order {

    private String id;

    private Customer customer;

    private final List<ItemGroup> itemGroupList = new ArrayList<>();

    private double totalPrice = 0;


    private Order(Customer customer, List<ItemGroup> itemGroupList) {
        this.id = UUID.randomUUID().toString();
        this.customer = customer;
        if(itemGroupList != null){
            this.itemGroupList.addAll(itemGroupList);
        }
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<ItemGroup> getItemGroupList() {
        return itemGroupList;
    }

    public String getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public static class OrderBuilder{

        private Customer customer;
        private List<ItemGroup> itemGroupList;

        public OrderBuilder setCustomer(Customer customer) {
            if (customer == null){
                throw new InvalidFieldValueException("Customer can't be null");
            }
            this.customer = customer;
            return this;
        }

        public OrderBuilder setItemGroupList(List<ItemGroup> itemGroupList) {
            if (itemGroupList == null){
                throw new InvalidFieldValueException("Itemgroup list can't be null");
            }
            this.itemGroupList = itemGroupList;
            return this;
        }

        public Order createOrder() {
            return new Order(customer, itemGroupList);
        }
    }
}
