package com.akawane.shopify.model;

import javax.persistence.*;

@Entity
@Table(name = "Inventory_item")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_ID")
    private long id;

    @Column(name = "item_Name")
    private String itemName;

    @Column(name = "item_Category")
    private String itemCategory;

    @Column(name = "item_price")
    private double itemPrice;

    @Column(name = "item_quantity")
    private int item_quantity;

    @Column(name = "invoiceNumber")
    private long invoiceNumber;

    public Item() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemCategory() {
        return itemCategory;
    }

    public void setItemCategory(String itemCategory) {
        this.itemCategory = itemCategory;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public int getItem_quantity() {
        return item_quantity;
    }

    public void setItem_quantity(int item_quantity) {
        this.item_quantity = item_quantity;
    }

    public long getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(long invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public Item(long id, String itemName, String item_Category, double item_price, int item_quantity, long invoiceNumber) {
        this.id = id;
        this.itemName = itemName;
        this.itemCategory = item_Category;
        this.itemPrice = item_price;
        this.item_quantity = item_quantity;
        this.invoiceNumber = invoiceNumber;
    }

    public boolean inStock() {
        return this.item_quantity > 0;
    }
}
