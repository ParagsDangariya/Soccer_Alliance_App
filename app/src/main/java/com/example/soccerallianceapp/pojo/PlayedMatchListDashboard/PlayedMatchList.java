
package com.example.soccerallianceapp.pojo.PlayedMatchListDashboard;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PlayedMatchList {

    @SerializedName("team1")
    @Expose
    private String team1;
    @SerializedName("team1_logo")
    @Expose
    private String team1Logo;
    @SerializedName("team2")
    @Expose
    private String team2;
    @SerializedName("team2_logo")
    @Expose
    private String team2Logo;
    @SerializedName("date_of_match")
    @Expose
    private String dateOfMatch;

    public String getTeam1() {
        return team1;
    }

    public void setTeam1(String team1) {
        this.team1 = team1;
    }

    public String getTeam1Logo() {
        return team1Logo;
    }

    public void setTeam1Logo(String team1Logo) {
        this.team1Logo = team1Logo;
    }

    public String getTeam2() {
        return team2;
    }

    public void setTeam2(String team2) {
        this.team2 = team2;
    }

    public String getTeam2Logo() {
        return team2Logo;
    }

    public void setTeam2Logo(String team2Logo) {
        this.team2Logo = team2Logo;
    }

    public String getDateOfMatch() {
        return dateOfMatch;
    }

    public void setDateOfMatch(String dateOfMatch) {
        this.dateOfMatch = dateOfMatch;
    }

    @Override
    public String toString() {
        return "{" +
                "team1='" + team1 + '\'' +
                ", team1Logo='" + team1Logo + '\'' +
                ", team2='" + team2 + '\'' +
                ", team2Logo='" + team2Logo + '\'' +
                ", dateOfMatch='" + dateOfMatch + '\'' +
                '}';
    }
}
