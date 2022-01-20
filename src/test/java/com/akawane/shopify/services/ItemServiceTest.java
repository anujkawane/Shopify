package com.akawane.shopify.services;

import com.akawane.shopify.model.Category;
import com.akawane.shopify.model.Item;
import com.akawane.shopify.repository.ItemRepository;
import com.google.common.collect.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Answers;
import org.mockito.Mock;

import java.time.ZonedDateTime;
import java.util.List;

import static org.mockito.Mockito.when;

class ItemServiceTest {


    private final List<Item> productsList = Lists.newArrayList(
            new Item(1, "HeadPhones", Category.ELECTRONICS, 100.0, 10, 12345, ZonedDateTime.now(), ZonedDateTime.now(), true),
            new Item(2, "FaceWash", Category.COSMETICS, 20, 10, 67890, ZonedDateTime.now(), ZonedDateTime.now(), true));

    @Mock(answer = Answers.RETURNS_SMART_NULLS)
    private ItemRepository itemRepository;

    private ItemService itemService;

    @BeforeEach
    public void setup() {
        itemService = new ItemService(itemRepository);
    }

    @Test
    void getItemById() {
        long idToSearch = 1;
        when(itemRepository.findAll()).thenReturn(productsList);
        final Item actualProducts = itemService.getItemById(idToSearch);
        Assertions.assertEquals(actualProducts.getId(),idToSearch);
    }

    @Test
    void validateItemId() {
    }

    @Test
    void getAllItems() {
    }

    @Test
    void createItem() {
    }

    @Test
    void deleteItem() {
    }

    @Test
    void getAllInStockItems() {
    }

    @Test
    void getItemByFilter() {
    }

    @Test
    void updateCustomer() {
    }

    @Test
    void undoDelete() {
    }
}