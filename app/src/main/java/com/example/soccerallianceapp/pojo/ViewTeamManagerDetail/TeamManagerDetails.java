
package com.example.soccerallianceapp.pojo.ViewTeamManagerDetail;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TeamManagerDetails {

    @SerializedName("team_id")
    @Expose
    private Integer teamId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("logo")
    @Expose
    private String logo;
    @SerializedName("team_label")
    @Expose
    private String teamLabel;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("full_name")
    @Expose
    private String fullName;
    @SerializedName("user_type")
    @Expose
    private String userType;
    @SerializedName("user_photo")
    @Expose
    private String userPhoto;
    @SerializedName("country")
    @Expose
    private String country;

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
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

    public String getTeamLabel() {
        return teamLabel;
    }

    public void setTeamLabel(String teamLabel) {
        this.teamLabel = teamLabel;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getUserPhoto() {
        return userPhoto;
    }

    public void setUserPhoto(String userPhoto) {
        this.userPhoto = userPhoto;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

}
