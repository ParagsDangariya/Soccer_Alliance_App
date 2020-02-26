
package com.example.soccerallianceapp.pojo.ViewTeamListByLeague;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ViewTeamListByLeague {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("Timestamp")
    @Expose
    private Integer timestamp;
    @SerializedName("Team List")
    @Expose
    private List<TeamList> teamList = null;

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

    public List<TeamList> getTeamList() {
        return teamList;
    }

    public void setTeamList(List<TeamList> teamList) {
        this.teamList = teamList;
    }

    @Override
    public String toString() {
        return "ViewTeamListByLeague{" +
                "status=" + status +
                ", timestamp=" + timestamp +
                ", teamList=" + teamList +
                '}';
    }
}
