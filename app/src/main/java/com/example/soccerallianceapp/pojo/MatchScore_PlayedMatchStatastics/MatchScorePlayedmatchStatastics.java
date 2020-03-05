
package com.example.soccerallianceapp.pojo.MatchScore_PlayedMatchStatastics;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MatchScorePlayedmatchStatastics {

    @SerializedName("Status")
    @Expose
    private Integer status;
    @SerializedName("TimeStamp")
    @Expose
    private Integer timeStamp;
    @SerializedName("MatchScores")
    @Expose
    private MatchScores matchScores;

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

    public MatchScores getMatchScores() {
        return matchScores;
    }

    public void setMatchScores(MatchScores matchScores) {
        this.matchScores = matchScores;
    }

    @Override
    public String toString() {
        return "MatchScorePlayedmatchStatastics{" +
                "status=" + status +
                ", timeStamp=" + timeStamp +
                ", matchScores=" + matchScores +
                '}';
    }
}
