package com.akawane.shopify.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class CreateItemRequestWrapper {
    @Setter(AccessLevel.NONE)
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
    @Min(0)
    @Max(value = Integer.MAX_VALUE)
    @JsonProperty("quantity")
    private int quantity;

    @NotNull(message = "Invoice number is mandatory.")
    @Min(value = 1)
    @Max(value = Long.MAX_VALUE)
    @JsonProperty("invoiceNumber")
    private long invoiceNumber;
}
