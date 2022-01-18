package com.akawane.shopify.services;

import com.akawane.shopify.mapper.ItemMapper;
import com.akawane.shopify.model.CreateItemRequestWrapper;
import com.akawane.shopify.model.Item;
import com.akawane.shopify.repository.ItemRepository;
import org.hibernate.Filter;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private ItemMapper itemMapper;

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


    public Iterable<Item> getAllItems(boolean isActive) {
        Session session = entityManager.unwrap(Session.class);
        Filter filter = session.enableFilter("deletedProductFilter");
        filter.setParameter("isActive", isActive);
        return itemRepository.findAll();
    }

    public void createItem(CreateItemRequestWrapper requestWrapper) {

        Item item = itemMapper.mapRequestToItem(requestWrapper);
        item.setCreatedAt(ZonedDateTime.now());
        item.setUpdatedAt(ZonedDateTime.now());
        itemRepository.save(item);
    }


    public void deleteItem(Long itemId) {
        itemRepository.deleteById(itemId);
    }

    public List<Item> getAllInStockItems(boolean isActive) {
        Session session = entityManager.unwrap(Session.class);
        Filter filter = session.enableFilter("deletedProductFilter");
        filter.setParameter("isActive", isActive);
        return StreamSupport.stream(itemRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

//    public List<Item> getItemByCategory(final String category) {
//        return itemRepository.findAll()
//                .stream()
//                .filter(item -> item.getCategory().equals(category))
//                .collect(Collectors.toList());
//    }

}
