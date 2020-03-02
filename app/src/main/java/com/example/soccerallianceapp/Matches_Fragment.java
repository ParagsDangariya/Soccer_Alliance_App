package com.example.soccerallianceapp;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.soccer_alliance_project_test.R;

import com.example.soccerallianceapp.pojo.PlayedMatchListDashboard.PlayedMatchList;
import com.example.soccerallianceapp.pojo.PlayedMatchListDashboard.PlayedmatchListDashboard;
import com.example.soccerallianceapp.pojo.matchListDashboard.MatchListDashboard;
import com.example.soccerallianceapp.pojo.matchListDashboard.UpcomingMatchList;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Matches_Fragment extends Fragment implements View.OnClickListener {

    public NavController DashboardNavController;
    private Context context;
    FloatingActionButton add_player_btn;
    RecyclerView um_recycler_View,pm_recycler_View;
    private ArrayList<matches_data_list> up_matches_data_lists,played_matches_data_lists;
    private Matches_adapter up_match_adapter,played_match_adapter;


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

        pm_recycler_View = view.findViewById(R.id.pm_recycler_View);
        played_matches_data_lists = new ArrayList<matches_data_list>();
        played_matches_data_lists.clear();
        played_match_adapter = new Matches_adapter(played_matches_data_lists, context);
        pm_recycler_View.setLayoutManager(new LinearLayoutManager(context));
        pm_recycler_View.setAdapter(played_match_adapter);

        um_recycler_View = view.findViewById(R.id.um_recycler_View);
        up_matches_data_lists = new ArrayList<matches_data_list>();
        up_matches_data_lists.clear();
        up_match_adapter = new Matches_adapter(up_matches_data_lists, context);
        um_recycler_View.setLayoutManager(new LinearLayoutManager(context));
        um_recycler_View.setAdapter(up_match_adapter);

        Getdataservice service = RetroFitInstance.getRetrofitInstance().create(Getdataservice.class);
        Call<MatchListDashboard> matchlistcall =service.getupcomingMatches_guestDashboardCall();

        matchlistcall.enqueue(new Callback<MatchListDashboard>() {

            @Override
            public void onResponse(Call<MatchListDashboard> call, Response<MatchListDashboard> response) {

                MatchListDashboard realData = response.body();
                System.out.println("response from match fragment" + response.body());
                if (response.body() != null) {

                    if (realData.getStatus() == 200) {
                        System.out.println("getting upcoming matches "+realData.getUpcomingMatchList());
                        for (UpcomingMatchList matchelist : realData.getUpcomingMatchList()) {
                            //set logo when imge gets done. here
                            up_matches_data_lists.add(new matches_data_list(
                                    1,
                                    matchelist.getTeam1(),
                                    matchelist.getTeam1Logo(),
                                    matchelist.getTeam2(),
                                    matchelist.getTeam2Logo(),
                                    matchelist.getDateOfMatch()));

                        }
                    }
                    up_match_adapter.notifyDataSetChanged();
                    up_match_adapter.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            DashboardNavController.navigate(R.id.upcomingMatchFragment);
                        }
                    });
                    //DashboardNavController.navigate(R.id.upcomingMatchFragment);
                } else {
                    Toast.makeText(getActivity(), "Response empty", Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<MatchListDashboard> call, Throwable t) {

                System.out.println("Error : " + t.getMessage());
            }
        });

       Call<PlayedmatchListDashboard> playmatchList = service.getplayedMatches_guestDashboardCall();
        playmatchList.enqueue(new Callback<PlayedmatchListDashboard>() {
            @Override
            public void onResponse(Call<PlayedmatchListDashboard> call, Response<PlayedmatchListDashboard> response) {


                PlayedmatchListDashboard realData = response.body();
                System.out.println("response from match fragment" + response.body());
                if (response.body() != null) {

                    if (realData.getStatus() == 200) {
                        System.out.println("getting upcoming matches "+realData.getPlayedMatchList());

                        for (PlayedMatchList matchelist : realData.getPlayedMatchList()) {
                            //set logo when imge gets done. here
                            played_matches_data_lists.add(new matches_data_list(
                                    1,
                                    matchelist.getTeam1(),
                                    matchelist.getTeam1Logo(),
                                    matchelist.getTeam2(),
                                    matchelist.getTeam2Logo(),
                                    matchelist.getDateOfMatch()));
                        }
                    }
                    played_match_adapter.notifyDataSetChanged();
                    played_match_adapter.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            DashboardNavController.navigate(R.id.match_Score_Fragment);
                        }
                    });
                    //DashboardNavController.navigate(R.id.match_Score_Fragment);
                } else {
                    Toast.makeText(getActivity(), "Response empty", Toast.LENGTH_LONG).show();
                }


            }

            @Override
            public void onFailure(Call<PlayedmatchListDashboard> call, Throwable t) {

            }
        });



    }


    @Override
    public void onClick(View view) {

    }
}