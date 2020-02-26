package com.example.soccerallianceapp;

class Comman_Data_List {


    private Integer item_id;
    private String item_name;
    private String item_image;


    public Comman_Data_List(String item_name, String item_image, Integer item_id) {

        this.item_name = item_name;
        this.item_image = item_image;
        this.item_id = item_id;
    }


    public Integer getItem_id() {
        return item_id;
    }

    public String getItem_name() {
        return item_name;
    }

    public String getItem_image() {
        return item_image;
    }
}

