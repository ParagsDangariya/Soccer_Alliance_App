package com.example.soccerallianceapp;

import com.example.soccerallianceapp.pojo.viewregisteruserdetail.ViewregisterUserDetail;

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


    @FormUrlEncoded
    @POST("registerUser")
    Call<ResponseBody> registerUser(

            @Field("uid") String uid, @Field("fullName") String full_name,
            @Field("email") String email, @Field("phone") String phone,
            @Field("gender") String gender, @Field("country") String country,
            @Field("age") String age,
            @Field("user_type") String user_type,
            @Field("user_photo") String user_photo
    );
    @GET("ViewregisterUserDetail&{uid}")
    Call<ViewregisterUserDetail> ViewregisterUserDetail(

            @Path("uid") String uid
    );




}
