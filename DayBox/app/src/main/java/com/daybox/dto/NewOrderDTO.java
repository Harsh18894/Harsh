package com.daybox.dto;

/**
 * Created by Dell on 2/20/2016.
 */
public class NewOrderDTO {
    private int id;
    String name;

    public NewOrderDTO(){

    }

    public NewOrderDTO(int id, String name){
        super();
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
