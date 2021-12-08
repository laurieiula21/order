package com.switchfully.order.service;

import com.switchfully.order.api.dtos.ItemDto;
import com.switchfully.order.domain.Item;
import com.switchfully.order.domain.exceptions.InvalidItemException;
import com.switchfully.order.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.NoSuchElementException;

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

    public Item getItemById(String id){
        return itemRepository.getById(id);
    }

    public Item updateItem(String id, ItemDto itemToUpdate) {
        Item item = itemRepository.getById(id);
        if (id == null || item == null){
            throw new NoSuchElementException("Item not found for id: " + id);
        }

        if (itemToUpdate != null){
            if(itemToUpdate.getName() != null){
                item.setName(itemToUpdate.getName());
            }
            if (itemToUpdate.getDescription() != null){
                item.setDescription(itemToUpdate.getDescription());
            }
            if (itemToUpdate.getPrice() > 0){
                item.setPrice(itemToUpdate.getPrice());
            }
            if (itemToUpdate.getAmount() >= 0){
                item.setAmount(itemToUpdate.getAmount());
            }
        }
        return item;
    }
}
