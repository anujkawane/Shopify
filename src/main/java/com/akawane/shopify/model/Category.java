package com.akawane.shopify.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Category {

    @JsonProperty("electronics")
    ELECTRONICS,

    @JsonProperty("cosmetics")
    COSMETICS
}
