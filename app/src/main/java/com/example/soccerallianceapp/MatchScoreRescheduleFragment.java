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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.soccer_alliance_project_test.R;


public class MatchScoreRescheduleFragment extends Fragment implements View.OnClickListener{

    public NavController DashboardNavController;
    // private ProgressBar progressbar;
    private Context context;
    int league_id,up_match_id,up_team1_id,up_team2_id;
    String up_team1name,up_team2name,up_team1icon,up_team2icon,up_match_date,up_match_time;

    ImageView team1_logo,team2_logo;
    TextView team1,team2;
    Button ReSchedule_match_btn,Update_Score_btn,Cancel_match_btn;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_match_score_reschedule, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        DashboardNavController = Navigation.findNavController(getActivity(), R.id.dashboard_host_fragment);
        context = getActivity().getApplicationContext();

        team1_logo = view.findViewById(R.id.team1_logo);
        team2_logo = view.findViewById(R.id.team2_logo);
        team1 = view.findViewById(R.id.team1);
        team2 = view.findViewById(R.id.team2);
        ReSchedule_match_btn = view.findViewById(R.id.ReSchedule_match_btn);
        ReSchedule_match_btn.setOnClickListener(this);
        Update_Score_btn = view.findViewById(R.id.Update_Score_btn);
        Update_Score_btn.setOnClickListener(this);
        Cancel_match_btn = view.findViewById(R.id.Cancel_match_btn);
        Cancel_match_btn.setOnClickListener(this);

        Bundle bundle = getArguments();

        if (getArguments() != null) {
            if (getArguments().getString("ComingFrom").equals("LeagueUpcomingMatchFragment")) {
                league_id = getArguments().getInt("League_id");
                System.out.println("league id from league upcoming match " + league_id);
                up_match_id = getArguments().getInt("up_match_id");
                up_team1_id = getArguments().getInt("up_team1_id");
                up_team2_id = getArguments().getInt("up_team2_id");
                up_team1name = getArguments().getString("up_team1name");
                up_team2name = getArguments().getString("up_team2name");
                up_team1icon = getArguments().getString("up_team1logo");
                up_team2icon = getArguments().getString("up_team2logo");
                up_match_date = getArguments().getString("up_match_date");
                up_match_time = getArguments().getString("up_match_time");


                team1.setText(up_team1name);
                team2.setText(up_team2name);

                Glide.with(context).load(up_team1icon).fitCenter().into(team1_logo);
                Glide.with(context).load(up_team2icon).fitCenter().into(team2_logo);

                // DashboardNavController.navigate(R.id.matchScoreUpdateFragment, bundle);

            }

        }
    }
    @Override
    public void onClick(View v) {

        if (v == ReSchedule_match_btn){
            Bundle bundlematch = new Bundle();
            bundlematch.putString("Coming_from","Reschedulematch");
            bundlematch.putInt("League_id",league_id);
            bundlematch.putInt("up_match_id", up_match_id);
            bundlematch.putInt("up_team1_id" ,up_team1_id);
            bundlematch.putInt("up_team2_id",up_team2_id);
            bundlematch.putString("up_team1name",up_team1name);
            bundlematch.putString("up_team2name",up_team2name);
            bundlematch.putString("up_team1logo",up_team1icon);
            bundlematch.putString("up_team2logo",up_team2icon);
            bundlematch.putString("up_match_date",up_match_date);
            bundlematch.putString("up_match_time",up_match_time);

            DashboardNavController.navigate(R.id.rescheduleMatchFragment,bundlematch);
        }
        if (v == Update_Score_btn){
            Bundle bundlematch = new Bundle();
            bundlematch.putString("Coming_from","updateScore");

            bundlematch.putInt("League_id",league_id);
            bundlematch.putInt("up_match_id", up_match_id);
            bundlematch.putInt("up_team1_id" ,up_team1_id);
            bundlematch.putInt("up_team2_id",up_team2_id);
            bundlematch.putString("up_team1name",up_team1name);
            bundlematch.putString("up_team2name",up_team2name);
            bundlematch.putString("up_team1logo",up_team1icon);
            bundlematch.putString("up_team2logo",up_team2icon);
            bundlematch.putString("up_match_date",up_match_date);
            bundlematch.putString("up_match_time",up_match_time);

            DashboardNavController.navigate(R.id.matchScoreUpdateFragment,bundlematch);

        }




    }
}
