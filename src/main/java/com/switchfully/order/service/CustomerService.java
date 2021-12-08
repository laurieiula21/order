package com.switchfully.order.service;

import com.switchfully.order.domain.Customer;
import com.switchfully.order.domain.exceptions.CustomerNotFoundException;
import com.switchfully.order.domain.exceptions.InvalidCustomerException;
import com.switchfully.order.domain.exceptions.UsernameAlreadyExistsException;
import com.switchfully.order.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

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

    public void validateUsername(String username){
        List<Customer> customerFoundForUsername = customerRepository.getCustomerList().values().stream()
                .filter(customer -> customer.getUsername().equals(username))
                .collect(Collectors.toList());
        if (!customerFoundForUsername.isEmpty()){
            throw new UsernameAlreadyExistsException("The username " + username + " is already taken...");
        }
    }

    public Customer validateCustomerAuthorisation(String header){
        String decodedUsernameAndPassword = new String(Base64.getDecoder().decode(header.substring("Basic ".length())));
        String headerUsername = decodedUsernameAndPassword.substring(0, decodedUsernameAndPassword.indexOf(":"));
        String headerPassword = decodedUsernameAndPassword.substring(decodedUsernameAndPassword.indexOf(":")+1);

        Customer customer = customerRepository.getByUsername(headerUsername);
        if (customer == null || !customer.getPassword().equals(headerPassword)){
            throw new NoSuchElementException("Username or password is wrong");
        }
        return customer;
    }

    public Collection<Customer> getAllCustomers() {
        return customerRepository.getCustomerList().values();
    }

    public Customer getCustomerById(String id) {
        Customer customer = customerRepository.getById(id);
        if (customer == null){
            throw new CustomerNotFoundException("The customer not found for id " + id);
        }
        return customer;
    }
}
