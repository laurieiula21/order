package com.switchfully.order.repository;

import com.switchfully.order.domain.Item;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class ItemRepository {

    private Map<String, Item> itemList = new HashMap<>();


    public Map<String, Item> getItemList() {
        return itemList;
    }

    public Item addItem(Item item) {
        itemList.put(item.getId(), item);
        return itemList.get(item.getId());
    }
}
