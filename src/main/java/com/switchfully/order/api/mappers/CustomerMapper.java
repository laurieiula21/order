package com.switchfully.order.api.mappers;

import com.switchfully.order.api.dtos.CustomerDto;
import com.switchfully.order.domain.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {


    public Customer mapDtoToCustomer(CustomerDto customerDto) {
        return new Customer.CustomerBuilder()
                .setFirstname(customerDto.getFirstname())
                .setLastname(customerDto.getLastname())
                .setEmail(customerDto.getEmail())
                .setAddress(customerDto.getAddress())
                .setPhoneNumber(customerDto.getPhoneNumber())
                .setUsername(customerDto.getUsername())
                .setPassword(customerDto.getPassword())
                .createCustomer();
    }

    public CustomerDto mapCustomerToDto(Customer customer){

        return new CustomerDto.CustomerDtoBuilder()
                .setId(customer.getId())
                .setFirstname(customer.getFirstname())
                .setLastname(customer.getLastname())
                .setEmail(customer.getEmail())
                .setAddress(customer.getAddress())
                .setPhoneNumber(customer.getPhoneNumber())
                .setUsername(customer.getUsername())
                .setPassword(customer.getPassword())
                .createCustomerDto();
    }
}
