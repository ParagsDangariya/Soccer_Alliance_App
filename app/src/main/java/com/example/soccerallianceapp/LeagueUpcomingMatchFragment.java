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
import com.example.soccerallianceapp.pojo.UpcomingMatchByLeague.UpcomingMatchByLeague;
import com.example.soccerallianceapp.pojo.UpcomingMatchByLeague.UpcomingMatchList;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LeagueUpcomingMatchFragment extends Fragment {

    public NavController DashboardNavController;
    // private ProgressBar progressbar;
    private Context context;
    FloatingActionButton add_player_btn;
    RecyclerView um_recycler_View, pm_recycler_View;
    private ArrayList<matches_data_list> up_matches_data_lists;
    private Matches_adapter up_match_adapter;
    int league_id;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_league_upcoming_match, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        DashboardNavController = Navigation.findNavController(getActivity(),R.id.dashboard_host_fragment);
        context = getActivity().getApplicationContext();

        um_recycler_View = view.findViewById(R.id.um_recycler_View);
        up_matches_data_lists = new ArrayList<matches_data_list>();
        up_matches_data_lists.clear();
        up_match_adapter = new Matches_adapter(up_matches_data_lists, context);
        um_recycler_View.setLayoutManager(new LinearLayoutManager(context));
        um_recycler_View.setAdapter(up_match_adapter);


        if(getArguments()!=null){

            if(getArguments().getString("ComingFrom").equals("LeagueUpcomingMatchFragment")){
                league_id = getArguments().getInt("League_id");
                System.out.println("league id from league upcoming match "+league_id);
               // Toast.makeText(context,league_id, Toast.LENGTH_LONG).show();
                Getdataservice service = RetroFitInstance.getRetrofitInstance().create(Getdataservice.class);

                Call matchesByLeague = service.getUpcomingMatchesByLeague(league_id);

                matchesByLeague.enqueue(new Callback<UpcomingMatchByLeague>() {
                    @Override
                    public void onResponse(Call<UpcomingMatchByLeague> call, Response<UpcomingMatchByLeague> response) {
                        System.out.println("response from league opration fragment get into response");
                        UpcomingMatchByLeague realData = response.body();
                        System.out.println("response from league opration fragment "+response.body());
                        if(response.body() != null){
                            if (realData.getStatus() == 200) {
                                for (UpcomingMatchList matchelist : realData.getUpcomingMatchList()) {
                                    System.out.println("getting match list from league in upcoming "+realData.getUpcomingMatchList());
                                    up_matches_data_lists.add(new matches_data_list(
                                            1,
                                            matchelist.getTeam1(),
                                            matchelist.getTeam1Logo(),
                                            matchelist.getTeam2(),
                                            matchelist.getTeam2Logo(),
                                            matchelist.getDateOfMatch()));

                                }
                                up_match_adapter.notifyDataSetChanged();
                                up_match_adapter.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {

                                        RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) view.getTag();
                                        int position = viewHolder.getAdapterPosition();
                                        Bundle upcoming_match_bundle = new Bundle();
                                        upcoming_match_bundle.putInt("up_match_id",up_matches_data_lists.get(position).getMatch_id());
                                        upcoming_match_bundle.putString("up_team1_name",up_matches_data_lists.get(position).getTeam1_name());
                                        upcoming_match_bundle.putString("up_team2_name",up_matches_data_lists.get(position).getTeam2_name());
                                        upcoming_match_bundle.putString("up_team1_logo",up_matches_data_lists.get(position).getTeam1_logo());
                                        upcoming_match_bundle.putString("up_team2_logo",up_matches_data_lists.get(position).getTeam2_logo());
                                        upcoming_match_bundle.putString("up_date",up_matches_data_lists.get(position).getMatch_date());
                                        upcoming_match_bundle.putString("up_time",up_matches_data_lists.get(position).getMatch_time());
                                        DashboardNavController.navigate(R.id.matchScoreRescheduleFragment,upcoming_match_bundle);
                                    }
                                });

                            }

                        }else{

                            Toast.makeText(getActivity() ,"Response empty",Toast.LENGTH_LONG).show();
                            // progressBar.setVisibility(View.VISIBLE);
                        }
                    }

                    @Override
                    public void onFailure(Call call, Throwable t) {
                        System.out.println("Error : "+t.getMessage());

                    }
                });

            }



        }

    }
}