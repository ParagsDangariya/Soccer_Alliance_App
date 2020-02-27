
package com.example.soccerallianceapp.pojo.matchListDashboard;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MatchListDashboard {

    @SerializedName("Status")
    @Expose
    private Integer status;
    @SerializedName("TimeStamp")
    @Expose
    private Integer timeStamp;
    @SerializedName("UpcomingMatchList")
    @Expose
    private List<UpcomingMatchList> upcomingMatchList = null;

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

    public List<UpcomingMatchList> getUpcomingMatchList() {
        return upcomingMatchList;
    }

    public void setUpcomingMatchList(List<UpcomingMatchList> upcomingMatchList) {
        this.upcomingMatchList = upcomingMatchList;
    }

    @Override
    public String toString() {
        return "MatchListDashboard{" +
                "status=" + status +
                ", timeStamp=" + timeStamp +
                ", upcomingMatchList=" + upcomingMatchList +
                '}';
    }
}
