package com.switchfully.order.domain;

import com.switchfully.order.domain.exceptions.MissingFieldException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {

    @Test
    void givenACustomerToAddWithNullFields_whenTryingToCreateTheCustomer_thenMIssingFieldExceptionIsThrown() {

        Assertions.assertThatExceptionOfType(MissingFieldException.class).isThrownBy(() -> new Customer.CustomerBuilder()
                .setFirstname(null)
                .setLastname("Iula")
                .setEmail("laurie@mail.com")
                .setAddress(null)
                .setPhoneNumber("0462853671")
                .createCustomer());
    }

}