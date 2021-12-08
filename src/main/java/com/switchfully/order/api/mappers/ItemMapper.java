package com.switchfully.order.api.mappers;

import com.switchfully.order.api.dtos.ItemDto;
import com.switchfully.order.domain.Item;
import org.springframework.stereotype.Component;

@Component
public class ItemMapper {

    public Item mapDtoToItem(ItemDto itemDto){
        return new Item.ItemBuilder()
                .setName(itemDto.getName())
                .setDescription(itemDto.getDescription())
                .setPrice(itemDto.getPrice())
                .setAmount(itemDto.getAmount())
                .createItem();
    }

    public ItemDto mapItemToItemDto(Item item){
        return new ItemDto.ItemDtoBuilder()
                .setId(item.getId())
                .setName(item.getName())
                .setDescription(item.getDescription())
                .setPrice(item.getPrice())
                .setAmount(item.getAmount())
                .createItemDto();
    }

}
