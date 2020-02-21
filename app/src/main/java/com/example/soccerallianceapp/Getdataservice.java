package com.example.soccerallianceapp;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
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


}
