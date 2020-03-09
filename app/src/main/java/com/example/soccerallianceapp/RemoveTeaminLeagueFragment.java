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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RemoveTeaminLeagueFragment extends Fragment implements View.OnClickListener {

    public NavController DashboardNavController;

    //private RecyclerView team_recycler_view;
    private Context context;
    //private ArrayList<Comman_Data_List> comman_data_List;
    //private Comman_adapter comman_adapter;
    MaterialButton remove_team_from_league_btn;
    int team_id;
    int league_id;
    ImageView Team_icon;
    TextView Team_name;
    //String country="";
    Getdataservice service;
    String team_name,team_logo;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

            league_id = getArguments().getInt("League_id");
            team_id = getArguments().getInt("team_id");
            team_name = getArguments().getString("team_name");
            team_logo = getArguments().getString("logo_url");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_remove_teamin_league, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        DashboardNavController = Navigation.findNavController(getActivity(), R.id.dashboard_host_fragment);
        context = getActivity().getApplicationContext();

        remove_team_from_league_btn = view.findViewById(R.id.remove_team_from_league_btn);
        remove_team_from_league_btn.setOnClickListener(this);


        Team_name = view.findViewById(R.id.team_name);
        Team_icon = view.findViewById(R.id.league_icon);

        Team_name.setText(team_name);

        Glide.with(context).load(team_logo).centerCrop().into(Team_icon);

        service = RetroFitInstance.getRetrofitInstance().create(Getdataservice.class);
    }

    @Override
    public void onClick(View v) {
        if(v == remove_team_from_league_btn){


            Toast.makeText(context,"League "+league_id,Toast.LENGTH_LONG).show();

            Toast.makeText(context,"team "+team_id,Toast.LENGTH_SHORT).show();

            System.out.println("league_id  "+league_id);

            System.out.println("team id "+team_id);


            Call call = service.removeTeamFromLeague(league_id,team_id);

            call.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) {

                    if(!response.isSuccessful()){
                        int s = response.code();
                        System.out.println("code"+s);
                    }
                    System.out.println("Team is added in league successfully ");

                    Toast.makeText(context,"Team is added in league successfully",Toast.LENGTH_SHORT).show();

                    Bundle bundleleague = new Bundle();
                    bundleleague.putString("Coming_from","RemoveTeamInLeague");
                    bundleleague.putInt("League_id from operation",league_id);

                    System.out.println("league"+league_id);

                    DashboardNavController.navigate(R.id.teamListFragment,bundleleague);

                }

                @Override
                public void onFailure(Call call, Throwable t) {


                    System.out.println("error" +t.getMessage());
                    Toast.makeText(context,"Something Went wrong .Try Again..."+t.getMessage(),Toast.LENGTH_SHORT).show();

                }
            });
        }

    }
}
