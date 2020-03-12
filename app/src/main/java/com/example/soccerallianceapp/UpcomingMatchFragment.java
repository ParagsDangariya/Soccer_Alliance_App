package com.example.soccerallianceapp;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
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

import com.bumptech.glide.Glide;
import com.example.soccer_alliance_project_test.R;
import com.example.soccerallianceapp.pojo.ViewPlayerListByTeamDashboard.PlayerList;
import com.example.soccerallianceapp.pojo.ViewPlayerListByTeamDashboard.ViewPlayerListDashboard;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/*
 * Upcoming matches will be which are coming in future and display details of match fixed by whom and who are inclueded.
 *
 * */
public class UpcomingMatchFragment extends Fragment {

    TextView umf_team1,umf_team2,umf_date,umf_time,team1_player_list, team2_player_list, team1_manager_name,team2_manager_name,umf_league_name;
    ImageView umf_team1_logo,umf_team2_logo,umf_league_icon;
    String Team1_Players="",Team2_Players="";
    Context context;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_upcoming_match, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        context = getActivity().getApplicationContext();

        umf_team1 = view.findViewById(R.id.umf_team1);
        umf_team2 = view.findViewById(R.id.umf_team2);
        umf_date = view.findViewById(R.id.umf_date);
        umf_time = view.findViewById(R.id.umf_time);
        team1_player_list = view.findViewById(R.id.team1_player_list);
        team2_player_list = view.findViewById(R.id.team2_player_list);
        team1_manager_name = view.findViewById(R.id.team1_manager_name);
        team2_manager_name = view.findViewById(R.id.team2_manager_name);
        umf_team1_logo = view.findViewById(R.id.umf_team1_logo);
        umf_team2_logo = view.findViewById(R.id.umf_team2_logo);


        if(getArguments()!=null){
            umf_team1.setText(getArguments().getString("team1name"));
            umf_team2.setText(getArguments().getString("team2name"));
            Glide.with(context).load(getArguments().getString("team1logo")).fitCenter().into(umf_team1_logo);
            Glide.with(context).load(getArguments().getString("team2logo")).fitCenter().into(umf_team2_logo);
            umf_date.setText(getArguments().getString("match_date"));
            umf_time.setText(getArguments().getString("match_time"));
        }

        Getdataservice service = RetroFitInstance.getRetrofitInstance().create(Getdataservice.class);

        Call<ViewPlayerListDashboard> viewPlayerListDashboardCall = service.getviewPlayerListFromTeamDashboardCall(1);
        System.out.println("call " + viewPlayerListDashboardCall);
        viewPlayerListDashboardCall.enqueue(new Callback<ViewPlayerListDashboard>() {
            @Override
            public void onResponse(Call<ViewPlayerListDashboard> call, Response<ViewPlayerListDashboard> response) {
                Log.d("step2", "after onResponse");
                ViewPlayerListDashboard realData = response.body();
                System.out.println("response" + realData);

                if (response.body() != null) {
                    if (realData.getStatus() == 200) {
                        for (PlayerList playerList : realData.getPlayerList()) {
                            Team1_Players = Team1_Players + playerList.getFullName()+"\n";
                        }
                        team1_player_list.setText(Team1_Players.trim());
                    }
                }
                else {
                    Toast.makeText(getActivity(), "Response empty", Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onFailure(Call<ViewPlayerListDashboard> call, Throwable t) {
                System.out.println("Error : " + t.getMessage());
            }
        });

        Call<ViewPlayerListDashboard> viewPlayerListDashboardCall2 = service.getviewPlayerListFromTeamDashboardCall(2);
        System.out.println("call " + viewPlayerListDashboardCall2);
        viewPlayerListDashboardCall2.enqueue(new Callback<ViewPlayerListDashboard>() {
            @Override
            public void onResponse(Call<ViewPlayerListDashboard> call, Response<ViewPlayerListDashboard> response) {
                Log.d("step2", "after onResponse");
                ViewPlayerListDashboard realData = response.body();
                System.out.println("response" + realData);

                if (response.body() != null) {
                    if (realData.getStatus() == 200) {
                        for (PlayerList playerList : realData.getPlayerList()) {
                            Team2_Players = Team2_Players + playerList.getFullName()+"\n";
                        }
                        team2_player_list.setText(Team2_Players.trim());
                    }

                } else {
                    Toast.makeText(getActivity(), "Response empty", Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onFailure(Call<ViewPlayerListDashboard> call, Throwable t) {
                System.out.println("Error : " + t.getMessage());
            }
        });
    }

}
