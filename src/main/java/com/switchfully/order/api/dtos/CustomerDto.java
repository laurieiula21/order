package com.switchfully.order.api.dtos;

import com.switchfully.order.domain.Address;

public class CustomerDto {

    private String id;
    private String firstname;
    private String lastname;
    private String email;
    private Address address;
    private String phoneNumber;
    private String username;
    private String password;

    private CustomerDto(String id, String firstname, String lastname, String email, Address address, String phoneNumber, String username, String password) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.username = username;
        this.password = password;
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

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public static class CustomerDtoBuilder{

        private String id;
        private String firstname;
        private String lastname;
        private String email;
        private Address address;
        private String phoneNumber;
        private String username;
        private String password;

        public CustomerDtoBuilder setId(String id) {
            this.id = id;
            return this;
        }

        public CustomerDtoBuilder setFirstname(String firstname) {
            this.firstname = firstname;
            return this;
        }

        public CustomerDtoBuilder setLastname(String lastname) {
            this.lastname = lastname;
            return this;
        }

        public CustomerDtoBuilder setEmail(String email) {
            this.email = email;
            return this;
        }

        public CustomerDtoBuilder setAddress(Address address) {
            this.address = address;
            return this;
        }

        public CustomerDtoBuilder setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public CustomerDtoBuilder setUsername(String username) {
            this.username = username;
            return this;
        }

        public CustomerDtoBuilder setPassword(String password) {
            this.password = password;
            return this;
        }

        public CustomerDto createCustomerDto() {
            return new CustomerDto(id, firstname, lastname, email, address, phoneNumber, username, password);
        }
    }
}
