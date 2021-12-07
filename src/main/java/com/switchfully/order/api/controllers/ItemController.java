package com.switchfully.order.api.controllers;

import com.switchfully.order.api.dtos.ItemDto;
import com.switchfully.order.api.mappers.ItemMapper;
import com.switchfully.order.domain.Admin;
import com.switchfully.order.domain.Item;
import com.switchfully.order.service.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/items")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private ItemMapper itemMapper;

    private final Logger logger = LoggerFactory.getLogger(ItemController.class);

    @PostMapping(consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public ItemDto createItem(@RequestHeader(required = false) String header, @RequestBody ItemDto itemDto){
        logger.info("Create item method is starting...");
        Admin.isAuthorized(header);
        Item item = new Item.ItemBuilder()
                .setName(itemDto.getName())
                .setDescription(itemDto.getDescription())
                .setPrice(itemDto.getPrice())
                .setAmount(itemDto.getAmount())
                .createItem();
        Item savedItem = itemService.saveItem(item);
        ItemDto savedItemDto = new ItemDto(savedItem.getId(), savedItem.getName(), savedItem.getDescription(), savedItem.getPrice(), savedItem.getAmount());
        logger.info("The create item method was successfully executed !");
        return savedItemDto;
    }
}
