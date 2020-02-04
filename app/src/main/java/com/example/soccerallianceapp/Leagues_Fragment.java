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


public class Leagues_Fragment extends Fragment {

    private RecyclerView league_recycler_view;
    private Context context;
    private ArrayList<Leagues_List> leagueLists;
    private Leagues_adapter leagueAdapter;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_leagues, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        context = getActivity().getApplicationContext();

        /*--------league Adapter Configuration--------*/
        league_recycler_view = view.findViewById(R.id.leagues_recycler_view);
        leagueLists = new ArrayList<Leagues_List>();
        leagueLists.clear();

        leagueLists.add(new Leagues_List("Match 1",R.drawable.user));
        leagueLists.add(new Leagues_List("Match 2",R.drawable.user));
        leagueLists.add(new Leagues_List("Match 3",R.drawable.user));
        leagueLists.add(new Leagues_List("Match 4",R.drawable.user));
        leagueLists.add(new Leagues_List("Match 5",R.drawable.user));

        leagueAdapter = new Leagues_adapter(leagueLists,context);
        leagueAdapter.notifyDataSetChanged();
        league_recycler_view.setLayoutManager(new LinearLayoutManager(context));
        league_recycler_view.setAdapter(leagueAdapter);
    }
}
