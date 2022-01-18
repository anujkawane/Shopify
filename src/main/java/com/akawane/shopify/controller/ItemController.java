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
import java.util.List;


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
    public ResponseEntity<List<Item>> getAllItem(){
        return ResponseEntity.ok(itemService.getAllItems());
    }

    @GetMapping(params = "showInStockOnly")
    public ResponseEntity<List<Item>> getInStockItems(@RequestParam(required = false, defaultValue = "false") final boolean showInStockOnly) {
        if (showInStockOnly)
            return  ResponseEntity.ok(itemService.getAllInStockItems());
        return ResponseEntity.ok(itemService.getAllItems());
    }

    @DeleteMapping(params = "itemToDelete")
    public ResponseEntity<String> Delete(@RequestParam(required = true) final long itemToDelete) {
        itemService.deleteItem(itemToDelete);
        return ResponseEntity.ok("Deleted item with id:"+itemToDelete);
    }

}
