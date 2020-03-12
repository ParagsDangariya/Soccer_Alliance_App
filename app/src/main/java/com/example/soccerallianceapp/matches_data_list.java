package com.example.soccerallianceapp;

public class matches_data_list {

    Integer match_id,team1Id,team2Id,schedule_id;
    String team1_name,team1_logo,team2_name,team2_logo,match_date;

    public matches_data_list(Integer match_id, Integer team1Id, Integer team2Id, Integer schedule_id, String team1_name, String team1_logo, String team2_name, String team2_logo, String match_date) {
        this.match_id = match_id;
        this.team1Id = team1Id;
        this.team2Id = team2Id;
        this.schedule_id = schedule_id;
        this.team1_name = team1_name;
        this.team1_logo = team1_logo;
        this.team2_name = team2_name;
        this.team2_logo = team2_logo;
        this.match_date = match_date;
        }


        public matches_data_list(Integer matchId, int match_id, String team1_name, String team1_logo, Integer team2Id, String team2_name, String team2_logo, String match_date) {
            this.match_id = match_id;
            this.team1_name = team1_name;
            this.team1_logo = team1_logo;
            this.team2_name = team2_name;
            this.team2_logo = team2_logo;
            this.match_date = match_date;
        }



//    public matches_data_list(Integer match_id, Integer team1Id, Integer team2Id, String team1_name, String team1_logo, String team2_name, String team2_logo, String match_date, String match_time) {
//        this.match_id = match_id;
//        this.team1Id = team1Id;
//        this.team2Id = team2Id;
//        this.team1_name = team1_name;
//        this.team1_logo = team1_logo;
//        this.team2_name = team2_name;
//        this.team2_logo = team2_logo;
//        this.match_date = match_date;
//        this.match_time = match_time;
//    }

//    public matches_data_list(Integer matchId, Integer team1Id, String team1, String team1Logo, Integer team2Id, String team2, String team2Logo, String dateOfMatch) {
//        this.match_id = matchId;
//        this.team1Id = team1Id;
//        this.team2Id = team2Id;
//        this.team1_name = team1;
//        this.team1_logo = team1Logo;
//        this.team2_name = team2;
//        this.team2_logo = team2Logo;
//        this.match_date = dateOfMatch;
//        this.match_time = match_time;
//    }


/*public matches_data_list(String team1, String team1Logo, String team2, String team2Logo, String dateOfMatch) {
        this.team1_name = team1_name;
        this.team1_logo = team1_logo;
        this.team2_name = team2_name;
        this.team2_logo = team2_logo;
        this.match_date = match_date;

    }*/

    public Integer getMatch_id() {
        return match_id;
    }

    public void setMatch_id(Integer match_id) {
        this.match_id = match_id;
    }

    public Integer getTeam1Id() {
        return team1Id;
    }

    public void setTeam1Id(Integer team1Id) {
        this.team1Id = team1Id;
    }

    public Integer getSchedule_id() {
        return schedule_id;
    }

    public void setSchedule_id(Integer schedule_id) {
        this.schedule_id = schedule_id;
    }

    public Integer getTeam2Id() {
        return team2Id;
    }

    public void setTeam2Id(Integer team2Id) {
        this.team2Id = team2Id;
    }

    public String getTeam1_name() {
        return team1_name;
    }

    public void setTeam1_name(String team1_name) {
        this.team1_name = team1_name;
    }

    public String getTeam1_logo() {
        return team1_logo;
    }

    public void setTeam1_logo(String team1_logo) {
        this.team1_logo = team1_logo;
    }

    public String getTeam2_name() {
        return team2_name;
    }

    public void setTeam2_name(String team2_name) {
        this.team2_name = team2_name;
    }

    public String getTeam2_logo() {
        return team2_logo;
    }

    public void setTeam2_logo(String team2_logo) {
        this.team2_logo = team2_logo;
    }

    public String getMatch_date() {
        return match_date;
    }

    public void setMatch_date(String match_date) {
        this.match_date = match_date;
    }

}
