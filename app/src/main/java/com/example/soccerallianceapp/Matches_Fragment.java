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

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Matches_Fragment extends Fragment implements View.OnClickListener {

    public NavController DashboardNavController;
    //private RecyclerView upcomingmatch_recycler_view;
    private Context context;
    FloatingActionButton add_player_btn;
    RecyclerView um_recycler_View,pm_recycler_View;


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

        um_recycler_View = view.findViewById(R.id.um_recycler_View);
        pm_recycler_View = view.findViewById(R.id.pm_recycler_View);


        /*
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
*/



    }

    @Override
    public void onClick(View view) {

    }

}