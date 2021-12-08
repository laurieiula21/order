package com.switchfully.order.service;

import com.switchfully.order.domain.Address;
import com.switchfully.order.domain.Customer;
import com.switchfully.order.domain.exceptions.CustomerNotFoundException;
import com.switchfully.order.domain.exceptions.InvalidCustomerException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
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
                .setUsername("laurie")
                .setPassword("laurie")
                .createCustomer();
        customerService.saveCustomer(customer);
        Assertions.assertThat(customerService.getAllCustomers()).contains(customer);

    }

    @Test
    void givenNullCustomer_whenTryingToSaveCustomer_thenInvalidCustomerExceptionIsThrown() {
        Assertions.assertThatExceptionOfType(InvalidCustomerException.class).isThrownBy(() -> customerService.saveCustomer(null));
    }

    @Test
    void givenCustomersInTheSystem_whenGettingRetrievingThem_theyAreAllReturned() {
        Customer customer1 = new Customer.CustomerBuilder()
                .setFirstname("Laurie")
                .setLastname("Iula")
                .setEmail("laurie@mail.com")
                .setAddress(new Address(24, "rue Edith Cavell", 1180, "Uccle", "Belgium"))
                .setPhoneNumber("0462587631")
                .setUsername("laurie")
                .setPassword("laurie")
                .createCustomer();
        customerService.saveCustomer(customer1);

        Customer customer2 = new Customer.CustomerBuilder()
                .setFirstname("Customer2")
                .setLastname("Lastname2")
                .setEmail("customer2@mail.com")
                .setAddress(new Address(26, "rue Customer2", 1180, "Los Angeles", "United States"))
                .setPhoneNumber("0462587664")
                .setUsername("customer2")
                .setPassword("customer2")
                .createCustomer();
        customerService.saveCustomer(customer2);

        Customer customer3 = new Customer.CustomerBuilder()
                .setFirstname("Customer3")
                .setLastname("Lastname3")
                .setEmail("customer3@mail.com")
                .setAddress(new Address(2128, "rue Customer3", 1180, "Madrid", "Spain"))
                .setPhoneNumber("0468587631")
                .setUsername("customer3")
                .setPassword("customer3")
                .createCustomer();
        customerService.saveCustomer(customer3);

        Assertions.assertThat(customerService.getAllCustomers()).contains(customer1, customer2, customer3);
    }

    @Test
    void givenACustomerId_whenGettingCustomerWithId_thenCorrespondingCustomerIsReturned() {

        Customer customer = new Customer.CustomerBuilder()
                .setFirstname("Laurie")
                .setLastname("Iula")
                .setEmail("laurie@mail.com")
                .setAddress(new Address(24, "rue Edith Cavell", 1180, "Uccle", "Belgium"))
                .setPhoneNumber("0462587631")
                .setUsername("laurie")
                .setPassword("laurie")
                .createCustomer();
        customerService.saveCustomer(customer);

        Assertions.assertThat(customerService.getCustomerById(customer.getId())).isEqualTo(customer);
    }

    @Test
    void givenACustomerIdThetDoesNotExist_whenTryingToGetCustomerWithId_thenCustomerNotFoundExceptionIsThrown() {

        Customer customer = new Customer.CustomerBuilder()
                .setFirstname("Laurie")
                .setLastname("Iula")
                .setEmail("laurie@mail.com")
                .setAddress(new Address(24, "rue Edith Cavell", 1180, "Uccle", "Belgium"))
                .setPhoneNumber("0462587631")
                .setUsername("laurie")
                .setPassword("laurie")
                .createCustomer();
        customerService.saveCustomer(customer);

        Assertions.assertThatExceptionOfType(CustomerNotFoundException.class).isThrownBy(() -> customerService.getCustomerById("NotExistingId"));
    }
}