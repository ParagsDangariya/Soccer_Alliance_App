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
import android.widget.Toast;

import com.example.soccer_alliance_project_test.R;
import com.example.soccerallianceapp.pojo.matchScore.MatchScoreDisplay;
import com.example.soccerallianceapp.pojo.matchScore.MatchScores;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MatchScoreUpdateFragment extends Fragment implements View.OnClickListener {

    public NavController DashboardNavController;
    MatchScores matchscore;
    MatchScores matchScoresteam2;

    int matchid = 3;
    int teamid = 1;
    int matchid2= 3;
    int team2id= 17;



    private Context context;
    private ArrayList<Comman_Data_List> comman_data_List;
    private Comman_adapter comman_adapter;
    Getdataservice service;

    TextInputEditText team1_goal_edt_txt,shots_team1_edt_txt,shotsontarget_team1_edt_txt,possession_team1_edt_txt,passes_team1_edt_txt,fouls_team1_edt_txt;
    TextInputEditText passaccuracy_team1_edt_txt,redcard_team1_edt_txt,offsides_team1_edt_txt,corners_team1_edt_txt,yellowcards_team1_edt_txt;
    TextInputEditText  team2_goal_edt_txt,shots_team2_edt_txt,shotsontarget_team2_edt_txt,possession_team2_edt_txt,passes_team2_edt_txt,fouls_team2_edt_txt;
    TextInputEditText passaccuracy_team2_edt_txt,redcard_team2_edt_txt,offsides_team2_edt_txt,corners_team2_edt_txt,yellowcards_team2_edt_txt;
    MaterialButton Update_Score_btn;

    int goalteam1,shots,shotsontarget,possession,passes,passaccuracy,redcard,offsides,corners,fouls,yellowcard;
    int goal2,shots2,shotsontarget2,possession2,passes2,passaccuracy2,redcard2,offsides2,corners2,fouls2,yellowcard2;


    //Button Schedule_match_btn;

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



        team2_goal_edt_txt = view.findViewById(R.id.team2_goal_edt_txt);
        shots_team2_edt_txt = view.findViewById(R.id.shots_team2_edt_txt);
        shotsontarget_team2_edt_txt = view.findViewById(R.id.shotsontarget_team2_edt_txt);
        possession_team2_edt_txt = view.findViewById(R.id.possession_team2_edt_txt);
        passes_team2_edt_txt = view.findViewById(R.id.passes_team2_edt_txt);
        passaccuracy_team2_edt_txt = view.findViewById(R.id.passaccuracy_team2_edt_txt);
        redcard_team2_edt_txt = view.findViewById(R.id.redcard_team2_edt_txt);
        offsides_team2_edt_txt = view.findViewById(R.id.offsides_team2_edt_txt);
        corners_team2_edt_txt = view.findViewById(R.id.corners_team2_edt_txt);
        yellowcards_team2_edt_txt = view.findViewById(R.id.yellowcards_team2_edt_txt);
        fouls_team2_edt_txt = view.findViewById(R.id.fouls_team2_edt_txt);


        Update_Score_btn = view.findViewById(R.id.Update_Score_btn);
        Update_Score_btn.setOnClickListener(this);


        try {


            service = RetroFitInstance.getRetrofitInstance().create(Getdataservice.class);

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

                    goalteam1 = matchscore.getGoal();
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

                    team1_goal_edt_txt.setText(String.valueOf(goalteam1));
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


            Call<MatchScoreDisplay> displayscore2 = service.getMatchScores(matchid2,team2id);

            displayscore2.enqueue(new Callback<MatchScoreDisplay>() {
                @Override
                public void onResponse(Call<MatchScoreDisplay> call, Response<MatchScoreDisplay> response) {

                    Log.d("step2", "after 2nd onResponse");


                    MatchScoreDisplay data1 = response.body();

                    System.out.println(data1);

                    System.out.println("response" + response.body().toString());

                    matchscore = data1.getMatchScores();

                    goal2 = matchscore.getGoal();
                    shots2 = matchscore.getShots();
                    shotsontarget2 = matchscore.getShotsOnTarget();
                    passaccuracy2 = matchscore.getPassAccuracy();
                    passes2 = matchscore.getPasses();
                    redcard2 = matchscore.getRedCards();
                    possession2 = matchscore.getPossession();
                    corners2 = matchscore.getCorners();
                    offsides2 = matchscore.getOffsides();
                    fouls2 = matchscore.getFouls();
                    yellowcard2 =matchscore.getYellowCards();

                    team2_goal_edt_txt.setText(String.valueOf(goal2));
                    shots_team2_edt_txt.setText(String.valueOf(shots2));
                    shotsontarget_team2_edt_txt.setText(String.valueOf(shotsontarget2));
                    possession_team2_edt_txt.setText(String.valueOf(possession2));
                    passes_team2_edt_txt.setText(String.valueOf(passes2));
                    passaccuracy_team2_edt_txt.setText(String.valueOf(passaccuracy2));
                    redcard_team2_edt_txt.setText(String.valueOf(redcard2));
                    corners_team2_edt_txt.setText(String.valueOf(corners2));

                    offsides_team2_edt_txt.setText(String.valueOf(offsides2));
                    yellowcards_team2_edt_txt.setText(String.valueOf(yellowcard2));
                    fouls_team2_edt_txt.setText(String.valueOf(fouls2));

                    System.out.println(team2_goal_edt_txt);


                }

                @Override
                public void onFailure(Call<MatchScoreDisplay> call, Throwable t) {

                }
            });


        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    public void onClick(View v) {
        if(v == Update_Score_btn){

            goalteam1 = Integer.parseInt(team1_goal_edt_txt.getEditableText().toString().trim());
            shots = Integer.parseInt(shots_team1_edt_txt.getEditableText().toString().trim());
            shotsontarget = Integer.parseInt(shotsontarget_team1_edt_txt.getEditableText().toString().trim());
            possession2 = Integer.parseInt(possession_team1_edt_txt.getEditableText().toString().trim());
            fouls2 = Integer.parseInt(fouls_team1_edt_txt.getEditableText().toString().trim());
            corners2 = Integer.parseInt(corners_team1_edt_txt.getEditableText().toString().trim());
            yellowcard2 = Integer.parseInt(yellowcards_team1_edt_txt.getEditableText().toString().trim());
            redcard2 = Integer.parseInt(redcard_team1_edt_txt.getEditableText().toString().trim());
            offsides2 = Integer.parseInt(offsides_team1_edt_txt.getEditableText().toString().trim());
            passaccuracy2 = Integer.parseInt(passaccuracy_team1_edt_txt.getEditableText().toString().trim());

            goal2 = Integer.parseInt(team2_goal_edt_txt.getEditableText().toString().trim());
            shots2 = Integer.parseInt(team1_goal_edt_txt.getEditableText().toString().trim());
            shotsontarget2 = Integer.parseInt(team1_goal_edt_txt.getEditableText().toString().trim());
            possession = Integer.parseInt(possession_team2_edt_txt.getEditableText().toString().trim());
            fouls = Integer.parseInt(fouls_team2_edt_txt.getEditableText().toString().trim());
            corners = Integer.parseInt(corners_team2_edt_txt.getEditableText().toString().trim());
            yellowcard = Integer.parseInt(yellowcards_team2_edt_txt.getEditableText().toString().trim());
            redcard = Integer.parseInt(redcard_team2_edt_txt.getEditableText().toString().trim());
            offsides = Integer.parseInt(offsides_team2_edt_txt.getEditableText().toString().trim());
            passaccuracy = Integer.parseInt(passaccuracy_team2_edt_txt.getEditableText().toString().trim());
            passes = Integer.parseInt(passes_team2_edt_txt.getEditableText().toString().trim());

            service = RetroFitInstance.getRetrofitInstance().create(Getdataservice.class);

            Log.d("step1", "after getService part in update score");

            Call<ResponseBody> updatescore = service.updateMatchScore(matchid,teamid,goalteam1,shots,shotsontarget,possession,passes,passaccuracy,fouls,yellowcard,redcard,offsides,corners,team2id,goal2,shots2,shotsontarget2,possession2,passes2,passaccuracy2,fouls2,yellowcard2,redcard2,offsides2,redcard2);

            System.out.println(updatescore);

            updatescore.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if(!response.isSuccessful()){
                        int s = response.code();
                        System.out.println("code"+s);
                        Toast.makeText(context,"succesfully not update...."+s,Toast.LENGTH_LONG).show();


                    }
                    int s = response.code();
                    System.out.println("code"+s);
                    String str = response.body().toString();
                    System.out.println("codestring"+str);
                    Toast.makeText(context,"succesfully updated...."+s,Toast.LENGTH_LONG).show();
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {


                    System.out.println("error"+t.getMessage());
                    Toast.makeText(context," noot working",Toast.LENGTH_LONG).show();

                }
            });



        }
    }
}
