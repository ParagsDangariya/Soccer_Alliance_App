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
        ms_viewPageAdapter = new Match_Score_ViewPageAdapter(getChildFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT,2);
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
