package com.switchfully.order.service;

import com.switchfully.order.domain.Address;
import com.switchfully.order.domain.Customer;
import com.switchfully.order.domain.exceptions.InvalidCustomerException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CustomerServiceTest {

    @Autowired
    private CustomerService customerService;


    @Test
    void givenANewCustomerToSave_whenSavingCustomer_thenCustomerIsInTheRepository(){
        Customer customer = new Customer.CustomerBuilder()
                .setFirstname("Laurie")
                .setLastname("Iula")
                .setEmail("laurie@mail.com")
                .setAddress(new Address(24, "rue Edith Cavell", 1180, "Uccle", "Belgium"))
                .setPhoneNumber("0462587631")
                .createCustomer();
        customerService.saveCustomer(customer);
        Assertions.assertThat(customerService.getCustomerRepository().get(customer.getId())).isEqualTo(customer);

    }

    @Test
    void givenNullCustomer_whenTryingToSaveCustomer_thenInvalidCustomerExceptionIsThrown() {
        Assertions.assertThatExceptionOfType(InvalidCustomerException.class).isThrownBy(() -> customerService.saveCustomer(null));
    }
}