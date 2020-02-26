package com.example.soccerallianceapp;

import com.example.soccerallianceapp.pojo.leaguelistbyuserId.LeagueList;
import com.example.soccerallianceapp.pojo.leaguelistbyuserId.LeaguesFragment;
import com.example.soccerallianceapp.pojo.viewregisteruserdetail.User;
import com.example.soccerallianceapp.pojo.viewregisteruserdetail.ViewregisterUserDetail;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
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


    @GET("ViewregisterUserDetail&{uid}")
    Call<ViewregisterUserDetail> ViewregisterUserDetail(

            @Path("uid") String uid
    );


    @GET("LeagueListByUser&{user_id}")
    Call<LeaguesFragment> getLeagueList(@Path("user_id") String user_id);






}
