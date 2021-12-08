package com.switchfully.order.service;

import com.switchfully.order.api.dtos.ItemDto;
import com.switchfully.order.domain.Item;
import com.switchfully.order.domain.exceptions.InvalidItemException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.NoSuchElementException;


@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
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

    @Test
    void givenAnItemToUpdate_whenUpdatingItem_thenItemFieldsAreUpdatedToNewValues() {
        Item itemToChange = new Item.ItemBuilder()
                .setName("Dress")
                .setDescription("Blue dress with sequins")
                .setPrice(25.99)
                .setAmount(3)
                .createItem();
        itemService.saveItem(itemToChange);

        ItemDto itemToUpdate = new ItemDto.ItemDtoBuilder()
                .setName("Skirt")
                .setDescription("Pink skirt with sequins")
                .setPrice(26.00)
                .setAmount(4)
                .createItemDto();

        Item updatedItem = itemService.updateItem(itemToChange.getId(), itemToUpdate);

        Assertions.assertThat(updatedItem.getName()).isEqualTo(itemToUpdate.getName());
        Assertions.assertThat(updatedItem.getDescription()).isEqualTo(itemToUpdate.getDescription());
        Assertions.assertThat(updatedItem.getPrice()).isEqualTo(itemToUpdate.getPrice());
        Assertions.assertThat(updatedItem.getAmount()).isEqualTo(itemToUpdate.getAmount());
    }

    @Test
    void givenAnItemToUpdateWithNullValues_whenUpdatingItem_thenItemFieldsAreNotUpdated() {
        Item itemToChange = new Item.ItemBuilder()
                .setName("Dress")
                .setDescription("Blue dress with sequins")
                .setPrice(25.99)
                .setAmount(3)
                .createItem();
        itemService.saveItem(itemToChange);

        ItemDto itemToUpdate = new ItemDto.ItemDtoBuilder()
                .setName(null)
                .setDescription(null)
                .setPrice(0)
                .setAmount(-5)
                .createItemDto();

        Item updatedItem = itemService.updateItem(itemToChange.getId(), itemToUpdate);

        Assertions.assertThat(updatedItem.getName()).isEqualTo(updatedItem.getName());
        Assertions.assertThat(updatedItem.getDescription()).isEqualTo(updatedItem.getDescription());
        Assertions.assertThat(updatedItem.getPrice()).isEqualTo(updatedItem.getPrice());
        Assertions.assertThat(updatedItem.getAmount()).isEqualTo(updatedItem.getAmount());
    }

    @Test
    void givenANullId_whenTryinfToUpdateItem_thenNoSuchElementExceptionIsThrown() {
        Item itemToChange = new Item.ItemBuilder()
                .setName("Dress")
                .setDescription("Blue dress with sequins")
                .setPrice(25.99)
                .setAmount(3)
                .createItem();
        itemService.saveItem(itemToChange);

        ItemDto itemToUpdate = new ItemDto.ItemDtoBuilder()
                .setName(null)
                .setDescription(null)
                .setPrice(0)
                .setAmount(-5)
                .createItemDto();

        Assertions.assertThatExceptionOfType(NoSuchElementException.class).isThrownBy(() -> itemService.updateItem(null, itemToUpdate));
    }

    @Test
    void givenAItemDtoToUpdateItem_whenTryinfToUpdateItem_thenNoSuchElementExceptionIsThrown() {
        Item itemToChange = new Item.ItemBuilder()
                .setName("Dress")
                .setDescription("Blue dress with sequins")
                .setPrice(25.99)
                .setAmount(3)
                .createItem();
        itemService.saveItem(itemToChange);

        ItemDto itemToUpdate = new ItemDto.ItemDtoBuilder()
                .setName(null)
                .setDescription(null)
                .setPrice(0)
                .setAmount(-5)
                .createItemDto();

        Assertions.assertThatExceptionOfType(NoSuchElementException.class).isThrownBy(() -> itemService.updateItem("NotExistingItem", itemToUpdate));
    }

}
