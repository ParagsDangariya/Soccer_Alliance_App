
package com.example.soccerallianceapp.pojo.listLeagueDashboard;

import androidx.annotation.NonNull;

import java.util.List;

import com.example.soccerallianceapp.pojo.listLeagueDashboard.League;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ListLeagueDashboard {

    @SerializedName("Status")
    @Expose
    private Integer status;
    @SerializedName("TimeStamp")
    @Expose
    private Integer timeStamp;
    @SerializedName("Leagues")
    @Expose
    private List<League> leagues = null;

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

    public List<League> getLeagues() {
        return leagues;
    }

    public void setLeagues(List<League> leagues) {
        this.leagues = leagues;
    }

    @Override
    public String toString() {
        return "ListLeagueDashboard{" +
                "status=" + status +
                ", timeStamp=" + timeStamp +
                ", leagues=" + leagues +
                '}';
    }
}
