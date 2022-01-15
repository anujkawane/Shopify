package com.akawane.shopify.controller;

import com.akawane.shopify.exception.ItemNotFoundException;
import com.akawane.shopify.model.Item;
import com.akawane.shopify.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/inventory")
public class ItemController {

    @Autowired
    private ItemRepository itemRepository;

    /*
	Get all items
	 */
    @GetMapping("/item")
    public List<Item> getAllItem(){
        return itemRepository.findAll();
    }

    /*
    Create new item
     */
    @PostMapping("/item")
    public Item createEmployee(@RequestBody Item item){
        return itemRepository.save(item);
    }

    /*
        Update Item
     */
    @PutMapping("/updateItem/{id}")
    public ResponseEntity<Item> updateItem(@PathVariable Long id, @RequestBody Item itemDetails){

        Item item = itemRepository.findById(id).
                orElseThrow(()->new ItemNotFoundException("Item not exist with id: "+id));
        if(itemDetails.getItemName() != null){
            item.setItemName(itemDetails.getItemName());
        }
        if(itemDetails.getCategory() != null){
            item.setCategory(itemDetails.getCategory());
        }
        if(itemDetails.getCompany() != null){
            item.setCompany(itemDetails.getCompany());
        }
        if(itemDetails.getQuantity() != 0){
            item.setQuantity(itemDetails.getQuantity());
        }
        if(itemDetails.getPrice() != null){
            item.setPrice(itemDetails.getPrice());
        }

        Item updatedItem  = itemRepository.save(item);
        return ResponseEntity.ok(updatedItem);
    }

    /*
        Delete item by id
     */
    @DeleteMapping("/deleteItem/{id}")
    public ResponseEntity<String> deleteItem(@PathVariable Long id){

        Item item = itemRepository.findById(id).
                orElseThrow(()->new ItemNotFoundException("Item not exist with id: "+id));
        itemRepository.delete(item);

        return ResponseEntity.ok("Deleted item with id:"+id);
    }

}
