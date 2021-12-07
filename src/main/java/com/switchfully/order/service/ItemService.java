package com.switchfully.order.service;

import com.switchfully.order.domain.Item;
import com.switchfully.order.domain.exceptions.InvalidItemException;
import com.switchfully.order.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ItemService {


    private ItemRepository itemRepository;

    @Autowired
    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public Item saveItem(Item item) {
        if(item == null){
            throw new InvalidItemException("The item you are trying to save is not valid");
        }
        return itemRepository.addItem(item);
    }

    public Map<String, Item> getItemRepository() {
        return itemRepository.getItemList();
    }
}
