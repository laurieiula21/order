package com.switchfully.order.domain;

public class Address {

    private final int number;
    private final String street;
    private final int postCode;
    private final String city;
    private final String country;

    public Address(int number, String street, int postCode, String city, String country) {
        this.number = number;
        this.street = street;
        this.postCode = postCode;
        this.city = city;
        this.country = country;
    }

    public int getNumber() {
        return number;
    }

    public String getStreet() {
        return street;
    }

    public int getPostCode() {
        return postCode;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }
}
