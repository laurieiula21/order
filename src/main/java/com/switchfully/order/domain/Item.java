package com.switchfully.order.domain;

import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import com.switchfully.order.domain.exceptions.InvalidFieldValueException;
import com.switchfully.order.domain.exceptions.MissingFieldException;

import java.util.UUID;

public class Item {

    private final String id;
    private String name;
    private String description;
    private double price;
    private int amount;

    private Item(String name, String description, Double price, Integer amount) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.description = description;
        this.price = price;
        this.amount = amount;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public int getAmount() {
        return amount;
    }

    public static class ItemBuilder{

        private String name;
        private String description;
        private Double price;
        private Integer amount;

        public ItemBuilder setName(String name) {
            if(name == null){
                throw new MissingFieldException("Field name is missing");
            }
            this.name = name;
            return this;
        }

        public ItemBuilder setDescription(String description) {
            if (description == null){
                throw new MissingFieldException("Field description is missing");
            }
            this.description = description;
            return this;
        }

        public ItemBuilder setPrice(Double price) {
            if (price <= 0){
                throw new InvalidFieldValueException("Price is not valid");
            }
            this.price = price;
            return this;
        }

        public ItemBuilder setAmount(Integer amount) {
            if (amount < 0){
                throw new InvalidFieldValueException("Amount is not valid");
            }
            this.amount = amount;
            return this;
        }

        public Item createItem() {
            return new Item(name, description, price, amount);
        }
    }
}
