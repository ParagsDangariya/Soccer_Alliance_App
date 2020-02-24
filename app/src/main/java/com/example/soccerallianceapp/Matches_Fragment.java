package com.example.soccerallianceapp;


import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.soccer_alliance_project_test.R;
import com.example.soccerallianceapp.pojo.matchListDashboard.MatchListDashboard;
import com.example.soccerallianceapp.pojo.matchListDashboard.UpcomingMatchList;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Matches_Fragment extends Fragment  {

     MaterialCardView um_cardView1,pm_cardView1;
    public NavController DashboardNavController;
    private Context context;
    TextView umc1_team1,umc1_date,umc1_team2;
    private RecyclerView match_recycleview;
  /*  private ArrayList<Match_Data_List> match_data_List;
    private MatchData_adapter matchData_adapter;*/
     private ArrayList<Comman_Data_List> comman_data_List;
    private Comman_adapter comman_adapter;


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

        /*--------Match Adapter Configuration--------*/


        comman_data_List = new ArrayList<Comman_Data_List>();
        match_recycleview = view.findViewById(R.id.match_recycler_view);
        comman_adapter = new Comman_adapter(comman_data_List, context);
        match_recycleview.setLayoutManager(new LinearLayoutManager(context));
        match_recycleview.setAdapter(comman_adapter);

       /* um_cardView1 = view.findViewById(R.id.um_cardView1);
        um_cardView1.setOnClickListener(this);
        umc1_team1 =view.findViewById(R.id.umc1_team1);
        umc1_team2 =view.findViewById(R.id.umc1_team2);
        umc1_date =view.findViewById(R.id.umc1_date);
        pm_cardView1 = view.findViewById(R.id.pm_cardView1);
        pm_cardView1.setOnClickListener(this);
      */

        Getdataservice service = RetroFitInstance.getRetrofitInstance().create(Getdataservice.class);
        Call<MatchListDashboard> matchlistcall =service.getupcomingMatches_guestDashboardCall();
        System.out.println("call "+ matchlistcall);
        matchlistcall.enqueue(new Callback<MatchListDashboard>() {

            @Override
            public void onResponse(Call<MatchListDashboard> call, Response<MatchListDashboard> response) {
                Log.d("step2", "after onResponse");
                MatchListDashboard realData = response.body();
                System.out.println("response" + realData);
                if (response.body() != null) {
                    System.out.print("before status "+realData.getStatus());
                    if (realData.getStatus() == 200) {
                       for (UpcomingMatchList matchelist : realData.getUpcomingMatchList()) {
                           //set logo when imge gets done. here
                           System.out.print("before loop "+matchelist.getName());
                           comman_data_List.add(new Comman_Data_List(
                                   matchelist.getName()));
                       }
                    }
                    comman_adapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(getActivity(), "Response empty", Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<MatchListDashboard> call, Throwable t) {

                System.out.println("Error : " + t.getMessage());
            }
        });

    }

   /* @Override
    public void onClick(View view) {
        if(view == um_cardView1){
            DashboardNavController.navigate(R.id.upcomingMatchFragment);
        }
        else if(view == pm_cardView1){
            DashboardNavController.navigate(R.id.match_Score_Fragment);
        }
    }*/

}
