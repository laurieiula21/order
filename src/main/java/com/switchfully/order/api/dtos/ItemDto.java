package com.switchfully.order.api.dtos;

public class ItemDto {

    private String id;
    private String name;
    private String description;
    private double price;
    private int amount;

    private ItemDto(String id, String name, String description, double price, int amount) {
        this.id = id;
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

    public static class ItemDtoBuilder{

        private String id;
        private String name;
        private String description;
        private double price;
        private int amount;

        public ItemDtoBuilder setId(String id) {
            this.id = id;
            return this;
        }

        public ItemDtoBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public ItemDtoBuilder setDescription(String description) {
            this.description = description;
            return this;
        }

        public ItemDtoBuilder setPrice(double price) {
            this.price = price;
            return this;
        }

        public ItemDtoBuilder setAmount(int amount) {
            this.amount = amount;
            return this;
        }

        public ItemDto createItemDto() {
            return new ItemDto(id, name, description, price, amount);
        }
    }
}
