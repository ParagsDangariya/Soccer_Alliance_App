
package com.example.soccerallianceapp.pojo.PlayerDetail;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PlayerDetails {

    @SerializedName("player_id")
    @Expose
    private Integer playerId;
    @SerializedName("full_name")
    @Expose
    private String fullName;
    @SerializedName("player_photo")
    @Expose
    private String playerPhoto;
    @SerializedName("position")
    @Expose
    private String position;
    @SerializedName("strength")
    @Expose
    private String strength;

    public Integer getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Integer playerId) {
        this.playerId = playerId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPlayerPhoto() {
        return playerPhoto;
    }

    public void setPlayerPhoto(String playerPhoto) {
        this.playerPhoto = playerPhoto;
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

}
