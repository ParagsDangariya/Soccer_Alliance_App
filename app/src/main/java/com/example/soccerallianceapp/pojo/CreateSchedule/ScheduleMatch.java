package com.example.soccerallianceapp.pojo.CreateSchedule;

public class ScheduleMatch {


    private String location;
    private String date_of_match;
    private String time_of_match;
    private int team1_id;
    private int team2_id;
    private int league_id;

    public ScheduleMatch(String location, String date_of_match, String time_of_match, int team1_id, int team2_id, int league_id) {

        this.location = location;
        this.date_of_match = date_of_match;
        this.time_of_match = time_of_match;
        this.team1_id = team1_id;
        this.team2_id = team2_id;
        this.league_id = league_id;
    }


    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public String getDate_of_match() {
        return date_of_match;
    }
    public void setDate_of_match(String date_of_match) {
        this.date_of_match = date_of_match;
    }
    public String getTime_of_match() {
        return time_of_match;
    }
    public void setTime_of_match(String time_of_match) {
        this.time_of_match = time_of_match;
    }
    public int getTeam1_id() {
        return team1_id;
    }
    public void setTeam1_id(int team1_id) {
        this.team1_id = team1_id;
    }
    public int getTeam2_id() {
        return team2_id;
    }
    public void setTeam2_id(int team2_id) {
        this.team2_id = team2_id;
    }
    public int getLeague_id() {
        return league_id;
    }
    public void setLeague_id(int league_id) {
        this.league_id = league_id;
    }

}
