package com.example.soccerallianceapp;

class Comman_Data_List {

    private int iteam_id;
    private String item_name;
    private String item_image;
    private String item_strenght;


   /*  public Comman_Data_List(String item_name, String item_image) {
        this.item_name = item_name;
        this.item_image = item_image;
    }*/

    public Comman_Data_List(String name, String logo, int league_id) {
        this.iteam_id =league_id;
        this.item_name = name;
        this.item_image = logo;
    }

    public Comman_Data_List(String countries) {
        this.item_name=countries;
    }

    public Comman_Data_List(String teamName, String logo) {

        this.item_name=teamName;
        this.item_image=logo;
    }

   /* public Comman_Data_List(String fullName, String playerPhoto, String strength) {

        this.item_name=fullName;
        this.item_image=playerPhoto;
        this.item_strenght=strength;

    }*/

    public int getIteam_id(){return iteam_id ;}

    public String getItem_name() {
        return item_name;
    }

    public String getItem_image() {
        return item_image;
    }
}

