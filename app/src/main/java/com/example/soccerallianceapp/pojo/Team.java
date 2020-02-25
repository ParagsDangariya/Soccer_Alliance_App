package com.example.soccerallianceapp.pojo;

public class Team {

    private int team_id;

    private String name;
    private String logo;
    private String team_label;
    private String user_id;

    public Team(int team_id, String name, String logo, String team_label, String user_id) {
        this.team_id = team_id;
        this.name = name;
        this.logo = logo;
        this.team_label = team_label;
        this.user_id = user_id;
    }

    public int getTeam_id() {
        return team_id;
    }
    public void setTeam_id(int team_id) {
        this.team_id = team_id;
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
    public String getTeam_label() {
        return team_label;
    }
    public void setTeam_label(String team_label) {
        this.team_label = team_label;
    }
    public String getUser_id() {
        return user_id;
    }
    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }


}