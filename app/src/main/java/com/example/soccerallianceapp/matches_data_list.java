package com.example.soccerallianceapp;

public class matches_data_list {

    int match_id;
    String team1_name,team1_logo,team2_name,team2_logo,match_date,match_time;

    public matches_data_list(int match_id, String team1_name, String team1_logo, String team2_name, String team2_logo, String match_date, String match_time) {
        this.match_id = match_id;
        this.team1_name = team1_name;
        this.team1_logo = team1_logo;
        this.team2_name = team2_name;
        this.team2_logo = team2_logo;
        this.match_date = match_date;
        this.match_time = match_time;
    }

    public int getMatch_id() {
        return match_id;
    }

    public String getTeam1_name() {
        return team1_name;
    }

    public String getTeam1_logo() {
        return team1_logo;
    }

    public String getTeam2_name() {
        return team2_name;
    }

    public String getTeam2_logo() {
        return team2_logo;
    }

    public String getMatch_date() {
        return match_date;
    }

    public String getMatch_time() {
        return match_time;
    }
}
