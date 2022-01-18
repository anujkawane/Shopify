package com.akawane.shopify.mapper;
import com.akawane.shopify.model.CreateItemRequestWrapper;
import com.akawane.shopify.model.Item;
import org.springframework.stereotype.Component;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;


@Component
public class ItemMapper{

    public Item dtoToModel(CreateItemRequestWrapper itemDto) {
        Item item = new Item();
        item.setInvoiceNumber(itemDto.getInvoiceNumber());
        item.setCategory(itemDto.getCategory());
        item.setQuantity(itemDto.getQuantity());
        item.setPrice(itemDto.getPrice());
        item.setName(itemDto.getName());
        item.setCreatedAt(ZonedDateTime.now(ZoneId.systemDefault()));
        return item;
    }
}
