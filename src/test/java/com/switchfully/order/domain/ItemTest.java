package com.switchfully.order.domain;

import com.switchfully.order.domain.exceptions.MissingFieldException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ItemTest {

    @Test
    void givenItemToCreateWithNullFields_whenTryingToCreateItem_thenMissingFieldExceptionIsThrown() {

        Assertions.assertThatExceptionOfType(MissingFieldException.class).isThrownBy(() -> new Item.ItemBuilder()
                .setName(null)
                .setDescription("A null item")
                .setPrice(0.55)
                .setAmount(3)
                .createItem());
    }
}
