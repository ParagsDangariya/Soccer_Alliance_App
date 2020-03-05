package com.example.soccerallianceapp.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Player {

    private int player_id;
    private String full_name;
    private String player_photo;
    private String position;
    private String strength;
    private String user_id;
    @SerializedName("message")
    @Expose
    private String message;

    public Player(int player_id, String full_name, String player_photo, String position, String strength) {
        this.player_id = player_id;
        this.full_name = full_name;
        this.player_photo = player_photo;
        this.position = position;
        this.strength = strength;
    }

    public Player(String full_name, String player_photo, String position, String strength, String user_id) {
        this.full_name = full_name;
        this.player_photo = player_photo;
        this.position = position;
        this.strength = strength;
        this.user_id = user_id;
    }

    public String getUser_id() {
        return user_id;
    }
    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
    public int getPlayer_id() {
        return player_id;
    }
    public void setPlayer_id(int player_id) {
        this.player_id = player_id;
    }


    public String getFull_name() {
        return full_name;
    }
    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }
    public String getPlayer_photo() {
        return player_photo;
    }
    public void setPlayer_photo(String player_photo) {
        this.player_photo = player_photo;
    }
    public String getPosition() {
        return position;
    }
    public void setPosition(String position) {
        this.position = position;
    }
    public String getStrength() {
        return strength;
    }
    public void setStrength(String strength) {
        this.strength = strength;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

}