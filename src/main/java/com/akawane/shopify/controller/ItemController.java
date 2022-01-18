package com.akawane.shopify.controller;

import com.akawane.shopify.dto.ItemCreateDTO;
import com.akawane.shopify.mapper.ItemMapper;
import com.akawane.shopify.model.Item;
import com.akawane.shopify.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping(value = "/api/v1/items")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private ItemMapper itemMapper;

    @PostMapping()
    public ResponseEntity<String> createItem(@Valid @RequestBody ItemCreateDTO itemDTO, Errors errors){
        if(errors.hasErrors()) {
            return ResponseEntity.ok(errors.getAllErrors().toString());
        }
        Item item = itemMapper.dtoToModel(itemDTO);
        itemService.saveItem(item);
        return ResponseEntity.ok(" success");
    }

    /*
	Get all items
	 */
    @GetMapping()
    public ResponseEntity<Iterable<Item>> getAllItem(){
        return ResponseEntity.ok(itemService.getAllItems(true));
    }

    @GetMapping(params = "showInStockOnly")
    public ResponseEntity<Iterable<Item>> getInStockItems(@RequestParam(required = false, defaultValue = "false") final boolean showInStockOnly) {
        if (showInStockOnly)
            return  ResponseEntity.ok(itemService.getAllInStockItems(true));
        return ResponseEntity.ok(itemService.getAllItems(true));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        itemService.deleteItem(id);
        return ResponseEntity.ok("Deleted item with id:"+id);

    }

}
