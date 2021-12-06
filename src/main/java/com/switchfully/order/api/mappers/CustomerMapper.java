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
                .createCustomer();
    }

    public CustomerDto mapCustomerToDto(Customer customer){
        return new CustomerDto(
                customer.getId(),
                customer.getFirstname(),
                customer.getLastname(),
                customer.getEmail(),
                customer.getAddress(),
                customer.getPhoneNumber()
        );
    }
}
