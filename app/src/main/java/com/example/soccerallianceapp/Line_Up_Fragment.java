package com.example.soccerallianceapp;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.soccer_alliance_project_test.R;
import com.example.soccerallianceapp.pojo.MatchScore_PlayedMatchStatastics.MatchScorePlayedmatchStatastics;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 *
 */
public class Line_Up_Fragment extends Fragment {

    private Context context;
    TextView sts_team1,sts_team2,sts_date,sts_time,sts_team1_goal,sts_team2_goal;
    ImageView sts_team1_logo,sts_team2_logo;


    int matchid,team1id,team2id;
    String team1,team2,logo1,logo2,date,time;

    Bundle bundle;
    private ProgressBar progressBar;


    public Line_Up_Fragment(Bundle bundle) {
        this.bundle = bundle;
    }


    public Line_Up_Fragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(bundle!=null){
            matchid = bundle.getInt("match_id");
            team1id = bundle.getInt("team1id");
            team2id = bundle.getInt("team2id");
            team1 = bundle.getString("team1");
            team2 = bundle.getString("team2");
            logo1 = bundle.getString("logo1");
            logo2 = bundle.getString("logo2");
            date = bundle.getString("date");
            time = bundle.getString("time");
            System.out.println("on create of statistics inside "+matchid);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_line_up, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        context = getActivity().getApplicationContext();

        sts_team1 = view.findViewById(R.id.team1);
        sts_team2 = view.findViewById(R.id.team2);
        sts_date = view.findViewById(R.id.date);
        sts_time = view.findViewById(R.id.time);
        sts_team1_goal = view.findViewById(R.id.team1_goal);
        sts_team2_goal = view.findViewById(R.id.team2_goal);
        sts_team1_logo = view.findViewById(R.id.team1_logo);
        sts_team2_logo = view.findViewById(R.id.team2_logo);

        sts_team1.setText(team1);
        sts_team2.setText(team2);
        Glide.with(context).load(logo1).fitCenter().into(sts_team1_logo);
        Glide.with(context).load(logo2).fitCenter().into(sts_team2_logo);

        sts_date.setText(date);
        sts_time.setText(time);

        getTeam1Score(matchid,team1id);
        getTeam2Score(matchid,team2id);
    }

    @Override
    public void onResume() {
        super.onResume();


    }

    private void getTeam1Score(int match_id, int team_id){

        Getdataservice service = RetroFitInstance.getRetrofitInstance().create(Getdataservice.class);

        Call<MatchScorePlayedmatchStatastics> scorelist = service.getMatchscore(team_id,match_id);

        scorelist.enqueue(new Callback<MatchScorePlayedmatchStatastics>() {
            @Override
            public void onResponse(Call<MatchScorePlayedmatchStatastics> call, Response<MatchScorePlayedmatchStatastics> response) {

                MatchScorePlayedmatchStatastics realData = response.body();

                if (response.body() != null) {
                    if (realData.getStatus() == 200) {
                        // sts_team1_logo.setImageResource(R.drawable.);
                        sts_team1_goal.setText(String.valueOf(realData.getMatchScores().getGoal()));

                        System.out.println("goal 1:"+realData.getMatchScores().getGoal());
                    }
//                    progressBar.setVisibility(View.VISIBLE);
                } else {
                    Toast.makeText(getActivity(), "Response empty", Toast.LENGTH_LONG).show();
                    //   progressBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<MatchScorePlayedmatchStatastics> call, Throwable t) {

            }
        });

    }

    private void getTeam2Score(int match_id,int team_id){

        Getdataservice service = RetroFitInstance.getRetrofitInstance().create(Getdataservice.class);

        Call<MatchScorePlayedmatchStatastics> scorelist = service.getMatchscore(team_id,match_id);

        scorelist.enqueue(new Callback<MatchScorePlayedmatchStatastics>() {
            @Override
            public void onResponse(Call<MatchScorePlayedmatchStatastics> call, Response<MatchScorePlayedmatchStatastics> response) {

                MatchScorePlayedmatchStatastics realData = response.body();

                if (response.body() != null) {
                    if (realData.getStatus() == 200) {
                        sts_team2_goal.setText(String.valueOf(realData.getMatchScores().getGoal()));

                        System.out.println("goal 2:"+realData.getMatchScores().getGoal());
                        System.out.println();
                    }
                    // progressBar.setVisibility(View.VISIBLE);
                } else {
                    Toast.makeText(getActivity(), "Response empty", Toast.LENGTH_LONG).show();
                    //progressBar.setVisibility(View.GONE);
                }

            }

            @Override
            public void onFailure(Call<MatchScorePlayedmatchStatastics> call, Throwable t) {

            }
        });



    }
}
