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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.soccer_alliance_project_test.R;
import com.example.soccerallianceapp.pojo.ListOfLeaguesByCountry.Leagues;
import com.example.soccerallianceapp.pojo.ListOfLeaguesByCountry.ListOfLeaguesByCountry;
import com.example.soccerallianceapp.pojo.ViewTeamListByLeague.ViewTeamListByLeague;
import com.example.soccerallianceapp.pojo.ViewTeamListDashboard.TeamList;

import com.example.soccerallianceapp.pojo.ViewTeamListDashboard.ViewTeamList;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class TeamListFragment extends Fragment implements View.OnClickListener{

    public NavController DashboardNavController;

    private RecyclerView team_recycler_view;
    private Context context;
    private ArrayList<Comman_Data_List> comman_data_List;
    private Comman_adapter comman_adapter;
    FloatingActionButton add_team_btn;
    String league_id="";
    String country="";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_team_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        DashboardNavController = Navigation.findNavController(getActivity(), R.id.dashboard_host_fragment);
        context = getActivity().getApplicationContext();

        add_team_btn = view.findViewById(R.id.add_team_btn);
        add_team_btn.setOnClickListener(this);
        Getdataservice service = RetroFitInstance.getRetrofitInstance().create(Getdataservice.class);
        /*--------Teams Adapter Configuration--------*/
        team_recycler_view = view.findViewById(R.id.team_recycler_view);
        comman_data_List = new ArrayList<Comman_Data_List>();
        comman_data_List.clear();
        comman_adapter = new Comman_adapter(comman_data_List, context);
        team_recycler_view.setLayoutManager(new LinearLayoutManager(context));
        team_recycler_view.setAdapter(comman_adapter);

        if(getArguments()!=null){
            if(getArguments().getString("Coming_from").equals("Leagues_Fragment_Class")){
                league_id = getArguments().getString("league_id");
                Toast.makeText(context,league_id, Toast.LENGTH_LONG).show();

                Call viewTeamListByLeague = service.getviewTeamListFromLeagueIdCall(league_id);

                viewTeamListByLeague.enqueue(new Callback<ViewTeamListByLeague>() {
                    @Override
                    public void onResponse(Call<ViewTeamListByLeague> call, Response<ViewTeamListByLeague> response) {

                        ViewTeamListByLeague teamListByLeague = response.body();
                        System.out.println("response pojo"+teamListByLeague);
                        if(response.body() != null){
                            if (teamListByLeague.getStatus() == 200) {
                                for (com.example.soccerallianceapp.pojo.ViewTeamListByLeague.TeamList teamList : teamListByLeague.getTeamList()) {

                                    comman_data_List.add(new Comman_Data_List(
                                            teamList.getTeamid(),
                                            teamList.getTeamName(),
                                            teamList.getLogo()));
                                }
                                comman_adapter.notifyDataSetChanged();

                                comman_adapter.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {

                                        RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) view.getTag();
                                        int position = viewHolder.getAdapterPosition();

                                        Bundle bundle = new Bundle();
                                        bundle.putInt("team_id",comman_data_List.get(position).getIteam_id());
                                        bundle.putString("Coming_from" ,"TeamList_Fragment_Class");

                                        DashboardNavController.navigate(R.id.player_List_Fragment,bundle);
                                    }
                                });
                            }

                        }else{

                            Toast.makeText(getActivity() ,"Response empty",Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call call, Throwable t) {
                        System.out.println("Error : "+t.getMessage());

                    }
                });

            }
            if(getArguments().getString("Coming_from").equals("Country_Fragment_Class")){
                country = getArguments().getString("country");
                Toast.makeText(context,country, Toast.LENGTH_LONG).show();

                Call listOfLeaguesByCountry = service.getListOfLeaguesByCountryCall(country);

                listOfLeaguesByCountry.enqueue(new Callback<ListOfLeaguesByCountry>() {
                    @Override
                    public void onResponse(Call<ListOfLeaguesByCountry> call, Response<ListOfLeaguesByCountry> response) {

                        ListOfLeaguesByCountry listOfLeaguesByCountry = response.body();
                        System.out.println("response pojo"+listOfLeaguesByCountry);
                        if(response.body() != null){
                            if (listOfLeaguesByCountry.getStatus() == 200) {
                                for (Leagues leaguesList : listOfLeaguesByCountry.getLeagues()) {

                                    comman_data_List.add(new Comman_Data_List(
                                            leaguesList.getName(),
                                            leaguesList.getLogo()));
                                }
                                comman_adapter.notifyDataSetChanged();
                              /*
                                comman_adapter.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {

                                        RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) view.getTag();
                                        int position = viewHolder.getAdapterPosition();

                                        Bundle bundle = new Bundle();
                                        bundle.putString("team_id",String.valueOf(1));
                                        bundle.putString("Coming_from" ,"TeamList_Fragment_Class");

                                        DashboardNavController.navigate(R.id.player_List_Fragment,bundle);
                                    }
                                });*/
                            }

                        }else{

                            Toast.makeText(getActivity() ,"Response empty",Toast.LENGTH_LONG).show();

                        }
                    }

                    @Override
                    public void onFailure(Call call, Throwable t) {
                        System.out.println("Error : "+t.getMessage());

                    }
                });

            }
        }
         else{
            Call<ViewTeamList> viewTeamListcall = service.getviewTeamListCall();
            System.out.println("call " + viewTeamListcall);
            viewTeamListcall.enqueue(new Callback<ViewTeamList>() {

                @Override
                public void onResponse(Call<ViewTeamList> call, Response<ViewTeamList> response) {
                    Log.d("step2", "after onResponse");
                    ViewTeamList realData = response.body();
                    System.out.println("response" + realData);

                    if (response.body() != null) {
                        if (realData.getStatus() == 200) {
                            for (TeamList  getTeamList : realData.getTeamList()) {

                                comman_data_List.add(new Comman_Data_List(
                                        getTeamList.getTeamName()
                                        ,getTeamList.getLogo()));
                            }
                        }

                    } else {
                        Toast.makeText(getActivity(), "Response empty", Toast.LENGTH_LONG).show();

                    }

                    comman_adapter.notifyDataSetChanged();
                }

                @Override
                public void onFailure(Call<ViewTeamList> call, Throwable t) {

                    System.out.println("Error : " + t.getMessage());
                }
            });


        }

        /*comman_adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DashboardNavController.navigate(R.id.player_List_Fragment);
            }
        });
*/
    }

    @Override
    public void onClick(View view) {
        if(view == add_team_btn){
            DashboardNavController.navigate(R.id.add_Team_in_League_Fragment);
        }
    }
}
