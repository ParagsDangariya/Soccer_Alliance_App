
package com.example.soccerallianceapp.pojo.ViewTeamManagerDetail;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ViewTeamManagerDetail {

    @SerializedName("Status")
    @Expose
    private Integer status;
    @SerializedName("TimeStamp")
    @Expose
    private Integer timeStamp;
    @SerializedName("TeamManagerDetails")
    @Expose
    private TeamManagerDetails teamManagerDetails;

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

    public TeamManagerDetails getTeamManagerDetails() {
        return teamManagerDetails;
    }

    public void setTeamManagerDetails(TeamManagerDetails teamManagerDetails) {
        this.teamManagerDetails = teamManagerDetails;
    }

}
