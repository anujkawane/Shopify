package com.akawane.shopify.services;

import com.akawane.shopify.mapper.ItemMapper;
import com.akawane.shopify.model.CreateItemRequestWrapper;
import com.akawane.shopify.model.Item;
import com.akawane.shopify.repository.ItemRepository;
import org.hibernate.Filter;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.RequestBody;

import javax.persistence.EntityManager;
import java.lang.reflect.Field;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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

    public Iterable<Item> getAllItems() {
        Session session = entityManager.unwrap(Session.class);
        Filter filter = session.enableFilter("deletedProductFilter");
        filter.setParameter("isActive", true);
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

    public List<Item> getAllInStockItems() {
        if(itemRepository.findByQuantityGreaterThan(0).isPresent())
            return itemRepository.findByQuantityGreaterThan(0).get();
        return null;
    }

    public List<Item> getItemByFilter(int quantity){
        if(itemRepository.findByQuantityGreaterThan(quantity).isPresent())
            return itemRepository.findByQuantityGreaterThan(quantity).get();
        return null;
    }

    public Item updateCustomer(Long id, Map<Object, Object> fields) {
        Optional<Item> item = itemRepository.findById(id);
        if(item.isPresent()){

            fields.forEach((key, value) ->{
                Field field = ReflectionUtils.findField(Item.class, (String) key);
                field.setAccessible(true);
                ReflectionUtils.setField(field, item.get(), value);
            });
            item.get().setUpdatedAt(ZonedDateTime.now());
            Item updateItem =  itemRepository.save(item.get());
            return updateItem;
        }
        return null;
    }


}
