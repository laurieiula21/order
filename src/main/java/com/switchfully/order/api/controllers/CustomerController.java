package com.switchfully.order.api.controllers;

import com.switchfully.order.api.dtos.CustomerDto;
import com.switchfully.order.api.mappers.CustomerMapper;
import com.switchfully.order.domain.Customer;
import com.switchfully.order.repository.CustomerRepository;
import com.switchfully.order.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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

        Customer customer = customerMapper.mapDtoToCustomer(customerDto);

        Customer savedCustomer = customerService.saveCustomer(customer);

        CustomerDto savedCustomerDto = customerMapper.mapCustomerToDto(savedCustomer);
        logger.info("Create new customer method was successfully excecuted !");
        return savedCustomerDto;
    }

}
