package com.akawane.shopify.repository;

import com.akawane.shopify.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {

}
