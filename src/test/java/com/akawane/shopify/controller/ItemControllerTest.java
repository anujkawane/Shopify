package com.akawane.shopify.controller;

import com.akawane.shopify.model.Category;
import com.akawane.shopify.model.CreateItemRequestWrapper;
import com.akawane.shopify.model.Item;
import com.akawane.shopify.services.ItemService;
import com.google.common.collect.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
class ItemControllerTest {
    private ItemController itemController;

    private final List<Item> expected = Lists.newArrayList(
            new Item(1, "HeadPhones", Category.ELECTRONICS, 100.0, 10, 12345, ZonedDateTime.now(), ZonedDateTime.now(), true),
            new Item(2, "FaceWash", Category.COSMETICS, 20, 10, 67890, ZonedDateTime.now(), ZonedDateTime.now(), true));

    @Autowired
    private ItemService mockItemService;

    @BeforeEach
    void setUp() {
        mockItemService =  mock(ItemService.class);
        itemController = new ItemController(mockItemService);
    }

    @Test
    void create() throws Exception {

        CreateItemRequestWrapper newItem = new CreateItemRequestWrapper(1, "HeadPhones", Category.ELECTRONICS, 100.0, 10, 12345);
        when(mockItemService.createItem(newItem)).thenReturn(expected.get(0));
        String result = itemController.create(newItem).getBody();
        Assertions.assertEquals( "success", result);
    }

    @Test
    void getAllItem() {
        when(mockItemService.getAllItems()).thenReturn(expected);
        Iterable<Item> actual  = itemController.getAllItem().getBody();
        List<Item> items = new ArrayList<Item>();
        actual.forEach(items::add);
        Assertions.assertEquals(items, expected);
    }

    @Test
    void undoDelete() {
        Item undeletedItem = new Item(1, "HeadPhones", Category.ELECTRONICS, 100.0, 10, 12345, ZonedDateTime.now(), ZonedDateTime.now(), true);
        when(mockItemService.undoDelete(Long.valueOf(1))).thenReturn(undeletedItem);
        Item actual  = itemController.undoDelete(Long.valueOf(1)).getBody();
        Assertions.assertEquals(actual.isActive(), true);
    }

    @Test
    void getInStockItems() throws Exception {
        when(mockItemService.getAllInStockItems()).thenReturn(expected);
        Iterable<Item> actual  = itemController.getInStockItems(true).getBody();
        List<Item> actualItems = new ArrayList<Item>();
        actual.forEach(actualItems::add);
        for(Item item : actualItems){
            assertTrue( item.getQuantity() > 0 );
        }
    }

    @Test
    void update() {
        Item itemToUpdate = expected.get(0);
//        Item
        Map<Object, Object> fields = new LinkedHashMap<>();
        fields.put("name", "Laptop" );
        fields.put("quantity", 10 );

        when(mockItemService.updateItem(itemToUpdate.getId(),fields)).thenReturn(itemToUpdate);
        String result = itemController.update(1, fields).getBody();
        Assertions.assertEquals("Item updated with id: "+1, result);
    }


}