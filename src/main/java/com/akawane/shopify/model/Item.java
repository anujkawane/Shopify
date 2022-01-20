package com.akawane.shopify.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.ZonedDateTime;

@Entity
@Table(name = "Item")
@SQLDelete(sql = "update item SET active = false WHERE id =?")
@FilterDef(name = "deletedProductFilter", parameters = @ParamDef(name = "isActive", type = "boolean"))
@Filter(name = "deletedProductFilter", condition = "active = :isActive")
@Getter
@Setter
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private long id;
    private String name;
    private Category category;
    private double price;
    private int quantity;
    private long invoiceNumber;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;
    private boolean active;

    public Item(){
        this.active = true;
    }

    public Item(long id, String name, Category category, double price, int quantity, long invoiceNumber, ZonedDateTime createdAt, ZonedDateTime updatedAt, boolean active) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.quantity = quantity;
        this.invoiceNumber = invoiceNumber;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.active = active;
    }
}
