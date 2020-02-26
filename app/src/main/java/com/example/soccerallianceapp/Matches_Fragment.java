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
import com.example.soccerallianceapp.pojo.matchListDashboard.MatchListDashboard;
import com.example.soccerallianceapp.pojo.matchListDashboard.UpcomingMatchList;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Matches_Fragment extends Fragment implements View.OnClickListener {

    public NavController DashboardNavController;
    //private RecyclerView upcomingmatch_recycler_view;
    private Context context;
    private ArrayList<Comman_Data_List> comman_data_List;
    private Comman_adapter comman_adapter;
    FloatingActionButton add_player_btn;
    MaterialCardView um_cardView1;


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

        comman_data_List = new ArrayList<Comman_Data_List>();

        comman_adapter = new Comman_adapter(comman_data_List, context);
        um_cardView1 = view.findViewById(R.id.um_cardView1);
//        um_cardView1.setOnClickListener(this);

        Getdataservice service = RetroFitInstance.getRetrofitInstance().create(Getdataservice.class);
        Call<MatchListDashboard> matchlistcall =service.getupcomingMatches_guestDashboardCall();
        System.out.println("call "+ matchlistcall);
        matchlistcall.enqueue(new Callback<MatchListDashboard>() {

            @Override
            public void onResponse(Call<MatchListDashboard> call, Response<MatchListDashboard> response) {

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

    @Override
    public void onClick(View view) {
        if(view == um_cardView1){
            DashboardNavController.navigate(R.id.upcomingMatchFragment);
        }

    }

}