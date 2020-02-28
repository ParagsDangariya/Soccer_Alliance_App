
package com.example.soccerallianceapp.pojo.PlayedMatchListDashboard;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PlayedmatchListDashboard {

    @SerializedName("Status")
    @Expose
    private Integer status;
    @SerializedName("TimeStamp")
    @Expose
    private Integer timeStamp;
    @SerializedName("PlayedMatchList")
    @Expose
    private List<PlayedMatchList> playedMatchList = null;

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

    public List<PlayedMatchList> getPlayedMatchList() {
        return playedMatchList;
    }

    public void setPlayedMatchList(List<PlayedMatchList> playedMatchList) {
        this.playedMatchList = playedMatchList;
    }

    @Override
    public String toString() {
        return "PlayedmatchListDashboard{" +
                "status=" + status +
                ", timeStamp=" + timeStamp +
                ", playedMatchList=" + playedMatchList +
                '}';
    }
}
