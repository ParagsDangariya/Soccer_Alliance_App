package com.example.soccerallianceapp;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.soccer_alliance_project_test.R;
import com.example.soccerallianceapp.pojo.matchScore.MatchScoreDisplay;
import com.example.soccerallianceapp.pojo.matchScore.MatchScores;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MatchScoreUpdateFragment extends Fragment implements View.OnClickListener {

    public NavController DashboardNavController;
    MatchScores matchscore;
    MatchScores matchScoresteam2;

    int matchid;
    int team1id;
    int team2id;

    int league_id, up_match_id, up_team1_id, up_team2_id;
    String up_team1name, up_team2name, up_team1icon, up_team2icon, up_match_date, up_match_time;


    private Context context;
//    private ArrayList<Comman_Data_List> comman_data_List;
//    private Comman_adapter comman_adapter;
    Getdataservice service;

    TextInputEditText team1_goal_edt_txt, shots_team1_edt_txt, shotsontarget_team1_edt_txt, possession_team1_edt_txt, passes_team1_edt_txt, fouls_team1_edt_txt;
    TextInputEditText passaccuracy_team1_edt_txt, redcard_team1_edt_txt, offsides_team1_edt_txt, corners_team1_edt_txt, yellowcards_team1_edt_txt;
    TextInputEditText team2_goal_edt_txt, shots_team2_edt_txt, shotsontarget_team2_edt_txt, possession_team2_edt_txt, passes_team2_edt_txt, fouls_team2_edt_txt;
    TextInputEditText passaccuracy_team2_edt_txt, redcard_team2_edt_txt, offsides_team2_edt_txt, corners_team2_edt_txt, yellowcards_team2_edt_txt;
    MaterialButton Update_Score_btn;

    int goalteam1 = 0, shots = 0, shotsontarget = 0, possession = 0, passes = 0, passaccuracy = 0, redcard = 0, offsides = 0, corners = 0, fouls = 0, yellowcard = 0;
    int goal2 = 0, shots2 = 0, shotsontarget2 = 0, possession2 = 0, passes2 = 0, passaccuracy2 = 0, redcard2 = 0, offsides2 = 0, corners2 = 0, fouls2 = 0, yellowcard2 = 0;


    //Button Schedule_match_btn;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            if (getArguments().getString("ComingFrom").equals("PlayedMatch")) {

                league_id = getArguments().getInt("League_id");
                System.out.println("league id from league - Match score update : " + league_id);

                matchid = getArguments().getInt("played_match_id");
                team1id = getArguments().getInt("played_team1_id");
                team2id = getArguments().getInt("played_team2_id");

                System.out.println("Match id in Match score update :" + matchid);
                System.out.println("Team1 id in Match score update :" + team1id);
                System.out.println("Team 2 id in Match score update :" + team2id);


                up_team1name = getArguments().getString("up_team1name");
                up_team2name = getArguments().getString("up_team2name");
                up_team1icon = getArguments().getString("up_team1logo");
                up_team2icon = getArguments().getString("up_team2logo");
                up_match_date = getArguments().getString("up_match_date");
                up_match_time = getArguments().getString("up_match_time");


            }
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_match_score_update, container, false);


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        DashboardNavController = Navigation.findNavController(getActivity(), R.id.dashboard_host_fragment);
        context = getActivity().getApplicationContext();
//        comman_data_List = new ArrayList<Comman_Data_List>();
//        comman_adapter = new Comman_adapter(comman_data_List, context);

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

        getTeam1Score(matchid,team1id);
        getTeam2Score(matchid,team2id);



