package com.example.soccerallianceapp;

import com.example.soccerallianceapp.pojo.ListOfLeaguesByCountry.ListOfLeaguesByCountry;
import com.example.soccerallianceapp.pojo.ViewPlayerListByTeamDashboard.ViewPlayerListDashboard;
import com.example.soccerallianceapp.pojo.ViewTeamListByLeague.ViewTeamListByLeague;
import com.example.soccerallianceapp.pojo.ViewTeamListDashboard.ViewTeamList;
import com.example.soccerallianceapp.pojo.listLeagueDashboard.ListLeagueDashboard;
import com.example.soccerallianceapp.pojo.listOfCountries.ListOfCountries;
import com.example.soccerallianceapp.pojo.matchListDashboard.MatchListDashboard;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Getdataservice {

//    @GET("{geoId}")
//    Call<Weather> getWeather(@Path("geoId")int geoId);

    @GET("registerUser&{uid}&{Full_name}&{Email}&{Phone}&{gender}&{Country}&{Age}&{User_type}&{User_photo}")
    Call<ResponseBody> registerUser(

            @Path("uid") String uid,@Path("Full_name") String full_name,
            @Path("Email") String email, @Path("Phone") String phone,
            @Path("Gender") String gender, @Path("Country") String country,
            @Path("Age") String age,
            @Path("User_type") String user_type,
            @Path("User_photo") String user_photo
    );

    @GET("listOfLeague_guestDashboard")
    Call<ListLeagueDashboard> getListOfLeague_guestDashboardCall();


    @GET("ListOfCountries")
    Call<ListOfCountries> getListOfCountriesCall();


    @GET("viewTeamListFromLeagueId&{league_id}")
    Call<ViewTeamListByLeague> getviewTeamListFromLeagueIdCall(@Path("league_id") String league_id);


    @GET("viewTeamList")
    Call<ViewTeamList> getviewTeamListCall();


   @GET("upcomingMatches_guestDashboard")
   Call<MatchListDashboard> getupcomingMatches_guestDashboardCall();


    @GET("viewPlayerListFromTeam&{team_id}")
    Call<ViewPlayerListDashboard> getviewPlayerListFromTeamDashboardCall(@Path("team_id") int team_id);

    @GET("ListOfLeaguesByCountry&{country}")
    Call<ListOfLeaguesByCountry> getListOfLeaguesByCountryCall(@Path("country") String country);

}

