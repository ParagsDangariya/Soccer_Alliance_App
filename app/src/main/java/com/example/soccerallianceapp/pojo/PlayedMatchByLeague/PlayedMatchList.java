
package com.example.soccerallianceapp.pojo.PlayedMatchByLeague;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PlayedMatchList {

    @SerializedName("team1_id")
    @Expose
    private Integer team1Id;
    @SerializedName("team1")
    @Expose
    private String team1;
    @SerializedName("team1_logo")
    @Expose
    private String team1Logo;
    @SerializedName("team2_id")
    @Expose
    private Integer team2Id;
    @SerializedName("team2")
    @Expose
    private String team2;
    @SerializedName("team2_logo")
    @Expose
    private String team2Logo;
    @SerializedName("date_of_match")
    @Expose
    private String dateOfMatch;
    @SerializedName("league_id")
    @Expose
    private Integer leagueId;
    @SerializedName("match_id")
    @Expose
    private Integer matchId;
    @SerializedName("schedule_id")
    @Expose
    private Integer scheduleId;

    public Integer getTeam1Id() {
        return team1Id;
    }

    public void setTeam1Id(Integer team1Id) {
        this.team1Id = team1Id;
    }

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

    public Integer getTeam2Id() {
        return team2Id;
    }

    public void setTeam2Id(Integer team2Id) {
        this.team2Id = team2Id;
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

    public Integer getLeagueId() {
        return leagueId;
    }

    public void setLeagueId(Integer leagueId) {
        this.leagueId = leagueId;
    }

    public Integer getMatchId() {
        return matchId;
    }

    public void setMatchId(Integer matchId) {
        this.matchId = matchId;
    }

    public Integer getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Integer scheduleId) {
        this.scheduleId = scheduleId;
    }

}
