package com.example.soccerallianceapp;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.soccer_alliance_project_test.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class Team_mngr_my_profile_Fragment extends Fragment {


    public Team_mngr_my_profile_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_team_mngr_my_profile_, container, false);
    }

}
