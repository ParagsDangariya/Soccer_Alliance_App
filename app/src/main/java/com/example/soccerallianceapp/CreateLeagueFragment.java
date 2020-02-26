package com.example.soccerallianceapp;



import android.content.Context;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;


import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;

import android.widget.ImageView;
import android.widget.Toast;

import com.example.soccer_alliance_project_test.R;

import com.example.soccerallianceapp.createleague.CreateLeagueBinClass;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CreateLeagueFragment extends Fragment implements View.OnClickListener {
    private ImageView league_img;
    private TextInputLayout league_name, no_of_leagues;
    private TextInputEditText edt_league_name, edit_no_of_teams;
    private Button create_league_btn;
    public NavController navController;
    private Context context;
    Getdataservice service;
     FirebaseAuth fAuth;
     String uid = "";


    String name, user_id, noOfTeams;
    int league_id, no_of_teams;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_create_league, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        context = getActivity().getApplicationContext();
        navController = Navigation.findNavController(getActivity(), R.id.host_fragment);

        league_name = view.findViewById(R.id.league_name_layout);
        no_of_leagues = view.findViewById(R.id.number_team_layout);
        edt_league_name = view.findViewById(R.id.league_name_edt_txt);
        edit_no_of_teams = view.findViewById(R.id.number_team_edt_txt);
        create_league_btn = view.findViewById(R.id.tmp_update_btn);
/*
        create_league_btn.setOnClickListener(this);
        edt_league_name.setOnEditorActionListener(this);
        edit_no_of_teams.setOnEditorActionListener(this);

 */
        // Constants.HideErrorOnTyping(league_name);
        // Constants.HideErrorOnTyping(no_of_leagues);
        fAuth = FirebaseAuth.getInstance();

        service = RetroFitInstance.getRetrofitInstance().create(Getdataservice.class);

    }


    @Override
    public void onClick(View v) {
        if (v == create_league_btn) {

            name = edt_league_name.getEditableText().toString().trim();
            noOfTeams = edit_no_of_teams.toString();
            no_of_teams = Integer.parseInt(noOfTeams);

            System.out.println("no_of_teams" + no_of_teams);

            if (TextUtils.isEmpty(name)) {
                edt_league_name.setError("Please enter league name!!");
                return;
            }
            if (TextUtils.isEmpty(noOfTeams)) {
                edit_no_of_teams.setError("Please enter number of Teams!!");
                return;
            }

           // uid =fAuth.getCurrentUser().getUid();
            uid = fAuth.getCurrentUser().getUid();

            System.out.println("current user:" +user_id);
            String logo="Nophoto";


            CreateLeagueBinClass league = new CreateLeagueBinClass(league_id, name, logo, no_of_teams, user_id);

            Call<CreateLeagueBinClass> call = service.createLeague(league);

            call.enqueue(new Callback<CreateLeagueBinClass>() {
                @Override
                public void onResponse(Call<CreateLeagueBinClass> call, Response<CreateLeagueBinClass> response) {
                    if (!response.isSuccessful()) {
                        int s = response.code();
                        System.out.println("code" + s);
                    }
                    int s = response.code();
                    Toast.makeText(context, "League is Succesfully created...." + s, Toast.LENGTH_LONG).show();
                }

                @Override
                public void onFailure(Call<CreateLeagueBinClass> call, Throwable t) {
                    System.out.println("error" + t.getMessage());
                    Toast.makeText(context, " no more hopes....", Toast.LENGTH_LONG).show();

                    navController.navigate(R.id.leagues_Fragment);
                }
            });


        }


    }

}

