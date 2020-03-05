package com.example.soccerallianceapp;

import android.widget.Toast;

import com.example.soccerallianceapp.pojo.leaguelistbyuserId.LeagueList;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroFitInstance {

    private static Retrofit retrofit;
    private static final String BASE_URL = "https://soccerallianceapp.appspot.com/rest/api/";

    public static Retrofit getRetrofitInstance(){

        if (retrofit == null){

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        }

        // System.out.println(retrofit);
        return retrofit;

    }

}