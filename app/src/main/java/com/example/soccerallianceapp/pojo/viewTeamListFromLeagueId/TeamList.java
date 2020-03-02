
package com.example.soccerallianceapp.pojo.viewTeamListFromLeagueId;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TeamList {

    @SerializedName("Teamid")
    @Expose
    private Integer teamid;
    @SerializedName("TeamName")
    @Expose
    private String teamName;
    @SerializedName("Logo")
    @Expose
    private Object logo;

    public Integer getTeamid() {
        return teamid;
    }

    public void setTeamid(Integer teamid) {
        this.teamid = teamid;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public Object getLogo() {
        return logo;
    }

    public void setLogo(Object logo) {
        this.logo = logo;
    }

}
