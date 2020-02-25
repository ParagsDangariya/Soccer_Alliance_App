package com.example.soccerallianceapp.pojo;

public class League {

	private int league_id;
	private String name;
	private String logo;
	private int no_of_teams;
	private String user_id;

	public League(int league_id, String name, String logo, int no_of_teams, String user_id) {
		this.league_id = league_id;
		this.name = name;
		this.logo = logo;
		this.no_of_teams = no_of_teams;
		this.user_id = user_id;
	}

	public int getLeague_id() {
		return league_id;
	}
	public void setLeague_id(int league_id) {
		this.league_id = league_id;
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
	public int getNo_of_teams() {
		return no_of_teams;
	}
	public void setNo_of_teams(int no_of_teams) {
		this.no_of_teams = no_of_teams;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	
}
