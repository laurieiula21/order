package com.switchfully.order.domain;

import com.switchfully.order.domain.exceptions.InvalidFieldValueException;
import com.switchfully.order.service.ItemService;
import com.switchfully.order.service.OrderService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OrderTest {

    @Autowired
    private ItemService itemService;

    @Autowired
    private OrderService orderService;

    @Test
    void givenANullItemGroup_whenTryingToCreateOrderWithItemGroup_thenInvalidFieldValueExceptionIsThrown() {

        Assertions.assertThatExceptionOfType(InvalidFieldValueException.class).isThrownBy(() -> {
            new Order.OrderBuilder()
                    .setCustomer(new Customer.CustomerBuilder()
                            .setFirstname("Laurie")
                            .setLastname("Iula")
                            .setEmail("laurie@mail.com")
                            .setAddress(new Address(65, "Rochaval street", 1180, "Uccle", "Belgium"))
                            .setPhoneNumber("0432587632")
                            .setUsername("laurie")
                            .setPassword("laurie")
                            .createCustomer())
                    .setItemGroupList(null);
        });
    }

    @Test
    void givenNullCustomer_whenTryingToCreateOrderWithCustomer_thenInvalidFieldValueExceptionIsThrown() {
        Item item = new Item.ItemBuilder()
                .setName("Dress")
                .setDescription("Blue dress with sequins")
                .setPrice(24.0)
                .setAmount(3)
                .createItem();

        itemService.saveItem(item);

        List<ItemGroup> itemGroupList = new ArrayList<>();
        itemGroupList.add(new ItemGroup.ItemGroupBuilder()
                .setItemId(item.getId())
                .setAmount(2)
                .createItemGroup());

        Assertions.assertThatExceptionOfType(InvalidFieldValueException.class).isThrownBy(() -> {
            new Order.OrderBuilder()
                    .setCustomer(null)
                    .setItemGroupList(itemGroupList);
        });
    }


}