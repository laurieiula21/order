package com.switchfully.order.service;

import com.switchfully.order.domain.Customer;
import com.switchfully.order.domain.exceptions.InvalidCustomerException;
import com.switchfully.order.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer saveCustomer(Customer customer){
        if(customer == null){
            throw new InvalidCustomerException("The customer you are trying to save is not valid");
        }
        return customerRepository.addCustomer(customer);
    }

    public Map<String, Customer> getCustomerRepository() {
        return customerRepository.getCustomerList();
    }
}
