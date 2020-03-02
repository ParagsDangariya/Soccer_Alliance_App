
package com.example.soccerallianceapp.pojo.leaguelistbyuserId;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LeaguesFragment {

    @SerializedName("Status")
    @Expose
    private Integer status;
    @SerializedName("TimeStamp")
    @Expose
    private Integer timeStamp;
    @SerializedName("LeagueList")
    @Expose
    private List<LeagueList> leagueList = null;

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

    public List<LeagueList> getLeagueList() {
        return leagueList;
    }

    public void setLeagueList(List<LeagueList> leagueList) {
        this.leagueList = leagueList;
    }

}
