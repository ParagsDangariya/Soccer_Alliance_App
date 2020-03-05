
package com.example.soccerallianceapp.pojo.ViewPlayerListByTeamDashboard;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ViewPlayerListDashboard {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("Timestamp")
    @Expose
    private Integer timestamp;
    @SerializedName("Player List")
    @Expose
    private List<PlayerList> playerList = null;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Integer timestamp) {
        this.timestamp = timestamp;
    }

    public List<PlayerList> getPlayerList() {
        return playerList;
    }

    public void setPlayerList(List<PlayerList> playerList) {
        this.playerList = playerList;
    }

}
