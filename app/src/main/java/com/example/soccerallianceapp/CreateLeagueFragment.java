package com.example.soccerallianceapp;



import android.content.Context;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;

import android.widget.ImageView;

import com.example.soccer_alliance_project_test.R;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;



public class CreateLeagueFragment extends Fragment implements View.OnClickListener{
    private ImageView league_img;
    private TextInputLayout league_name,no_of_leagues;
    private TextInputEditText edt_league_name,edit_no_of_teams;
    private Button create_league_btn;
    public NavController navController;
    private Context context;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_create_league, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        context = getActivity().getApplicationContext();
        navController = Navigation.findNavController(getActivity(),R.id.host_fragment);
        
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
    }


    @Override
    public void onClick(View v) {

    }
}






