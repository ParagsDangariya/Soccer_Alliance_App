package com.example.soccerallianceapp;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.soccer_alliance_project_test.R;
import com.google.android.material.button.MaterialButton;

import java.sql.SQLOutput;
import java.util.ArrayList;


public class LeagueOperationsFragment extends Fragment implements View.OnClickListener{


    public NavController DashboardNavController;
    private Context context;
    private ArrayList<Comman_Data_List> comman_data_List;
    private Comman_adapter comman_adapter;
    TextView LeagueName;
    String league_name,league_icon;
    int league_id;
    ImageView league_logo;




    MaterialButton league_operations_addteam_btn,league_operations_removeteam_btn,
            league_operations_teamlist_btn,league_operations_upmatch_btn,
            league_operations_schedulematch_btn,league_operations_playedmatch_btn;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {


            league_id = getArguments().getInt("League_id");
            System.out.println("league_id on operation screen on create.."+league_id);

        }
            }

    public LeagueOperationsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        DashboardNavController = Navigation.findNavController(getActivity(),R.id.dashboard_host_fragment);
        context = getActivity().getApplicationContext();

        LeagueName = view.findViewById(R.id.team_name);

        league_operations_addteam_btn = view.findViewById(R.id.league_operations_addteam_btn);
        league_operations_addteam_btn.setOnClickListener(this);

        league_operations_removeteam_btn = view.findViewById(R.id.league_operations_removeteam_btn);
        league_operations_removeteam_btn.setOnClickListener(this);

        league_operations_teamlist_btn = view.findViewById(R.id.league_operations_teamlist_btn);
        league_operations_teamlist_btn.setOnClickListener(this);

        league_operations_upmatch_btn = view.findViewById(R.id.league_operations_upmatch_btn);
        league_operations_upmatch_btn.setOnClickListener(this);

        league_operations_playedmatch_btn = view.findViewById(R.id.league_operations_playedmatch_btn);
        league_operations_playedmatch_btn.setOnClickListener(this);

        league_logo = view.findViewById(R.id.league_icon);


        Bundle bundle = getArguments();

        league_operations_schedulematch_btn = view.findViewById(R.id.league_operations_schedulematch_btn);


        //Getdataservice service = RetroFitInstance.getRetrofitInstance().create(Getdataservice.class);


        comman_data_List = new ArrayList<Comman_Data_List>();
        comman_data_List.clear();
        comman_adapter = new Comman_adapter(comman_data_List, context);

        try {
            if (getArguments() != null) {
                if (getArguments().getString("Coming_from").equals("ListLeaguesFragment_Class")) {

                    league_id = getArguments().getInt("League_id");

                    Toast.makeText(context, "league Operation Fragment in League Id : "+ league_id, Toast.LENGTH_LONG).show();

                    league_name = getArguments().getString("League_name");


                    league_icon = getArguments().getString("League_Logo");

                    LeagueName.setText(league_name);
                    Glide.with(context).load(league_icon).fitCenter().into(league_logo);

                    bundle.putString("League_name",league_name);
                    bundle.putInt("League_id",league_id);
                    bundle.putString("ComingFrom" ,"LeagueUpcomingMatchFragment");
                    league_operations_upmatch_btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            System.out.println("forward form opration fragment");
                            bundle.putInt("League_id",league_id);
                            DashboardNavController.navigate(R.id.leagueUpcomingMatchFragment,bundle);

                        }
                    });



                    league_operations_schedulematch_btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            DashboardNavController.navigate(R.id.scheduleMatchFragment,bundle);

                        }
                    });
                }


            } else {
                Toast.makeText(getActivity(), "Something Went Wrong", Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_league_operations, container, false);
    }

    @Override
    public void onClick(View v) {
        if(v == league_operations_teamlist_btn){
            Bundle bundleLeague = new Bundle();
            bundleLeague.putString("Coming_from","TeamListInLeague");
            //bundleLeague.putString("Coming_from","Leagues_Fragment_Class");
            //bundleLeague.putString("league_id", String.valueOf(league_id));
            bundleLeague.putInt("League_id from operation",league_id);
            DashboardNavController.navigate(R.id.teamListFragment,bundleLeague);
        }

        if( v == league_operations_addteam_btn){

            System.out.println("league_id on operation screen..add team"+league_id);
            Bundle bundleleague = new Bundle();
            bundleleague.putString("Coming_from","AddTeamInLeague");
            bundleleague.putInt("League_id from operation",league_id);
            System.out.println("league"+league_id);
            DashboardNavController.navigate(R.id.teamListFragment,bundleleague);
        }

        if(v == league_operations_removeteam_btn){

            System.out.println("league_id on operation screen..remove team"+league_id);
            Bundle bundleleague = new Bundle();
            bundleleague.putString("Coming_from","RemoveTeamInLeague");
            bundleleague.putInt("League_id from operation",league_id);
            System.out.println("league"+league_id);
            DashboardNavController.navigate(R.id.teamListFragment,bundleleague);
        }
        if( v == league_operations_playedmatch_btn){

            Bundle bundle = new Bundle();
            //bundle.putString("League_name",league_name);
            bundle.putInt("League_id",league_id);
            DashboardNavController.navigate(R.id.leaguePlayedMatchFragment,bundle);
        }

    }
}
