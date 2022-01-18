package com.akawane.shopify.dto;

import com.akawane.shopify.model.Category;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ItemCreateDTO {
    private long id;


    @NotNull(message = "Item name is mandatory.")
    @JsonProperty("name")
    private String name;

    @NotNull(message = "Item category is mandatory")
    @JsonProperty("category")
    private Category category;

    @NotNull(message = "Item price is mandatory.")
    @Min(value = 1)
    @Max(value = (long) Double.MAX_VALUE)
    @JsonProperty("price")
    private double price;

    @NotNull(message = "Item quantity is mandatory")
    @Min(1)
    @Max(value = Integer.MAX_VALUE)
    @JsonProperty("quantity")
    private int quantity;

    @NotNull(message = "Invoice number is mandatory.")
    @Min(value = 1)
    @Max(value = Long.MAX_VALUE)
    @JsonProperty("invoiceNumber")
    private long invoiceNumber;

    public ItemCreateDTO(){

    }

    public ItemCreateDTO(long id, int quantity, long invoiceNumber, double price, String name, Category category) {
        this.id = id;
        this.quantity = quantity;
        this.invoiceNumber = invoiceNumber;
        this.price = price;
        this.name = name;
        this.category = category;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
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
}
