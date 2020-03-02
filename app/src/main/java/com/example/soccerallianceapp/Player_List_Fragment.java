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
import com.example.soccerallianceapp.pojo.ViewPlayerListByTeamDashboard.PlayerList;
import com.example.soccerallianceapp.pojo.ViewPlayerListByTeamDashboard.ViewPlayerListDashboard;
import com.example.soccerallianceapp.pojo.ViewTeamDetail.TeamDetails;
import com.example.soccerallianceapp.pojo.ViewTeamDetail.ViewTeamDetail;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Player_List_Fragment extends Fragment implements View.OnClickListener {


    public NavController DashboardNavController;
    private RecyclerView player_recycler_view;
    private Context context;
    private ArrayList<Comman_Data_List> comman_data_List;
    private Comman_adapter comman_adapter;
    FloatingActionButton add_player_btn,edit_player_btn;
    int team_id;
    FirebaseAuth fAuth;
    String uid="";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_player_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        DashboardNavController = Navigation.findNavController(getActivity(), R.id.dashboard_host_fragment);
        context = getActivity().getApplicationContext();

        add_player_btn = view.findViewById(R.id.add_player_btn);
        add_player_btn.setOnClickListener(this);

        edit_player_btn = view.findViewById(R.id.edit_player_btn);
        edit_player_btn.setOnClickListener(this);

        if(getActivity().getIntent().getExtras()==null){
            add_player_btn.setVisibility(View.GONE);
        }
        /*if(getActivity().getIntent().getStringExtra("user_type").equals("Team_Manager")){
            add_player_btn.setVisibility(View.VISIBLE);
            //usertype = "Team_Manager";

        }

         */

        Getdataservice service = RetroFitInstance.getRetrofitInstance().create(Getdataservice.class);

        fAuth = FirebaseAuth.getInstance();
        uid =fAuth.getCurrentUser().getUid();

        /*--------Teams Adapter Configuration--------*/
        player_recycler_view = view.findViewById(R.id.player_recycler_view);
        comman_data_List = new ArrayList<Comman_Data_List>();
        comman_data_List.clear();

        comman_adapter = new Comman_adapter(comman_data_List, context);

        player_recycler_view.setLayoutManager(new LinearLayoutManager(context));
        player_recycler_view.setAdapter(comman_adapter);


        if(getArguments()!=null){
            if(getArguments().getString("Coming_from").equals("TeamList_Fragment_Class")){
                team_id = getArguments().getInt("team_id");
                Toast.makeText(context,""+team_id, Toast.LENGTH_LONG).show();
                System.out.println("team Id "+team_id);

                getPlayerlist(team_id,service);


            }else if(getArguments().getString("Coming_from").equals("Team_Manager")){
                add_player_btn.setVisibility(View.VISIBLE);

                team_id = getArguments().getInt("team_id");

                //getTeamid(uid,service);


                getPlayerlist(team_id,service);
                Toast.makeText(context,"got success"+team_id, Toast.LENGTH_LONG).show();

                System.out.println("team Id for team"+team_id);

/*
                if(team_id != 0){
                    getPlayerlist(team_id,service);
                    Toast.makeText(context,"got success"+team_id, Toast.LENGTH_LONG).show();

                }
                Toast.makeText(context,"take time to get player list"+team_id, Toast.LENGTH_LONG).show();



 */
            }
        }
    }

    private void getPlayerlist(int team_id, Getdataservice service) {

        Call viewPlayerLIstByTeam = service.getviewPlayerListFromTeamDashboardCall(team_id);
        System.out.println("Before respon");
        viewPlayerLIstByTeam.enqueue(new Callback<ViewPlayerListDashboard>() {
            @Override
            public void onResponse(Call<ViewPlayerListDashboard> call, Response<ViewPlayerListDashboard> response) {

                ViewPlayerListDashboard playerListDashboard = response.body();
                System.out.println("after get response "+response.body());
                if(response.body() != null){
                    if (playerListDashboard.getStatus() == 200) {
                        System.out.println("status from player frag "+playerListDashboard.getStatus());
                        for (PlayerList playerList : playerListDashboard.getPlayerList()) {

                            comman_data_List.add(new Comman_Data_List(
                                    playerList.getFullName(),
                                    playerList.getPlayerPhoto(),
                                    playerList.getStrength()
                            ));
                        }
                        comman_adapter.notifyDataSetChanged();
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

    /*
    private void getTeamid(String uid, Getdataservice service) {

        Call<ViewTeamDetail> call = service.ViewTeamDetail(uid);

        call.enqueue(new Callback<ViewTeamDetail>() {
            @Override
            public void onResponse(Call<ViewTeamDetail> call, Response<ViewTeamDetail> response) {
                ViewTeamDetail viewTeam = response.body();



                TeamDetails teamDetails = viewTeam.getTeamDetails();

                if(teamDetails!= null){

                    team_id = teamDetails.getTeamId();

                    System.out.println("id of team "+team_id);

                }

            }

            @Override
            public void onFailure(Call<ViewTeamDetail> call, Throwable t) {

            }
        });

        System.out.println("teamid"+team_id);

        //getPlayerlist(team_id,service);
    }

     */

    @Override
    public void onClick(View view) {
        if(view == add_player_btn){
            Bundle bundle = new Bundle();
            bundle.putInt("team_id",team_id);
            DashboardNavController.navigate(R.id.add_Player_Fragment,bundle);
        }
        if(view == edit_player_btn){
            DashboardNavController.navigate(R.id.editTeamFragment);

        }

    }
}