//        if (getArguments() != null) {
//            if (getArguments().getString("Coming_from").equals("updateScore")) {
//                try {
//                    service = RetroFitInstance.getRetrofitInstance().create(Getdataservice.class);
//                    Log.d("step1", "after getService part in match score display ");
//                    System.out.println("matchid" + matchid);
//                    System.out.println("Match id in Match :" + matchid);
//                    System.out.println("Team1 id in Match :" + team1id);
//                    System.out.println("Team 2 id in Match :" + team2id);
//                    Call<MatchScoreDisplay> displayscore = service.getMatchScores(matchid, team1id);
//
//                    Log.d("step2", "after displayscore part in match score display ");
//                    System.out.println(displayscore.toString());
//                    displayscore.enqueue(new Callback<MatchScoreDisplay>() {
//                        @Override
//                        public void onResponse(Call<MatchScoreDisplay> call, Response<MatchScoreDisplay> response) {
//
//                            Log.d("step3", "after onResponse");
//
//
//                            MatchScoreDisplay data = response.body();
//
//                            System.out.println(data);
//                            System.out.println("response" + response.body().toString());
//
//                            matchscore = data.getMatchScores();
//
//                            goalteam1 = matchscore.getGoal();
//                            shots = matchscore.getShots();
//                            shotsontarget = matchscore.getShotsOnTarget();
//                            passaccuracy = matchscore.getPassAccuracy();
//                            passes = matchscore.getPasses();
//                            redcard = matchscore.getRedCards();
//                            possession = matchscore.getPossession();
//                            corners = matchscore.getCorners();
//                            offsides = matchscore.getOffsides();
//                            fouls = matchscore.getFouls();
//                            yellowcard = matchscore.getYellowCards();
//
//                            team1_goal_edt_txt.setText(String.valueOf(goalteam1));
//                            shots_team1_edt_txt.setText(String.valueOf(shots));
//                            shotsontarget_team1_edt_txt.setText(String.valueOf(shotsontarget));
//                            possession_team1_edt_txt.setText(String.valueOf(possession));
//                            passes_team1_edt_txt.setText(String.valueOf(passes));
//                            passaccuracy_team1_edt_txt.setText(String.valueOf(passaccuracy));
//                            redcard_team1_edt_txt.setText(String.valueOf(redcard));
//                            corners_team1_edt_txt.setText(String.valueOf(corners));
//                            offsides_team1_edt_txt.setText(String.valueOf(offsides));
//                            yellowcards_team1_edt_txt.setText(String.valueOf(yellowcard));
//                            fouls_team1_edt_txt.setText(String.valueOf(fouls));
//
//
//                            System.out.println(team1_goal_edt_txt);
//
//
//                        }
//
//                        @Override
//                        public void onFailure(Call<MatchScoreDisplay> call, Throwable t) {
//
//
//                            System.out.println("Error : " + t.getMessage());
//
//                        }
//                    });
//
//
//                    Call<MatchScoreDisplay> displayscore2 = service.getMatchScores(matchid, team2id);
//
//                    displayscore2.enqueue(new Callback<MatchScoreDisplay>() {
//                        @Override
//                        public void onResponse(Call<MatchScoreDisplay> call, Response<MatchScoreDisplay> response) {
//
//                            Log.d("step2", "after 2nd onResponse");
//
//
//                            MatchScoreDisplay data1 = response.body();
//
//                            System.out.println(data1);
//
//                            System.out.println("response" + response.body().toString());
//
//                            matchscore = data1.getMatchScores();
//
//                            goal2 = matchscore.getGoal();
//                            shots2 = matchscore.getShots();
//                            shotsontarget2 = matchscore.getShotsOnTarget();
//                            passaccuracy2 = matchscore.getPassAccuracy();
//                            passes2 = matchscore.getPasses();
//                            redcard2 = matchscore.getRedCards();
//                            possession2 = matchscore.getPossession();
//                            corners2 = matchscore.getCorners();
//                            offsides2 = matchscore.getOffsides();
//                            fouls2 = matchscore.getFouls();
//                            yellowcard2 = matchscore.getYellowCards();
//
//                            team2_goal_edt_txt.setText(String.valueOf(goal2));
//                            shots_team2_edt_txt.setText(String.valueOf(shots2));
//                            shotsontarget_team2_edt_txt.setText(String.valueOf(shotsontarget2));
//                            possession_team2_edt_txt.setText(String.valueOf(possession2));
//                            passes_team2_edt_txt.setText(String.valueOf(passes2));
//                            passaccuracy_team2_edt_txt.setText(String.valueOf(passaccuracy2));
//                            redcard_team2_edt_txt.setText(String.valueOf(redcard2));
//                            corners_team2_edt_txt.setText(String.valueOf(corners2));
//
//                            offsides_team2_edt_txt.setText(String.valueOf(offsides2));
//                            yellowcards_team2_edt_txt.setText(String.valueOf(yellowcard2));
//                            fouls_team2_edt_txt.setText(String.valueOf(fouls2));
//
//                            System.out.println(team2_goal_edt_txt);
//
//
//                        }
//
//                        @Override
//                        public void onFailure(Call<MatchScoreDisplay> call, Throwable t) {
//
//                        }
//                    });
//
//
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        }


    }

    @Override
    public void onClick(View v) {
        if (v == Update_Score_btn) {


            updateScore(matchid,team1id,team2id);


        }
    }


    private void getTeam1Score(int match_id, int team_id) {

                    service = RetroFitInstance.getRetrofitInstance().create(Getdataservice.class);
                    Log.d("step1", "after getService part in match score display ");
                    System.out.println("matchid in Update match" + matchid);

                    System.out.println("Match id in in Update match :" + matchid);
                    System.out.println("Team1 id in in Update match :" + team1id);
                    System.out.println("Team 2 id in in Update match:" + team2id);

                    Call<MatchScoreDisplay> displayscore = service.getMatchScores(matchid, team1id);

                    Log.d("step2", "after displayscore part in match score display ");
                    System.out.println(displayscore.toString());
                    displayscore.enqueue(new Callback<MatchScoreDisplay>() {
                        @Override
                        public void onResponse(Call<MatchScoreDisplay> call, Response<MatchScoreDisplay> response) {
                            Log.d("step3", "after onResponse");
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
                            yellowcard = matchscore.getYellowCards();

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



    }
    private void getTeam2Score(int match_id, int team_id) {



                    service = RetroFitInstance.getRetrofitInstance().create(Getdataservice.class);
                    Log.d("step1", "after getService part in match score display ");

                    System.out.println("matchid" + matchid);
                    System.out.println("Match id in Match :" + matchid);
                    System.out.println("Team1 id in Match :" + team1id);
                    System.out.println("Team 2 id in Match :" + team2id);

                    Call<MatchScoreDisplay> displayscore2 = service.getMatchScores(matchid, team2id);

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
                            yellowcard2 = matchscore.getYellowCards();

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

    }


    private void updateScore(int matchid,int team1id,int team2id){

       if(team1_goal_edt_txt.getEditableText().toString().trim()!=null &&
               team2_goal_edt_txt.getEditableText().toString().trim()!=null) {
           goalteam1 = Integer.parseInt(team1_goal_edt_txt.getEditableText().toString().trim());
           goal2 = Integer.parseInt(team2_goal_edt_txt.getEditableText().toString().trim());
       }
       if(shots_team1_edt_txt.getEditableText().toString().trim() != null &&
               shots_team2_edt_txt.getEditableText().toString().trim() != null){
           shots = Integer.parseInt(shots_team1_edt_txt.getEditableText().toString().trim());
           shots2 = Integer.parseInt(shots_team2_edt_txt.getEditableText().toString().trim());
       }
        if(shotsontarget_team1_edt_txt.getEditableText().toString().trim()!=null &&
                shotsontarget_team2_edt_txt.getEditableText().toString().trim()!=null){
            shotsontarget = Integer.parseInt(shotsontarget_team1_edt_txt.getEditableText().toString().trim());
            shotsontarget2 = Integer.parseInt(shotsontarget_team2_edt_txt.getEditableText().toString().trim());

        }
        if(possession_team1_edt_txt.getEditableText().toString().trim()!=null &&
                possession_team2_edt_txt.getEditableText().toString().trim()!=null){
            possession = Integer.parseInt(possession_team1_edt_txt.getEditableText().toString().trim());
            possession2 = Integer.parseInt(possession_team2_edt_txt.getEditableText().toString().trim());
        }

        if(fouls_team1_edt_txt.getEditableText().toString().trim()!=null &&
                fouls_team2_edt_txt.getEditableText().toString().trim()!=null){
            fouls = Integer.parseInt(fouls_team1_edt_txt.getEditableText().toString().trim());
            fouls2 = Integer.parseInt(fouls_team2_edt_txt.getEditableText().toString().trim());

        }

        if(corners_team1_edt_txt.getEditableText().toString().trim()!=null &&
                corners_team2_edt_txt.getEditableText().toString().trim()!=null){
            corners = Integer.parseInt(corners_team1_edt_txt.getEditableText().toString().trim());
            corners2 = Integer.parseInt(corners_team2_edt_txt.getEditableText().toString().trim());

        }

        if(yellowcards_team1_edt_txt.getEditableText().toString().trim()!=null &&
                yellowcards_team2_edt_txt.getEditableText().toString().trim()!=null){
            yellowcard = Integer.parseInt(yellowcards_team1_edt_txt.getEditableText().toString().trim());
            yellowcard2 = Integer.parseInt(yellowcards_team2_edt_txt.getEditableText().toString().trim());

        }

        if(redcard_team1_edt_txt.getEditableText().toString().trim()!=null &&
                redcard_team2_edt_txt.getEditableText().toString().trim()!=null){
            redcard = Integer.parseInt(redcard_team1_edt_txt.getEditableText().toString().trim());
            redcard2 = Integer.parseInt(redcard_team2_edt_txt.getEditableText().toString().trim());

        }

        if(offsides_team1_edt_txt.getEditableText().toString().trim()!=null &&
                offsides_team2_edt_txt.getEditableText().toString().trim()!=null){
            offsides = Integer.parseInt(offsides_team1_edt_txt.getEditableText().toString().trim());
            offsides2 = Integer.parseInt(offsides_team2_edt_txt.getEditableText().toString().trim());

        }

        if(passaccuracy_team1_edt_txt.getEditableText().toString().trim()!=null &&
                passaccuracy_team2_edt_txt.getEditableText().toString().trim()!=null){
            passaccuracy = Integer.parseInt(passaccuracy_team1_edt_txt.getEditableText().toString().trim());
            passaccuracy2 = Integer.parseInt(passaccuracy_team2_edt_txt.getEditableText().toString().trim());

        }
        if(passes_team1_edt_txt.getEditableText().toString().trim()!=null &&
                passes_team2_edt_txt.getEditableText().toString().trim()!=null){

            passes = Integer.parseInt(passes_team1_edt_txt.getEditableText().toString().trim());
            passes2 = Integer.parseInt(passes_team2_edt_txt.getEditableText().toString().trim());
        }

        service = RetroFitInstance.getRetrofitInstance().create(Getdataservice.class);

        Log.d("step1", "after getService part in update score");
        Call<ResponseBody> updatescore = service.updateMatchScore(matchid, team1id, goalteam1, shots, shotsontarget, possession, passes, passaccuracy, fouls, yellowcard, redcard, offsides, corners, team2id, goal2, shots2, shotsontarget2, possession2, passes2, passaccuracy2, fouls2, yellowcard2, redcard2, offsides2, redcard2);

        System.out.println(updatescore);

        updatescore.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (!response.isSuccessful()) {
                    int s = response.code();
                    System.out.println("code" + s);
                    Toast.makeText(context, "succesfully not update...." + s, Toast.LENGTH_LONG).show();
                }
                int s = response.code();
                System.out.println("code" + s);
                String str = response.body().toString();
                System.out.println("codestring" + str);
                Toast.makeText(context, "succesfully updated...." + s, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                System.out.println("error" + t.getMessage());
                Toast.makeText(context, " not working", Toast.LENGTH_LONG).show();
            }
        });



    }

}
