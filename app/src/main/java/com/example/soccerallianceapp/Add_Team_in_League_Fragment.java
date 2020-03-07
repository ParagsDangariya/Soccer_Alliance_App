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

import java.util.ArrayList;


public class Add_Team_in_League_Fragment extends Fragment {


    public NavController DashboardNavController;

    private RecyclerView add_team_recycler_view;
    private Context context;
    private ArrayList<Comman_Checkbox_Data_List> comman_checkbox_data_lists;
    private Comman_checkbox_adapter comman_checkbox_adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_team_in_league, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        DashboardNavController = Navigation.findNavController(getActivity(), R.id.dashboard_host_fragment);
        context = getActivity().getApplicationContext();

        /*--------Teams Adapter Configuration--------*/
        add_team_recycler_view = view.findViewById(R.id.add_team_recycler_view);
        comman_checkbox_data_lists = new ArrayList<Comman_Checkbox_Data_List>();
        comman_checkbox_data_lists.clear();
        /*

        comman_checkbox_data_lists.add(new Comman_Checkbox_Data_List("Add Team 1", R.drawable.user));
        comman_checkbox_data_lists.add(new Comman_Checkbox_Data_List("Add Team 2", R.drawable.user));
        comman_checkbox_data_lists.add(new Comman_Checkbox_Data_List("Add Team 3", R.drawable.user));
        comman_checkbox_data_lists.add(new Comman_Checkbox_Data_List("Add Team 4", R.drawable.user));
        comman_checkbox_data_lists.add(new Comman_Checkbox_Data_List("Add Team 5", R.drawable.user));



         */
        comman_checkbox_adapter = new Comman_checkbox_adapter(comman_checkbox_data_lists,context);
        comman_checkbox_adapter.notifyDataSetChanged();
        add_team_recycler_view.setLayoutManager(new LinearLayoutManager(context));
        add_team_recycler_view.setAdapter(comman_checkbox_adapter);

        comman_checkbox_adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(context,"player selected.",Toast.LENGTH_SHORT).show();
            }
        });

    }
}
