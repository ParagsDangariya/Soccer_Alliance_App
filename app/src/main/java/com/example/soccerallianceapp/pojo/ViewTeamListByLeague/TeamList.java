
package com.example.soccerallianceapp.pojo.ViewTeamListByLeague;

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
    private String logo;

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

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    @Override
    public String toString() {
        return "{" +
                "teamid=" + teamid +
                ", teamName='" + teamName + '\'' +
                ", logo='" + logo + '\'' +
                '}';
    }
}
