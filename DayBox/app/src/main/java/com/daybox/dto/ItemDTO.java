package com.daybox.dto;

/**
 * Created by Dell on 2/22/2016.
 */
public class ItemDTO {
    String id;
    String name;
    String qty;

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
