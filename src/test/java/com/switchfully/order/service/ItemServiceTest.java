package com.switchfully.order.service;

import com.switchfully.order.domain.Item;
import com.switchfully.order.domain.exceptions.InvalidItemException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class ItemServiceTest {

    @Autowired
    private ItemService itemService;

    @Test
    void givenAnItem_whenSavingItem_thenItemIsInTheRepository() {
        Item item = new Item.ItemBuilder()
                .setName("Dress")
                .setDescription("Blue dress with sequins")
                .setPrice(25.99)
                .setAmount(3)
                .createItem();
        itemService.saveItem(item);
        Assertions.assertThat(itemService.getItemRepository().get(item.getId())).isEqualTo(item);
    }

    @Test
    void givenANullItem_whenTryingToSaveItem_thenInvalidItemExceptionIsThrown() {
        Assertions.assertThatExceptionOfType(InvalidItemException.class).isThrownBy(() -> itemService.saveItem(null));
    }
}
