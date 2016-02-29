package com.daybox.dto;

/**
 * Created by adityaagrawal on 28/02/16.
 */
public class OrderDTO {
    private String id;
    private String qty;
    private String name;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }


    @Override
    public String toString() {
        return "OrderDTO{" +
                "id='" + id + '\'' +
                ", qty='" + qty + '\'' +
                '}';
    }
}
