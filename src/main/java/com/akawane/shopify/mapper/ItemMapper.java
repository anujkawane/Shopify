package com.akawane.shopify.mapper;
import com.akawane.shopify.Utilities.Helper;
import com.akawane.shopify.dto.ItemCreateDTO;
import com.akawane.shopify.model.Item;
import org.springframework.stereotype.Component;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;


@Component
public class ItemMapper{

    public ItemCreateDTO modelToDto(Item item) {
        ItemCreateDTO itemDto = new ItemCreateDTO();
        itemDto.setId(item.getId());
        itemDto.setPrice(item.getPrice());
        itemDto.setInvoiceNumber(item.getInvoiceNumber());
        itemDto.setName(item.getName());
        itemDto.setQuantity(item.getQuantity());
        itemDto.setCategory(item.getCategory());
        return itemDto;
    }

    public List<ItemCreateDTO> modelToDto(List<Item> itemList) {
        List<ItemCreateDTO> itemDtoList = new ArrayList<>();
        for (Item item : itemList) {
            itemDtoList.add(modelToDto(item));
        }
        return itemDtoList;
    }

    public Item dtoToModel(ItemCreateDTO itemDto) {
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
