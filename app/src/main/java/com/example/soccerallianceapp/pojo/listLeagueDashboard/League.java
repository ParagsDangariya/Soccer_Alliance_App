
package com.example.soccerallianceapp.pojo.listLeagueDashboard;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class League {

    @SerializedName("league_id")
    @Expose
    private Integer leagueId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("logo")
    @Expose
    private String logo;

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

    @Override
    public String toString() {
        return "{" +
                "leagueId=" + leagueId +
                ", name='" + name + '\'' +
                ", logo='" + logo + '\'' +
                '}';
    }
}
