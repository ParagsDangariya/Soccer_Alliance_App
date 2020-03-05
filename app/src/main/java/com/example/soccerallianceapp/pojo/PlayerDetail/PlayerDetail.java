
package com.example.soccerallianceapp.pojo.PlayerDetail;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PlayerDetail {

    @SerializedName("Status")
    @Expose
    private Integer status;
    @SerializedName("TimeStamp")
    @Expose
    private Integer timeStamp;
    @SerializedName("PlayerDetails")
    @Expose
    private PlayerDetails playerDetails;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Integer timeStamp) {
        this.timeStamp = timeStamp;
    }

    public PlayerDetails getPlayerDetails() {
        return playerDetails;
    }

    public void setPlayerDetails(PlayerDetails playerDetails) {
        this.playerDetails = playerDetails;
    }

}
