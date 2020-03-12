package com.example.soccerallianceapp;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.soccer_alliance_project_test.R;
import com.google.android.material.tabs.TabLayout;


public class Match_Score_Fragment extends Fragment {


    private TabLayout ms_tabs_layout;
    private ViewPager ms_tabs_view_pager;
    private Match_Score_ViewPageAdapter ms_viewPageAdapter;
    Bundle bundle;


    int matchid,team1id,team2id;
    String team1,team2,logo1,logo2,date,time;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null){
            matchid = getArguments().getInt("played_match_id");
            team1id = getArguments().getInt("played_team1_id");
            team2id = getArguments().getInt("played_team2_id");
            team1 = getArguments().getString("played_team1name");
            team2 = getArguments().getString("played_team2name");
            logo1 = getArguments().getString("played_team1logo");
            logo2 = getArguments().getString("played_team2logo");
            date = getArguments().getString("played_match_date");
            time = getArguments().getString("played_match_time");

            System.out.println("on create on match score"+matchid);

            bundle = new Bundle();
            bundle.putInt("match_id", matchid);
            bundle.putInt("team1id",team1id);
            bundle.putInt("team2id",team2id);
            bundle.putString("team1",team1);
            bundle.putString("team2",team2);
            bundle.putString("logo1",logo1);
            bundle.putString("logo2",logo2);
            bundle.putString("date",date);
            bundle.putString("time",time);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_match_score, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        /*-------------Tab Layout Setup---------*/
        ms_tabs_layout = view.findViewById(R.id.ms_tabs_layout);
        ms_tabs_view_pager = view.findViewById(R.id.ms_tabs_view_pager);
        ms_viewPageAdapter = new Match_Score_ViewPageAdapter(getChildFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT,2,bundle);
        ms_tabs_view_pager.setAdapter(ms_viewPageAdapter);
        ms_tabs_view_pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(ms_tabs_layout));
        ms_tabs_layout.setupWithViewPager(ms_tabs_view_pager);
        ms_tabs_layout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                ms_tabs_view_pager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }
}
