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
    public ItemDto createItem(@RequestHeader(required = false) String authorization, @RequestBody ItemDto itemDto){
        logger.info("Create item method is starting...");
        Admin.isAuthorized(authorization);
        Item item = itemMapper.mapDtoToItem(itemDto);
        Item savedItem = itemService.saveItem(item);
        ItemDto savedItemDto = itemMapper.mapItemToItemDto(savedItem);
        logger.info("The create item method was successfully executed !");
        return savedItemDto;
    }

    @PutMapping(path = "/{id}", consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public ItemDto updateItem(@RequestHeader(required = false) String authorization,@PathVariable("id") String id, @RequestBody ItemDto itemDto){
        logger.info("Update item method is starting");
        Admin.isAuthorized(authorization);
        Item updatedItem = itemService.updateItem(id, itemDto);
        ItemDto updatedItemDto = itemMapper.mapItemToItemDto(updatedItem);
        logger.info("The update item method executed successfully");
        return updatedItemDto;
    }
}
