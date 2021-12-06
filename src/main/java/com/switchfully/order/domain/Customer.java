package com.switchfully.order.domain;

import com.switchfully.order.domain.exceptions.MissingFieldException;

import java.util.Objects;
import java.util.UUID;

public class Customer {

    private final String id;
    private final String firstname;
    private final String lastname;
    private final String email;
    private final Address address;
    private final String phoneNumber;

    private Customer(String firstname, String lastname, String email, Address address, String phoneNumber) {
        this.id = UUID.randomUUID().toString();
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

    public static class CustomerBuilder {

        private String firstname;
        private String lastname;
        private String email;
        private Address address;
        private String phoneNumber;

        public CustomerBuilder setFirstname(String firstname) {
            if(firstname == null){
                throw new MissingFieldException("Field firstname is missing");
            }
            this.firstname = firstname;
            return this;
        }

        public CustomerBuilder setLastname(String lastname) {
            if(lastname == null){
                throw new MissingFieldException("Field lastname is missing");
            }
            this.lastname = lastname;
            return this;
        }

        public CustomerBuilder setEmail(String email) {
            if(email == null){
                throw new MissingFieldException("Field email is missing");
            }
            this.email = email;
            return this;
        }

        public CustomerBuilder setAddress(Address address) {
            if(address == null){
                throw new MissingFieldException("Field address is missing");
            }
            this.address = address;
            return this;
        }

        public CustomerBuilder setPhoneNumber(String phoneNumber) {
            if(phoneNumber == null){
                throw new MissingFieldException("Field phoneNumber is missing");
            }
            this.phoneNumber = Objects.requireNonNull(phoneNumber);
            return this;
        }

        public Customer createCustomer() {
            return new Customer(firstname, lastname, email, address, phoneNumber);
        }
    }
}
