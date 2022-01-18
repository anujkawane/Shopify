package com.akawane.shopify.services;

import com.akawane.shopify.model.Item;
import com.akawane.shopify.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    public Item getItemById(long id) {
        Optional<Item> optional = itemRepository.findById(id);
        Item item = null;
        if (optional.isPresent()) {
            item = optional.get();
        } else {
            // Exception
        }
        return item;
    }

    public String validateItemId(long id) {
        String errorMessage = "";
        Item item = getItemById(id);
        if (item == null) {
            errorMessage = "Item id does not exist";
        }
        return errorMessage;
    }


    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    public void saveItem(Item item) {
        itemRepository.save(item);
    }


    public void deleteItem(long itemId) {
        Item item = getItemById(itemId);
        itemRepository.delete(item);
    }

    public List<Item> getAllInStockItems() {
        return itemRepository.findAll()
                .stream()
                .filter(Item::inStock)
                .collect(Collectors.toList());
    }

    public List<Item> getItemByCategory(final String category) {
        return itemRepository.findAll()
                .stream()
                .filter(item -> item.getCategory().equals(category))
                .collect(Collectors.toList());
    }

}
