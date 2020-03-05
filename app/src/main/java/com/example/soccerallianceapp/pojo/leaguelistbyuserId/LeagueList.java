
package com.example.soccerallianceapp.pojo.leaguelistbyuserId;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LeagueList {

    @SerializedName("LeagueId")
    @Expose
    private Integer leagueId;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("logo")
    @Expose
    private String logo;
    @SerializedName("noOfTeams")
    @Expose
    private Integer noOfTeams;
    @SerializedName("UserID")
    @Expose
    private String userID;

    public Integer getLeagueId() {
        return leagueId;
    }

    public void setLeagueId(Integer leagueId) {
        this.leagueId = leagueId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public Integer getNoOfTeams() {
        return noOfTeams;
    }

    public void setNoOfTeams(Integer noOfTeams) {
        this.noOfTeams = noOfTeams;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

}
