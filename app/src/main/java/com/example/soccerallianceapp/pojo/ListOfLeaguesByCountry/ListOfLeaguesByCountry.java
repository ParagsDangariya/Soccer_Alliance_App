
package com.example.soccerallianceapp.pojo.ListOfLeaguesByCountry;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ListOfLeaguesByCountry {

    @SerializedName("Status")
    @Expose
    private Integer status;
    @SerializedName("Timestamp")
    @Expose
    private Integer timestamp;
    @SerializedName("Leagues: ")
    @Expose
    private List<Leagues> leagues = null;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Integer timestamp) {
        this.timestamp = timestamp;
    }

    public List<Leagues> getLeagues() {
        return leagues;
    }

    public void setLeagues(List<Leagues> leagues) {
        this.leagues = leagues;
    }

    @Override
    public String toString() {
        return "ListOfLeaguesByCountry{" +
                "status=" + status +
                ", timestamp=" + timestamp +
                ", leagues=" + leagues +
                '}';
    }
}
