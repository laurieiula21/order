package com.switchfully.order.domain;

import com.switchfully.order.domain.exceptions.InvalidFieldValueException;
import com.switchfully.order.domain.exceptions.MissingFieldException;

import java.time.LocalDate;

public class ItemGroup {

    private final String itemId;
    private final int amount;
    private LocalDate shippingDate;

    public String getItemId() {
        return itemId;
    }

    public int getAmount() {
        return amount;
    }

    public LocalDate getShippingDate() {
        return shippingDate;
    }

    private ItemGroup(String itemId, int amount, LocalDate shippingDate) {
        this.itemId = itemId;
        this.amount = amount;
        this.shippingDate = shippingDate;
    }

    public void setShippingDate(LocalDate shippingDate) {
        this.shippingDate = shippingDate;
    }

    public static class ItemGroupBuilder{

        private String itemId;
        private int amount;
        private LocalDate shippingDate;

        public ItemGroupBuilder setItemId(String itemId) {
            if (itemId == null){
                throw new MissingFieldException("Field itemId is missing");
            }
            this.itemId = itemId;
            return this;
        }

        public ItemGroupBuilder setAmount(int amount) {
            if (amount <= 0){
                throw new InvalidFieldValueException("Amount should more than 0");
            }
            this.amount = amount;
            return this;
        }

        public ItemGroupBuilder setShippingDate(LocalDate shippingDate) {
            this.shippingDate = shippingDate;
            return this;
        }

        public ItemGroup createItemGroup() {
            return new ItemGroup(itemId, amount, shippingDate);
        }
    }
}
