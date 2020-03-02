package com.example.soccerallianceapp;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.example.soccer_alliance_project_test.R;

import com.example.soccerallianceapp.pojo.CreateSchedule.ScheduleMatch;
import com.example.soccerallianceapp.pojo.viewTeamListFromLeagueId.TeamList;
import com.example.soccerallianceapp.pojo.viewTeamListFromLeagueId.ViewTeamListFromLeagueId;
import com.example.soccerallianceapp.pojo.viewregisteruserdetail.User;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.R.layout.simple_list_item_1;

public class ScheduleMatchFragment extends Fragment implements View.OnClickListener {

    public NavController navController;
    private Context context;
    private ArrayList<Comman_Data_List> comman_data_List;
    private Comman_adapter comman_adapter;

    FirebaseAuth fAuth;

    Getdataservice service;

    Integer League_id = 1;

    MaterialButton Schedule_match_btn;

    String uid = "";


    AutoCompleteTextView schedule_match_edt_txt;


    TextInputEditText schedule_match_date_edt_txt, schedule_match_time_layout_edt_txt, schedule_match_location_layout_edt_txt, schedule_match_team2_edt_txt;


    AutoCompleteTextView team1;
    String team2;
    String date;
    String time;
    String location;

    int team1id, team2id;

    int league_id;

    public ScheduleMatchFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

/*
        if (getArguments() != null) {
            league_id = getArguments().getInt("league_id");

        }

 */
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_schedule_match, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        fAuth = FirebaseAuth.getInstance();
        context = getActivity().getApplicationContext();

        schedule_match_edt_txt = view.findViewById(R.id.schedule_match_edt_txt);
        schedule_match_team2_edt_txt = view.findViewById(R.id.schedule_match_team2_edt_txt);
        schedule_match_date_edt_txt = view.findViewById(R.id.schedule_match_date_edt_txt);
        schedule_match_time_layout_edt_txt = view.findViewById(R.id.schedule_match_time_layout_edt_txt);
        schedule_match_location_layout_edt_txt = view.findViewById(R.id.schedule_match_location_layout_edt_txt);

        Schedule_match_btn = view.findViewById(R.id.Schedule_match_btn);
        Schedule_match_btn.setOnClickListener(this);


        uid = fAuth.getCurrentUser().getUid();
        Toast.makeText(getActivity(), "UID : " + uid, Toast.LENGTH_LONG).show();
        System.out.println("User Id : " + uid);

        Getdataservice service = RetroFitInstance.getRetrofitInstance().create(Getdataservice.class);


        // Call<ScheduleMatch> scheduleMatchCall = service.scheduleMatch(scdulematch);


        Log.d("step1", "after getService part");

        //League_id = getArguments().getInt("League_id");

//        Toast.makeText(context, "Schedule MAtch Fragment (League Id ): " + League_id, Toast.LENGTH_LONG).show();

        Call<ViewTeamListFromLeagueId> call = service.viewTeamListFromLeagueId(League_id);

        System.out.println(call);

        call.enqueue(new Callback<ViewTeamListFromLeagueId>() {
            @Override
            public void onResponse(Call<ViewTeamListFromLeagueId> call, Response<ViewTeamListFromLeagueId> response) {

                Log.d("Response : ", " " + response);


                if (response.body() != null) {
                    List<TeamList> teamLists = response.body().getTeamList();

                    String[] listofteam = new String[teamLists.size()];


                    for (int i = 0; i < teamLists.size(); i++) {
                        listofteam[i] = teamLists.get(i).getTeamName();
                        System.out.print("teamlists." + listofteam[i]);
                    }

                    ArrayAdapter<String> teamadapter = new ArrayAdapter<>(context,
                            simple_list_item_1, listofteam);


                    schedule_match_edt_txt.setAdapter(teamadapter);


                } else {
                    Toast.makeText(getActivity(), "REsponse empty", Toast.LENGTH_LONG).show();

                }

            }

            @Override
            public void onFailure(Call<ViewTeamListFromLeagueId> call, Throwable t) {

            }
        });


    }


    @Override
    public void onClick(View view) {

        if (view == Schedule_match_btn) {

            team1 = (AutoCompleteTextView) schedule_match_edt_txt.getText();
            team2 = schedule_match_team2_edt_txt.getEditableText().toString().trim();

            date = schedule_match_date_edt_txt.getEditableText().toString();

            if (TextUtils.isEmpty((CharSequence) team1)) {
                schedule_match_edt_txt.setError("Team1 Required.");
                return;
            }
        } else if (TextUtils.isEmpty(team2)) {
            schedule_match_team2_edt_txt.setError("Team2 is Required.");
            return;
        } else if (team1.equals(team2)) {
            schedule_match_team2_edt_txt.setError("Both Team name must be different.");
            return;
        }

        date = schedule_match_date_edt_txt.getEditableText().toString();
        location = schedule_match_location_layout_edt_txt.getEditableText().toString();
        time = schedule_match_time_layout_edt_txt.getEditableText().toString();

        league_id = getArguments().getInt("League_id");


        String url = "https://soccerallianceapp.appspot.com/rest/api/CreateSchedule&" + location + "&" + date + "&" + time + "&" + team1 + "&" + team2 + "&" + league_id + "";


        System.out.println("url" + url);

        ScheduleMatch schedulematch = new ScheduleMatch(location, date, time, team1id, team2id, league_id);

        Call<ScheduleMatch> call = service.scheduleMatch(schedulematch);

        call.enqueue(new Callback<ScheduleMatch>() {
            @Override
            public void onResponse(Call<ScheduleMatch> call, Response<ScheduleMatch> response) {

                if(!response.isSuccessful()){
                    int sm = response.code();
                    System.out.println("code"+sm);
                    Toast.makeText(context,"Schedule Match Successfully "+sm,Toast.LENGTH_LONG).show();


                }

                int sm = response.code();
                System.out.println("code"+sm);
                Toast.makeText(context,"Schedule Match Successfully "+sm,Toast.LENGTH_LONG).show();


                navController.navigate(R.id.leagueOperationsFragment);
            }

            @Override
            public void onFailure(Call<ScheduleMatch> call, Throwable t) {


                System.out.println("error"+t.getMessage());
                Toast.makeText(context,"Wrong thing happened",Toast.LENGTH_LONG).show();

            }
        });


    }


}

