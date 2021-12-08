package com.switchfully.order.api.dtos;

import com.switchfully.order.domain.Customer;
import com.switchfully.order.domain.ItemGroup;

import java.util.List;

public class OrderDto {

    private String id;
    private Customer customer;
    private List<ItemGroup> itemGroupList;
    private double totalPrice;

    private OrderDto(String id, Customer customer, List<ItemGroup> itemGroupList, double totalPrice) {
        this.id = id;
        this.customer = customer;
        this.itemGroupList = itemGroupList;
        this.totalPrice = totalPrice;
    }

    public String getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public List<ItemGroup> getItemGroupList() {
        return itemGroupList;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public static class OrderDtoBuilder{

        private String id;
        private Customer customer;
        private List<ItemGroup> itemGroupList;
        private double totalPrice;

        public OrderDtoBuilder setId(String id) {
            this.id = id;
            return this;
        }

        public OrderDtoBuilder setCustomer(Customer customer) {
            this.customer = customer;
            return this;
        }

        public OrderDtoBuilder setItemGroupList(List<ItemGroup> itemGroupList) {
            this.itemGroupList = itemGroupList;
            return this;
        }

        public OrderDtoBuilder setTotalPrice(double totalPrice) {
            this.totalPrice = totalPrice;
            return this;
        }

        public OrderDto createOrderDto() {
            return new OrderDto(id, customer, itemGroupList, totalPrice);
        }
    }
}
