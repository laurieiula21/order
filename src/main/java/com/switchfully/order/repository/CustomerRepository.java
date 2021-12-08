package com.switchfully.order.repository;

import com.switchfully.order.domain.Customer;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Repository
public class CustomerRepository {

    private final Map<String, Customer> customerList = new HashMap<>();

    public Customer addCustomer(Customer customer){
        customerList.put(customer.getId(), customer);
        return customerList.get(customer.getId());
    }

    public Map<String, Customer> getCustomerList() {
        return customerList;
    }

    public Customer getByUsername(String username) {
        return customerList.values().stream()
                .filter(customer -> customer.getUsername().equals(username))
                .findFirst().orElse(null);
    }


    public Customer getById(String id) {
        return customerList.get(id);
    }
}
