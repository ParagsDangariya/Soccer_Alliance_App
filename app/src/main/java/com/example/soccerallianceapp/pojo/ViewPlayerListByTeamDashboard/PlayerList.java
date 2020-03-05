
package com.example.soccerallianceapp.pojo.ViewPlayerListByTeamDashboard;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PlayerList {

    @SerializedName("Playerid")
    @Expose
    private Integer playerid;
    @SerializedName("FullName")
    @Expose
    private String fullName;
    @SerializedName("PlayerPhoto")
    @Expose
    private String playerPhoto;
    @SerializedName("Strength")
    @Expose
    private String strength;
    @SerializedName("position")
    @Expose
    private String position;

    public Integer getPlayerid() {
        return playerid;
    }

    public void setPlayerid(Integer playerid) {
        this.playerid = playerid;
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

    public String getStrength() {
        return strength;
    }

    public void setStrength(String strength) {
        this.strength = strength;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

}
