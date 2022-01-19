package com.akawane.shopify.mapper;
import com.akawane.shopify.model.CreateItemRequestWrapper;
import com.akawane.shopify.model.Item;
import org.springframework.stereotype.Component;


@Component
public class ItemMapper{

    public Item mapRequestToItem (CreateItemRequestWrapper request) {
        Item item = new Item();
        item.setInvoiceNumber(request.getInvoiceNumber());
        item.setCategory(request.getCategory());
        item.setQuantity(request.getQuantity());
        item.setPrice(request.getPrice());
        item.setName(request.getName());
        return item;
    }
}
