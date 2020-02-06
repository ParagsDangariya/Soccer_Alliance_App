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


public class Home_Fragment extends Fragment {

    private TabLayout dashboardTabLayout;
    private ViewPager tabs_view_pager;
    private ViewPageAdapter viewPageAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        /*-------------Tab Layout Setup---------*/
        dashboardTabLayout = view.findViewById(R.id.dashboard_tabs_layout);
        tabs_view_pager = view.findViewById(R.id.tabs_view_pager);
        viewPageAdapter = new ViewPageAdapter(getChildFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT,2);
        tabs_view_pager.setAdapter(viewPageAdapter);
        tabs_view_pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(dashboardTabLayout));
        dashboardTabLayout.setupWithViewPager(tabs_view_pager);
        dashboardTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                tabs_view_pager.setCurrentItem(tab.getPosition());
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
