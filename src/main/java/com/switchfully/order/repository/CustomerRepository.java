package com.switchfully.order.repository;

import com.switchfully.order.domain.Customer;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class CustomerRepository {

    private final Map<String, Customer> customerList = new HashMap<>();

    public CustomerRepository() {
    }

    public Customer addCustomer(Customer customer){
        customerList.put(customer.getId(), customer);
        return customerList.get(customer.getId());
    }

    public Map<String, Customer> getCustomerList() {
        return customerList;
    }
}
