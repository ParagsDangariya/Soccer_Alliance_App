
package com.example.soccerallianceapp.pojo.ViewTeamListDashboard;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TeamList {

    @SerializedName("Team Id")
    @Expose
    private Integer teamId;
    @SerializedName("Team Name")
    @Expose
    private String teamName;
    @SerializedName("Logo")
    @Expose
    private String logo;
    @SerializedName("Team Label")
    @Expose
    private String teamLabel;
    @SerializedName("User Id")
    @Expose
    private Integer userId;

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
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

    public String getTeamLabel() {
        return teamLabel;
    }

    public void setTeamLabel(String teamLabel) {
        this.teamLabel = teamLabel;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "{" +
                "teamId=" + teamId +
                ", teamName='" + teamName + '\'' +
                ", logo='" + logo + '\'' +
                ", teamLabel='" + teamLabel + '\'' +
                ", userId=" + userId +
                '}';
    }
}
