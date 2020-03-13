package com.example.soccerallianceapp;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.soccer_alliance_project_test.R;
import com.example.soccerallianceapp.pojo.PlayedMatchByLeague.PlayedMatchByLeague;
import com.example.soccerallianceapp.pojo.PlayedMatchByLeague.PlayedMatchList;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LeaguePlayedMatchFragment extends Fragment implements View.OnClickListener{

    public NavController DashboardNavController;
    // private ProgressBar progressbar;
    private Context context;
    RecyclerView um_recycler_View, pm_recycler_View;
    private ArrayList<matches_data_list> played_matches_data_lists;
    private Matches_adapter played_match_adapter;
    Getdataservice service;
    int league_id;
    //int league_id;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_league_played_match, container, false);


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        try {
            DashboardNavController = Navigation.findNavController(getActivity(), R.id.dashboard_host_fragment);
            context = getActivity().getApplicationContext();

            pm_recycler_View = view.findViewById(R.id.pm_recycler_View);
            played_matches_data_lists = new ArrayList<matches_data_list>();
            played_matches_data_lists.clear();
            played_match_adapter = new Matches_adapter(played_matches_data_lists, context);
            pm_recycler_View.setLayoutManager(new LinearLayoutManager(context));
            pm_recycler_View.setAdapter(played_match_adapter);


            if (getArguments() != null) {
                if (getArguments().getString("Coming_from").equals("PlayedMatchInLeague")) {

                    league_id = getArguments().getInt("League_id");
                    System.out.println("league id from league upcoming match " + league_id);

                    Toast.makeText(context, "Played match : " + league_id, Toast.LENGTH_LONG).show();

                    service = RetroFitInstance.getRetrofitInstance().create(Getdataservice.class);

                    Call matchesByLeague = service.getPlayedMatchesByLeague(league_id);

                    matchesByLeague.enqueue(new Callback<PlayedMatchByLeague>() {
                        @Override
                        public void onResponse(Call<PlayedMatchByLeague> call, Response<PlayedMatchByLeague> response) {
                            System.out.println("response from league opration fragment get into response");

                            PlayedMatchByLeague realData = response.body();

                            System.out.println("Response : " + response.body().toString());

                            System.out.println("response from league opration fragment " + response.body());
                            if (response.body() != null) {

                                if (realData.getStatus() == 200) {
                                    for (PlayedMatchList matchelist : realData.getPlayedMatchList()) {
                                        System.out.println("getting match list from league  " + realData.getPlayedMatchList());
                                        played_matches_data_lists.add(new matches_data_list(
                                                matchelist.getMatchId(),
                                                matchelist.getTeam1Id(),
                                                matchelist.getTeam2Id(),
                                                matchelist.getScheduleId(),
                                                matchelist.getTeam1(),
                                                matchelist.getTeam1Logo(),
                                                matchelist.getTeam2(),
                                                matchelist.getTeam2Logo(),
                                                matchelist.getDateOfMatch()
                                        ));

                                    }
                                    played_match_adapter.notifyDataSetChanged();
                                    played_match_adapter.setOnClickListener(new View.OnClickListener() {
                                        @Override

                                        public void onClick(View view) {

                                            RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) view.getTag();
                                            int position = viewHolder.getAdapterPosition();
                                            Bundle bundle = new Bundle();
                                            bundle.putInt("played_match_id", played_matches_data_lists.get(position).getMatch_id());
                                            bundle.putInt("played_team1_id", played_matches_data_lists.get(position).getTeam1Id());
                                            bundle.putInt("played_team2_id", played_matches_data_lists.get(position).getTeam2Id());
                                            bundle.putString("played_team1name", played_matches_data_lists.get(position).getTeam1_name());
                                            bundle.putString("played_team2name", played_matches_data_lists.get(position).getTeam2_name());
                                            bundle.putString("played_team1logo", played_matches_data_lists.get(position).getTeam1_logo());
                                            bundle.putString("played_team2logo", played_matches_data_lists.get(position).getTeam2_logo());
                                            bundle.putString("played_match_date", played_matches_data_lists.get(position).getMatch_date());

                                            DashboardNavController.navigate(R.id.matchScoreUpdateFragment, bundle);
                                        }
                                    });

                                }

                            } else {

                                Toast.makeText(getActivity(), "Response empty", Toast.LENGTH_LONG).show();
                                // progressBar.setVisibility(View.VISIBLE);
                            }
                        }

                        @Override
                        public void onFailure(Call call, Throwable t) {
                            System.out.println("Error : " + t.getMessage());

                        }
                    });
                }
            }
        } catch (Exception e) {

            System.out.println("Played match catch :" + e);
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {

    }
}
