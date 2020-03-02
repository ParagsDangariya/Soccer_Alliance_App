package com.example.soccerallianceapp;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.soccer_alliance_project_test.R;
import com.example.soccerallianceapp.pojo.matchScore.MatchScoreDisplay;
import com.example.soccerallianceapp.pojo.matchScore.MatchScores;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MatchScoreUpdateFragment extends Fragment {

    public NavController DashboardNavController;

    MatchScores matchscore;

    int matchid = 1;
    int teamid = 1;

    private Context context;

    private ArrayList<Comman_Data_List> comman_data_List;

    private Comman_adapter comman_adapter;


     TextInputEditText team1_goal_edt_txt,shots_team1_edt_txt,shotsontarget_team1_edt_txt,possession_team1_edt_txt,passes_team1_edt_txt,fouls_team1_edt_txt;

    TextInputEditText passaccuracy_team1_edt_txt,redcard_team1_edt_txt,offsides_team1_edt_txt,corners_team1_edt_txt,yellowcards_team1_edt_txt;


    int goal,shots,shotsontarget,possession,passes,passaccuracy,redcard,offsides,corners,fouls,yellowcard;

    Button Schedule_match_btn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_match_score_update, container, false);


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        DashboardNavController = Navigation.findNavController(getActivity(),R.id.dashboard_host_fragment);

        /*--------league Adapter Configuration--------*/

        context = getActivity().getApplicationContext();

        comman_data_List = new ArrayList<Comman_Data_List>();

        comman_adapter = new Comman_adapter(comman_data_List,context);

        team1_goal_edt_txt = view.findViewById(R.id.team1_goal_edt_txt);
        shots_team1_edt_txt = view.findViewById(R.id.shots_team1_edt_txt);
        shotsontarget_team1_edt_txt = view.findViewById(R.id.shotsontarget_team1_edt_txt);
        possession_team1_edt_txt = view.findViewById(R.id.possession_team1_edt_txt);
        passes_team1_edt_txt = view.findViewById(R.id.passes_team1_edt_txt);
        passaccuracy_team1_edt_txt = view.findViewById(R.id.passaccuracy_team1_edt_txt);
        redcard_team1_edt_txt = view.findViewById(R.id.redcard_team1_edt_txt);
        offsides_team1_edt_txt = view.findViewById(R.id.offsides_team1_edt_txt);
        corners_team1_edt_txt = view.findViewById(R.id.corners_team1_edt_txt);
        yellowcards_team1_edt_txt = view.findViewById(R.id.yellowcards_team1_edt_txt);
        fouls_team1_edt_txt = view.findViewById(R.id.fouls_team1_edt_txt);

        Schedule_match_btn = view.findViewById(R.id.Schedule_match_btn);






        try {


            Getdataservice service = RetroFitInstance.getRetrofitInstance().create(Getdataservice.class);

            Log.d("step1", "after getService part");

            Call<MatchScoreDisplay> displayscore = service.getMatchScores(matchid, teamid);

            displayscore.enqueue(new Callback<MatchScoreDisplay>() {
                @Override
                public void onResponse(Call<MatchScoreDisplay> call, Response<MatchScoreDisplay> response) {

                    Log.d("step2", "after onResponse");


                    MatchScoreDisplay data = response.body();

                    System.out.println(data);

                    System.out.println("response" + response.body().toString());

                    matchscore = data.getMatchScores();

                    goal = matchscore.getGoal();
                    shots = matchscore.getShots();
                    shotsontarget = matchscore.getShotsOnTarget();
                    passaccuracy = matchscore.getPassAccuracy();
                    passes = matchscore.getPasses();
                    redcard = matchscore.getRedCards();
                    possession = matchscore.getPossession();
                    corners = matchscore.getCorners();
                    offsides = matchscore.getOffsides();
                    fouls = matchscore.getFouls();
                    yellowcard =matchscore.getYellowCards();

                    team1_goal_edt_txt.setText(String.valueOf(goal));
                    shots_team1_edt_txt.setText(String.valueOf(shots));
                    shotsontarget_team1_edt_txt.setText(String.valueOf(shotsontarget));
                    possession_team1_edt_txt.setText(String.valueOf(possession));
                    passes_team1_edt_txt.setText(String.valueOf(passes));
                    passaccuracy_team1_edt_txt.setText(String.valueOf(passaccuracy));
                    redcard_team1_edt_txt.setText(String.valueOf(redcard));
                    corners_team1_edt_txt.setText(String.valueOf(corners));

                    offsides_team1_edt_txt.setText(String.valueOf(offsides));
                    yellowcards_team1_edt_txt.setText(String.valueOf(yellowcard));
                    fouls_team1_edt_txt.setText(String.valueOf(fouls));


                    System.out.println(team1_goal_edt_txt);




                }

                @Override
                public void onFailure(Call<MatchScoreDisplay> call, Throwable t) {


                    System.out.println("Error : " + t.getMessage());

                }
            });


            Schedule_match_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {









                }
            });






        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
