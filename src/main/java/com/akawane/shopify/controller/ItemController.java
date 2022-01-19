package com.akawane.shopify.controller;

import com.akawane.shopify.model.CreateItemRequestWrapper;
import com.akawane.shopify.model.Item;
import com.akawane.shopify.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping(value = "/api/v1/items")
public class ItemController {

    @Autowired
    private ItemService itemService;


    @PostMapping()
    public ResponseEntity<String> createItem(@Valid @RequestBody CreateItemRequestWrapper request, Errors errors){
        if(errors.hasErrors()) {
            return ResponseEntity.ok(errors.getAllErrors().toString());
        }
        itemService.createItem(request);
        return ResponseEntity.ok(" success");
    }

    @GetMapping()
    public ResponseEntity<Iterable<Item>> getAllItem(){
        return ResponseEntity.ok(itemService.getAllItems(true));
    }

    @GetMapping(params = "showInStockOnly")
    public ResponseEntity<Iterable<Item>> getInStockItems(@RequestParam(required = true, defaultValue = "false") final boolean showInStockOnly) {
        if (showInStockOnly)
            return  ResponseEntity.ok(itemService.getAllInStockItems(true));
        return ResponseEntity.ok(itemService.getAllItems(true));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        itemService.deleteItem(id);
        return ResponseEntity.ok("Deleted item with id:"+id);
    }

    @GetMapping(params = "quantity")
    public ResponseEntity<List<Item>> getInStockItems(@RequestParam(required = false) final int quantity) {
        return ResponseEntity.ok(itemService.getItemByFilter(quantity));
    }

    @GetMapping(params = "price")
    public ResponseEntity<List<Item>> getItemByPriceAndCreatedDate(@RequestParam(required = false) final double price) {
        return ResponseEntity.ok(itemService.getItemByPriceAndCreatedDate(price));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> patch(@PathVariable("id") Long id, @RequestBody Map<Object, Object> fields){
        Item item = itemService.updateCustomer(id, fields);
        if(item != null){
            return ResponseEntity.ok("Item updated with id: "+id);
        }
        return ResponseEntity.ok("No item found with id: "+id);
    }
}
