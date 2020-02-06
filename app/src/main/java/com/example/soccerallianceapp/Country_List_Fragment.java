package com.example.soccerallianceapp;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.soccer_alliance_project_test.R;

import java.util.ArrayList;


public class Country_List_Fragment extends Fragment {

    private RecyclerView country_recycler_view;
    private Context context;
    private ArrayList<Comman_Data_List> comman_data_List;
    private Comman_adapter comman_adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_country_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        context = getActivity().getApplicationContext();

        /*--------Teams Adapter Configuration--------*/
        country_recycler_view = view.findViewById(R.id.country_recycler_view);
        comman_data_List = new ArrayList<Comman_Data_List>();
        comman_data_List.clear();

        comman_data_List.add(new Comman_Data_List("Country 1", R.drawable.user));
        comman_data_List.add(new Comman_Data_List("Country 2", R.drawable.user));
        comman_data_List.add(new Comman_Data_List("Country 3", R.drawable.user));
        comman_data_List.add(new Comman_Data_List("Country 4", R.drawable.user));
        comman_data_List.add(new Comman_Data_List("Country 5", R.drawable.user));

        comman_adapter = new Comman_adapter(comman_data_List,context);
        comman_adapter.notifyDataSetChanged();
        country_recycler_view.setLayoutManager(new LinearLayoutManager(context));
        country_recycler_view.setAdapter(comman_adapter);

    }
}
