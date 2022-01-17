package com.akawane.shopify.controller;

import com.akawane.shopify.dto.ItemDTO;
import com.akawane.shopify.mapper.ItemMapper;
import com.akawane.shopify.model.Item;
import com.akawane.shopify.repository.ItemRepository;
import com.akawane.shopify.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@Controller
@RequestMapping("/api/v1/inventory")
public class ItemController {
//
//    @Autowired
//    private ItemRepository itemRepository;

    @Autowired
    private ItemService itemService;

    @Autowired
    private ItemMapper itemMapper;

    @PostMapping("/createItem")
    public ResponseEntity<Item> createEmployee(@RequestBody ItemDTO itemDTO){
        Item item = itemMapper.dtoToModel(itemDTO);
        itemService.saveItem(item);
        return ResponseEntity.ok(item);
    }


    /*
	Get all items
	 */
    @GetMapping("/viewItem")
    public ResponseEntity<List<Item>> getAllItem(){
        return ResponseEntity.ok(itemService.getAllItems());
    }

//    /*
//    Create new item
//     */
//    @PostMapping("/item")
//    public Item createEmployee(@RequestBody Item item){
//        return itemRepository.save(item);
//    }
//
//    /*
//        Update Item
//     */
//    @PutMapping("/updateItem/{id}")
//    public ResponseEntity<Item> updateItem(@RequestBody Item itemDetails){
//        itemRepository.save(itemDetails);
//        return ResponseEntity.ok(itemDetails);
//    }
//
//    /*
//        Delete item by id
//     */
//    @DeleteMapping("/deleteItem/{id}")
//    public ResponseEntity<String> deleteItem(@PathVariable Long id){
//        Item item = itemRepository.findById(id).
//                orElseThrow(()->new ItemNotFoundException("Item not exist with id: "+id));
//        itemRepository.delete(item);
//        return ResponseEntity.ok("Deleted item with id:"+id);
//    }

}
