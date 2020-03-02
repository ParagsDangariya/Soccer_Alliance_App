
package com.example.soccerallianceapp.pojo.matchScore;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MatchScores {

    @SerializedName("ScoreId")
    @Expose
    private Integer scoreId;
    @SerializedName("MatchId")
    @Expose
    private Integer matchId;
    @SerializedName("TeamId")
    @Expose
    private Integer teamId;
    @SerializedName("goal")
    @Expose
    private Integer goal;
    @SerializedName("shots")
    @Expose
    private Integer shots;
    @SerializedName("shotsOnTarget")
    @Expose
    private Integer shotsOnTarget;
    @SerializedName("possession")
    @Expose
    private Integer possession;
    @SerializedName("passes")
    @Expose
    private Integer passes;
    @SerializedName("passAccuracy")
    @Expose
    private Integer passAccuracy;
    @SerializedName("fouls")
    @Expose
    private Integer fouls;
    @SerializedName("yellowCards")
    @Expose
    private Integer yellowCards;
    @SerializedName("redCards")
    @Expose
    private Integer redCards;
    @SerializedName("offsides")
    @Expose
    private Integer offsides;
    @SerializedName("corners")
    @Expose
    private Integer corners;

    public Integer getScoreId() {
        return scoreId;
    }

    public void setScoreId(Integer scoreId) {
        this.scoreId = scoreId;
    }

    public Integer getMatchId() {
        return matchId;
    }

    public void setMatchId(Integer matchId) {
        this.matchId = matchId;
    }

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    public Integer getGoal() {
        return goal;
    }

    public void setGoal(Integer goal) {
        this.goal = goal;
    }

    public Integer getShots() {
        return shots;
    }

    public void setShots(Integer shots) {
        this.shots = shots;
    }

    public Integer getShotsOnTarget() {
        return shotsOnTarget;
    }

    public void setShotsOnTarget(Integer shotsOnTarget) {
        this.shotsOnTarget = shotsOnTarget;
    }

    public Integer getPossession() {
        return possession;
    }

    public void setPossession(Integer possession) {
        this.possession = possession;
    }

    public Integer getPasses() {
        return passes;
    }

    public void setPasses(Integer passes) {
        this.passes = passes;
    }

    public Integer getPassAccuracy() {
        return passAccuracy;
    }

    public void setPassAccuracy(Integer passAccuracy) {
        this.passAccuracy = passAccuracy;
    }

    public Integer getFouls() {
        return fouls;
    }

    public void setFouls(Integer fouls) {
        this.fouls = fouls;
    }

    public Integer getYellowCards() {
        return yellowCards;
    }

    public void setYellowCards(Integer yellowCards) {
        this.yellowCards = yellowCards;
    }

    public Integer getRedCards() {
        return redCards;
    }

    public void setRedCards(Integer redCards) {
        this.redCards = redCards;
    }

    public Integer getOffsides() {
        return offsides;
    }

    public void setOffsides(Integer offsides) {
        this.offsides = offsides;
    }

    public Integer getCorners() {
        return corners;
    }

    public void setCorners(Integer corners) {
        this.corners = corners;
    }

}
