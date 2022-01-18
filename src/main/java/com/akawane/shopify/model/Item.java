package com.akawane.shopify.model;

import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Inventory_item")
@SQLDelete(sql = "update Inventory_item SET active = false WHERE id =?")
@FilterDef(name = "deletedProductFilter", parameters = @ParamDef(name = "isActive", type = "boolean"))
@Filter(name = "deletedProductFilter", condition = "active = :isActive")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "item_Name")
    private String name;

    @Column(name = "item_Category")
    private Category category;

    @Column(name = "item_price")
    private double price;

    @Column(name = "item_quantity")
    private int quantity;

    @Column(name = "invoiceNumber")
    private long invoiceNumber;

    @Column(name = "createdDate")
    private String createdDate;

    private boolean active = true;

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean isActive) {
        this.active = isActive;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public Item() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public long getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(long invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public Item(long id, String name, Category category, double price, int quantity, long invoiceNumber, String createdDate) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.quantity = quantity;
        this.invoiceNumber = invoiceNumber;
        this.createdDate = createdDate;
    }

    public boolean inStock() {
        return this.quantity > 0;
    }
}
