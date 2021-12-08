package com.switchfully.order.api.controllers;

import com.switchfully.order.api.dtos.CustomerDto;
import com.switchfully.order.api.mappers.CustomerMapper;
import com.switchfully.order.domain.Admin;
import com.switchfully.order.domain.Customer;
import com.switchfully.order.repository.CustomerRepository;
import com.switchfully.order.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;
    @Autowired
    private CustomerMapper customerMapper;
    private final Logger logger = LoggerFactory.getLogger(CustomerController.class);


    @PostMapping(consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerDto createNewCustomer(@RequestBody CustomerDto customerDto){
        logger.info("Create new customer method is starting...");

        customerService.validateUsername(customerDto.getUsername());

        Customer customer = customerMapper.mapDtoToCustomer(customerDto);

        Customer savedCustomer = customerService.saveCustomer(customer);

        CustomerDto savedCustomerDto = customerMapper.mapCustomerToDto(savedCustomer);
        logger.info("Create new customer method was successfully excecuted !");
        return savedCustomerDto;
    }

    @GetMapping(produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public Collection<CustomerDto> getAllCustomers(@RequestHeader(required = false) String authorization){
        logger.info("Get all customers method is starting...");
        Admin.isAuthorized(authorization);

        Collection<Customer> customerList = customerService.getAllCustomers();
        Collection<CustomerDto> customerDtosList = customerList.stream()
                .map(customer -> customerMapper.mapCustomerToDto(customer))
                .collect(Collectors.toList());
        logger.info("Get all cutomers method executed successfully");
        return customerDtosList;
    }

}
