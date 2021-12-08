package com.switchfully.order.service;

import com.switchfully.order.domain.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class OrderServiceTest {

    @Autowired
    private ItemService itemService;
    @Autowired
    private OrderService orderService;

    @Test
    void givenAnOrder_whenAssigningTotalPrice_thenRightPriceIsAssignedToTotalPriceField() {
        Customer customer = new Customer.CustomerBuilder()
                .setFirstname("Laurie")
                .setLastname("Iula")
                .setEmail("laurie@mail.com")
                .setAddress(new Address(65, "Rochaval street", 1180, "Uccle", "Belgium"))
                .setPhoneNumber("0432587632")
                .createCustomer();

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

        Order order = new Order.OrderBuilder()
                .setItemGroupList(itemGroupList)
                .setCustomer(customer)
                .createOrder();
        order = orderService.assignTotalPriceToOrder(order);

        Assertions.assertThat(order.getTotalPrice()).isEqualTo(48);
    }

    @Test
    void givenAnItemGroupWithAmountHigherThanStock_whenCreatingOrder_thenShippingDateShoulbeAWeekAfterTheOrderDate() {
        Customer customer = new Customer.CustomerBuilder()
                .setFirstname("Laurie")
                .setLastname("Iula")
                .setEmail("laurie@mail.com")
                .setAddress(new Address(65, "Rochaval street", 1180, "Uccle", "Belgium"))
                .setPhoneNumber("0432587632")
                .createCustomer();

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
                .setAmount(4)
                .createItemGroup());

        Order order = new Order.OrderBuilder()
                .setItemGroupList(itemGroupList)
                .setCustomer(customer)
                .createOrder();
        order = orderService.assignShippingDateToItemGroups(order);


        Assertions.assertThat(order.getItemGroupList().get(0).getShippingDate()).isEqualTo(LocalDate.now().plusDays(7));


    }


    @Test
    void givenAnItemGroupWithAmountLowerOrEqualThanStock_whenCreatingOrder_thenShippingDateShoulbeADayAfterTheOrderDate() {
        Customer customer = new Customer.CustomerBuilder()
                .setFirstname("Laurie")
                .setLastname("Iula")
                .setEmail("laurie@mail.com")
                .setAddress(new Address(65, "Rochaval street", 1180, "Uccle", "Belgium"))
                .setPhoneNumber("0432587632")
                .createCustomer();

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

        Order order = new Order.OrderBuilder()
                .setItemGroupList(itemGroupList)
                .setCustomer(customer)
                .createOrder();
        order = orderService.assignShippingDateToItemGroups(order);


        Assertions.assertThat(order.getItemGroupList().get(0).getShippingDate()).isEqualTo(LocalDate.now().plusDays(1));
    }

}