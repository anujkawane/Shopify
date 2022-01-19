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
    public ResponseEntity<String> create(@Valid @RequestBody CreateItemRequestWrapper request, Errors errors){
        if(errors.hasErrors()) {
            return ResponseEntity.ok(errors.getAllErrors().toString());
        }
        itemService.createItem(request);
        return ResponseEntity.ok(" success");
    }

    @GetMapping()
    public ResponseEntity<Iterable<Item>> getAllItem(){
        return ResponseEntity.ok(itemService.getAllItems());
    }

    @GetMapping(params = "showInStockOnly")
    public ResponseEntity<Iterable<Item>> getInStockItems(@RequestParam(required = true, defaultValue = "false") final boolean showInStockOnly) {
        if (showInStockOnly)
            return  ResponseEntity.ok(itemService.getAllInStockItems());
        return ResponseEntity.ok(itemService.getAllItems());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        itemService.deleteItem(id);
        return ResponseEntity.ok("Deleted item with id:"+id);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable("id") Long id, @RequestBody Map<Object, Object> fields){
        Item item = itemService.updateCustomer(id, fields);
        if(item != null){
            return ResponseEntity.ok("Item updated with id: "+id);
        }
        return ResponseEntity.ok("No item found with id: "+id);
    }

    @GetMapping(params = "quantity")
    public ResponseEntity<List<Item>> getInStockItems(@RequestParam(required = false) final int quantity) {
        return ResponseEntity.ok(itemService.getItemByFilter(quantity));
    }
}
