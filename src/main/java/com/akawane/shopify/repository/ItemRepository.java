package com.akawane.shopify.repository;

import com.akawane.shopify.model.Category;
import com.akawane.shopify.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemRepository extends CrudRepository<Item, Long> {
    Optional<List<Item>> findByQuantityGreaterThanEqual(int quantity);
}
