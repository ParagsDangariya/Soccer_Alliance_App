
package com.example.soccerallianceapp.pojo.PlayedMatchByLeague;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PlayedMatchByLeague {

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

}
