package com.switchfully.order.api.dtos;

import com.switchfully.order.domain.Address;

public class CustomerDto {

    private String id;
    private String firstname;
    private String lastname;
    private String email;
    private Address address;
    private String phoneNumber;

    public CustomerDto(String id, String firstname, String lastname, String email, Address address, String phoneNumber) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public String getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
