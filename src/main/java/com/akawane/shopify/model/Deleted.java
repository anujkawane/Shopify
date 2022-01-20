package com.akawane.shopify.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
@Table(name = "DeletedItem")
@Getter
@Setter
public class Deleted {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private long id;

    private long itemId;
    private String comments;
    private ZonedDateTime deletionDate;

    public Deleted(){
        this.deletionDate = ZonedDateTime.now();
    }
}
