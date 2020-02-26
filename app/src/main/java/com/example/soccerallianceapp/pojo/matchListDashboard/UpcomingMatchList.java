
package com.example.soccerallianceapp.pojo.matchListDashboard;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UpcomingMatchList {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("date_of_match")
    @Expose
    private String dateOfMatch;
    @SerializedName("logo")
    @Expose
    private String logo;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateOfMatch() {
        return dateOfMatch;
    }

    public void setDateOfMatch(String dateOfMatch) {
        this.dateOfMatch = dateOfMatch;
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
                "name='" + name + '\'' +
                ", dateOfMatch='" + dateOfMatch + '\'' +
                ", logo='" + logo + '\'' +
                '}';
    }
}
