package com.example.soccerallianceapp;

import com.example.soccerallianceapp.pojo.ListOfLeaguesByCountry.ListOfLeaguesByCountry;
import com.example.soccerallianceapp.pojo.Player;
import com.example.soccerallianceapp.pojo.ViewPlayerListByTeamDashboard.ViewPlayerListDashboard;
import com.example.soccerallianceapp.pojo.ViewTeamListByLeague.ViewTeamListByLeague;
import com.example.soccerallianceapp.pojo.ViewTeamListDashboard.ViewTeamList;
import com.example.soccerallianceapp.pojo.listLeagueDashboard.ListLeagueDashboard;
import com.example.soccerallianceapp.pojo.listOfCountries.ListOfCountries;
import com.example.soccerallianceapp.pojo.matchListDashboard.MatchListDashboard;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

import com.example.soccerallianceapp.pojo.Team;
import com.example.soccerallianceapp.pojo.User;

import com.example.soccerallianceapp.pojo.ViewTeamDetail.ViewTeamDetail;
import com.example.soccerallianceapp.pojo.viewregisteruserdetail.ViewregisterUserDetail;
import retrofit2.http.Body;

import retrofit2.http.Headers;
import retrofit2.http.POST;

import retrofit2.http.Path;

public interface Getdataservice {

//    @GET("{geoId}")
//    Call<Weather> getWeather(@Path("geoId")int geoId);



    //@FormUrlEncoded
    @Headers({"Content-Type:application/json"})
    @POST("registerUser")

    Call<User> registerUser(@Body User user);



    @POST("UpdateUserProfile")
    Call<User> UpdateUserProfile(@Body User user);





    @GET("ViewregisterUserDetail&{uid}")
    Call<ViewregisterUserDetail> ViewregisterUserDetail(

            @Path("uid") String uid
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

    @GET("ViewTeamDetail&{uid}")
    Call<ViewTeamDetail> ViewTeamDetail(

            @Path("uid") String uid
    );


    @POST("CreateTeam")
    Call<Team> CreateTeam(@Body Team team);



    @POST("UpdateTeam")
    Call<Team> UpdateTeam(@Body Team team);



    @GET("viewPlayerListFromTeam&{team_id}")
    Call<Team> viewPlayerListFromTeam(

            @Path("team_id") int team_id
    );

    @POST("AddPlayerInTeam")
    Call<Player> AddPlayerInTeam(@Body Player player);


}

