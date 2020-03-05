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
import com.example.soccerallianceapp.pojo.ViewTeamListByLeague.ViewTeamListByLeague;
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
    private ArrayList<matches_data_list> up_matches_data_lists, played_matches_data_lists;
    private Matches_adapter up_match_adapter, played_match_adapter;
    int league_id;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_matches, container, false);
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

            if(getArguments().getString("Coming_from").equals("Leagues_Fragment_Class")){
                league_id = getArguments().getInt("league_id");
                Toast.makeText(context,league_id, Toast.LENGTH_LONG).show();
                Getdataservice service = RetroFitInstance.getRetrofitInstance().create(Getdataservice.class);

                Call matchesByLeague = service.getUpcomingMatchesByLeague(league_id);

                matchesByLeague.enqueue(new Callback<UpcomingMatchByLeague>() {
                    @Override
                    public void onResponse(Call<UpcomingMatchByLeague> call, Response<UpcomingMatchByLeague> response) {

                        UpcomingMatchByLeague realData = response.body();
                        System.out.println("response pojo"+realData);
                        if(response.body() != null){
                            if (realData.getStatus() == 200) {
                                for (UpcomingMatchList matchList : realData.getUpcomingMatchList()) {
                                   /* up_matches_data_lists.add(new matches_data_list(
                                            matchList.getTeam1(),
                                            matchList.getTeam1Logo(),
                                            matchList.getTeam2(),
                                            matchList.getTeam2Logo(),
                                            matchList.getDateOfMatch()));*/
                                }
                                up_match_adapter.notifyDataSetChanged();
                                //  progressBar.setVisibility(View.VISIBLE);
                               /* comman_adapter.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {

                                        RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) view.getTag();
                                        int position = viewHolder.getAdapterPosition();

                                        Bundle bundle = new Bundle();
                                        bundle.putInt("team_id",comman_data_List.get(position).getIteam_id());
                                        bundle.putString("Coming_from" ,"TeamList_Fragment_Class");

                                        DashboardNavController.navigate(R.id.player_List_Fragment,bundle);
                                    }
                                });*/
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