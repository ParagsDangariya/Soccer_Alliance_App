package com.example.soccerallianceapp;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.soccer_alliance_project_test.R;
import com.google.android.material.card.MaterialCardView;


public class Matches_Fragment extends Fragment implements View.OnClickListener {


    MaterialCardView um_cardView1,pm_cardView1;
    public NavController DashboardNavController;
    private Context context;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_matches, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        DashboardNavController = Navigation.findNavController(getActivity(),R.id.dashboard_host_fragment);
        context = getActivity().getApplicationContext();

        um_cardView1 = view.findViewById(R.id.um_cardView1);
        um_cardView1.setOnClickListener(this);

        pm_cardView1 = view.findViewById(R.id.pm_cardView1);
        pm_cardView1.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if(view == um_cardView1){
            DashboardNavController.navigate(R.id.upcomingMatchFragment);
        }
        else if(view == pm_cardView1){
            DashboardNavController.navigate(R.id.match_Score_Fragment);
        }
    }

}