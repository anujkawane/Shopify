package com.akawane.shopify.controller;

import com.akawane.shopify.model.CreateItemRequestWrapper;
import com.akawane.shopify.model.Deleted;
import com.akawane.shopify.model.Item;
import com.akawane.shopify.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;


@RestController
@RequestMapping(value = "/api/v1/items")
public class ItemController {


    private ItemService itemService;

    @Autowired
    public ItemController(final ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping()
    public ResponseEntity<String> create(@Valid @RequestBody CreateItemRequestWrapper request){
//    public ResponseEntity<String> create(@Valid @RequestBody CreateItemRequestWrapper request, Errors errors){
//        if(errors.hasErrors()) {
//            return ResponseEntity.ok(errors.getAllErrors().toString());
//        }
        itemService.createItem(request);
        return ResponseEntity.ok("success");
    }

    @GetMapping()
    public ResponseEntity<Iterable<Item>> getAllItem(){
        return ResponseEntity.ok(itemService.getAllItems());
    }


    @PatchMapping("/{id}")
    public ResponseEntity<Item> update(@PathVariable("id") long id, @RequestBody Map<Object, Object> fields){
        Item item = itemService.updateItem(id, fields);
        return ResponseEntity.ok(item);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") long id, @RequestBody Deleted request) {
        itemService.deleteItem(id, request);
        return ResponseEntity.ok("Deleted item with id:"+id);
    }

    @GetMapping(params = "showInStockOnly")
    public ResponseEntity<Iterable<Item>> getInStockItems(@RequestParam(required = true, defaultValue = "false") final boolean showInStockOnly) {
        if (showInStockOnly)
            return  ResponseEntity.ok(itemService.getAllInStockItems());
        return ResponseEntity.ok(itemService.getAllItems());
    }

    @PatchMapping("/undoDelete/{id}")
    public ResponseEntity<Item> undoDelete(@PathVariable("id") long id){
        return ResponseEntity.ok(itemService.undoDelete(id));
    }
}
