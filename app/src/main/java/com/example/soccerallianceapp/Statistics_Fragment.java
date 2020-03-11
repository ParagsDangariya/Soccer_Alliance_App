package com.example.soccerallianceapp;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.soccer_alliance_project_test.R;
import com.example.soccerallianceapp.pojo.MatchScore_PlayedMatchStatastics.MatchScorePlayedmatchStatastics;
import com.example.soccerallianceapp.pojo.MatchScore_PlayedMatchStatastics.MatchScores;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Statistics_Fragment extends Fragment {


    TextView sts_team1,sts_team2,sts_date,sts_time,sts_team1_goal,sts_team2_goal;
    ImageView sts_team1_logo,sts_team2_logo;

    TextView shot_t1, shot_t2,
            shot_on_target_t1, shot_on_target_t2,
            possession_t1, possession_t2,
            passes_t1, passes_t2,
            pass_accuracy_t1, pass_accuracy_t2,
            fouls_t1, fouls_t2,
            yellow_cards_t1, yellow_cards_t2,
            red_cards_t1, red_cards_t2,
            offsides_t1, offsides_t2,
            corners_t1,corners_t2;
    int matchid;
     private ProgressBar progressBar;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (this.getArguments() != null){
            matchid = getArguments().getInt("match_id");
            System.out.println("on create of statistics"+matchid);
        }
        System.out.println("on create of statistics outside");



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_statistics_, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

       /* progressBar=view.findViewById(R.id.progressbar);
        progressBar.setVisibility(View.VISIBLE);
*/
        sts_team1 = view.findViewById(R.id.sts_team1);
        sts_team2 = view.findViewById(R.id.sts_team2);
        sts_date = view.findViewById(R.id.sts_date);
        sts_time = view.findViewById(R.id.sts_time);
        sts_team1_goal = view.findViewById(R.id.sts_team1_goal);
        sts_team2_goal = view.findViewById(R.id.sts_team2_goal);
        sts_team1_logo = view.findViewById(R.id.sts_team1_logo);
        sts_team2_logo = view.findViewById(R.id.sts_team2_logo);

        shot_t1 = view.findViewById(R.id.shot_t1);
        shot_t2 = view.findViewById(R.id.shot_t2);
        shot_on_target_t1 = view.findViewById(R.id.shot_on_target_t1);
        shot_on_target_t2 = view.findViewById(R.id.shot_on_target_t2);
        possession_t1 = view.findViewById(R.id.possession_t1);
        possession_t2 = view.findViewById(R.id.possession_t2);
        passes_t1 = view.findViewById(R.id.passes_t1);
        passes_t2 = view.findViewById(R.id.passes_t2);
        pass_accuracy_t1 = view.findViewById(R.id.pass_accuracy_t1);
        pass_accuracy_t2 = view.findViewById(R.id.pass_accuracy_t2);
        fouls_t1 = view.findViewById(R.id.fouls_t1);
        fouls_t2 = view.findViewById(R.id.fouls_t2);
        yellow_cards_t1 = view.findViewById(R.id.yellow_cards_t1);
        yellow_cards_t2 = view.findViewById(R.id.yellow_cards_t2);
        red_cards_t1 = view.findViewById(R.id.red_cards_t1);
        red_cards_t2 = view.findViewById(R.id.red_cards_t2);
        offsides_t1 = view.findViewById(R.id.offsides_t1);
        offsides_t2 = view.findViewById(R.id.offsides_t2);
        corners_t1 = view.findViewById(R.id.corners_t1);
        corners_t2 = view.findViewById(R.id.corners_t2);

    }

    @Override
    public void onResume() {
        super.onResume();
        getTeam1Score(1,1);
        getTeam2Score(2,1);
    }

    private void getTeam1Score(int match_id,int team_id){

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
                        shot_t1.setText(String.valueOf(realData.getMatchScores().getShots()));
                        shot_on_target_t1.setText(String.valueOf(realData.getMatchScores().getShotsOnTarget()));
                        possession_t1.setText(String.valueOf(realData.getMatchScores().getPossession()));
                        passes_t1.setText(String.valueOf(realData.getMatchScores().getPasses()));
                        pass_accuracy_t1.setText(String.valueOf(realData.getMatchScores().getPassAccuracy()));
                        fouls_t1.setText(String.valueOf(realData.getMatchScores().getFouls()));
                        yellow_cards_t1.setText(String.valueOf(realData.getMatchScores().getYellowCards()));
                        red_cards_t1.setText(String.valueOf(realData.getMatchScores().getRedCards()));
                        offsides_t1.setText(String.valueOf(realData.getMatchScores().getOffsides()));
                        corners_t1.setText(String.valueOf(realData.getMatchScores().getCorners()));
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
                        shot_t2.setText(String.valueOf(realData.getMatchScores().getShots()));
                        shot_on_target_t2.setText(String.valueOf(realData.getMatchScores().getShotsOnTarget()));
                        possession_t2.setText(String.valueOf(realData.getMatchScores().getPossession()));
                        passes_t2.setText(String.valueOf(realData.getMatchScores().getPasses()));
                        pass_accuracy_t2.setText(String.valueOf(realData.getMatchScores().getPassAccuracy()));
                        fouls_t2.setText(String.valueOf(realData.getMatchScores().getFouls()));
                        yellow_cards_t2.setText(String.valueOf(realData.getMatchScores().getYellowCards()));
                        red_cards_t2.setText(String.valueOf(realData.getMatchScores().getRedCards()));
                        offsides_t2.setText(String.valueOf(realData.getMatchScores().getOffsides()));
                        corners_t2.setText(String.valueOf(realData.getMatchScores().getCorners()));
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
