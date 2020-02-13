package com.example.soccerallianceapp;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.soccer_alliance_project_test.R;
import com.google.android.material.button.MaterialButton;


public class LeagueOperationsFragment extends Fragment implements View.OnClickListener{


    public NavController DashboardNavController;
    private Context context;
    MaterialButton league_operations_addteam_btn,league_operations_removeteam_btn,
            league_operations_teamlist_btn,league_operations_upmatch_btn,
            league_operations_schedulematch_btn,league_operations_playedmatch_btn;


    public LeagueOperationsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        DashboardNavController = Navigation.findNavController(getActivity(),R.id.dashboard_host_fragment);
        context = getActivity().getApplicationContext();

        league_operations_addteam_btn = view.findViewById(R.id.league_operations_addteam_btn);
        league_operations_addteam_btn.setOnClickListener(this);

        league_operations_removeteam_btn = view.findViewById(R.id.league_operations_removeteam_btn);
        league_operations_removeteam_btn.setOnClickListener(this);

        league_operations_teamlist_btn = view.findViewById(R.id.league_operations_teamlist_btn);
        league_operations_teamlist_btn.setOnClickListener(this);

        league_operations_upmatch_btn = view.findViewById(R.id.league_operations_upmatch_btn);
        league_operations_upmatch_btn.setOnClickListener(this);

        league_operations_schedulematch_btn = view.findViewById(R.id.league_operations_schedulematch_btn);
        league_operations_schedulematch_btn.setOnClickListener(this);

        league_operations_playedmatch_btn = view.findViewById(R.id.league_operations_playedmatch_btn);
        league_operations_playedmatch_btn.setOnClickListener(this);






    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_league_operations, container, false);
    }

    @Override
    public void onClick(View v) {

    }
}
