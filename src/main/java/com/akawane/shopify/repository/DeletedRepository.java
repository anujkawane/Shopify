package com.akawane.shopify.repository;

import com.akawane.shopify.model.Deleted;
import com.akawane.shopify.model.Item;
import org.springframework.data.repository.CrudRepository;

public interface DeletedRepository extends CrudRepository<Deleted, Long> {

}
