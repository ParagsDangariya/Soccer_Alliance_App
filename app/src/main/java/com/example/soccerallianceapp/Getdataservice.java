package com.example.soccerallianceapp;

import com.example.soccerallianceapp.pojo.listLeagueDashboard.League;
import com.example.soccerallianceapp.pojo.listLeagueDashboard.ListLeagueDashboard;
import com.example.soccerallianceapp.pojo.listOfCountries.ListOfCountries;
import com.example.soccerallianceapp.pojo.matchListDashboard.MatchListDashboard;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
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

    @FormUrlEncoded
    @POST("viewTeamListFromLeagueId")
    Call<ListOfCountries> getviewTeamListFromLeagueIdCall(@Field("league_id") String league_id);


   /*
    This service is unavavlible
    @GET("viewTeamList")
    Call<ListOfCountries> getviewTeamListCall();
*/
   @GET("upcomingMatches_guestDashboard")
   Call<MatchListDashboard> getupcomingMatches_guestDashboardCall();



}
