package com.example.soccerallianceapp;

class Leagues_List {

    private String league_name;
    private int league_image;

    public Leagues_List(String league_name, int league_image) {
        this.league_name = league_name;
        this.league_image = league_image;
    }

    public String getLeague_name() {
        return league_name;
    }

    public int getLeague_image() {
        return league_image;
    }
}

