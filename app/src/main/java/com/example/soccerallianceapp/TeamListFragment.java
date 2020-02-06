package com.example.soccerallianceapp;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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


public class TeamListFragment extends Fragment implements View.OnClickListener{

    public NavController DashboardNavController;

    private RecyclerView team_recycler_view;
    private Context context;
    private ArrayList<Comman_Data_List> comman_data_List;
    private Comman_adapter comman_adapter;
    FloatingActionButton add_team_btn;

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

        /*--------Teams Adapter Configuration--------*/
        team_recycler_view = view.findViewById(R.id.team_recycler_view);
        comman_data_List = new ArrayList<Comman_Data_List>();
        comman_data_List.clear();

        comman_data_List.add(new Comman_Data_List("Team 1", R.drawable.user));
        comman_data_List.add(new Comman_Data_List("Team 2", R.drawable.user));
        comman_data_List.add(new Comman_Data_List("Team 3", R.drawable.user));
        comman_data_List.add(new Comman_Data_List("Team 4", R.drawable.user));
        comman_data_List.add(new Comman_Data_List("Team 5", R.drawable.user));

        comman_adapter = new Comman_adapter(comman_data_List,context);
        comman_adapter.notifyDataSetChanged();
        team_recycler_view.setLayoutManager(new LinearLayoutManager(context));
        team_recycler_view.setAdapter(comman_adapter);

        comman_adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DashboardNavController.navigate(R.id.player_List_Fragment);
            }
        });

    }

    @Override
    public void onClick(View view) {
        if(view == add_team_btn){
            DashboardNavController.navigate(R.id.add_Team_in_League_Fragment);
        }
    }
}
