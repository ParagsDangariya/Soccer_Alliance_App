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

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.soccer_alliance_project_test.R;
import com.example.soccerallianceapp.pojo.ListOfLeaguesByCountry.Leagues;
import com.example.soccerallianceapp.pojo.ListOfLeaguesByCountry.ListOfLeaguesByCountry;
import com.example.soccerallianceapp.pojo.ViewTeamListByLeague.ViewTeamListByLeague;
import com.example.soccerallianceapp.pojo.ViewTeamListDashboard.TeamList;
import com.example.soccerallianceapp.pojo.ViewTeamListDashboard.ViewTeamList;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class addTeaminLeague extends Fragment implements View.OnClickListener{

    public NavController DashboardNavController;

    //private RecyclerView team_recycler_view;
    private Context context;
    //private ArrayList<Comman_Data_List> comman_data_List;
    //private Comman_adapter comman_adapter;
    MaterialButton add_team_in_league_btn;
    int league_id,team_id;
    //String country="";

    public addTeaminLeague() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

            league_id = getArguments().getInt("League_id");
            team_id = getArguments().getInt("team_id");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_teamin_league, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        DashboardNavController = Navigation.findNavController(getActivity(), R.id.dashboard_host_fragment);
        context = getActivity().getApplicationContext();

        add_team_in_league_btn = view.findViewById(R.id.add_team_in_league_btn);
        add_team_in_league_btn.setOnClickListener(this);


        //Getdataservice service = RetroFitInstance.getRetrofitInstance().create(Getdataservice.class);


    }

    @Override
    public void onClick(View v) {
        if(v == add_team_in_league_btn){

            Toast.makeText(context,"team "+team_id,Toast.LENGTH_LONG).show();

        }

    }
}
