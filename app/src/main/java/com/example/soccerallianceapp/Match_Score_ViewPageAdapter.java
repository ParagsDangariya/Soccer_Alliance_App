package com.example.soccerallianceapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class Match_Score_ViewPageAdapter extends FragmentPagerAdapter {

    private int numOfTabs;
    private String[] tabTitles = new String[]{"Line Up", "Statistics"};

    public Match_Score_ViewPageAdapter(@NonNull FragmentManager fm, int behavior, int numOfTabs) {
        super(fm, behavior);
        this.numOfTabs = numOfTabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment=null;
        switch (position){
            case 0:




                return new Line_Up_Fragment();
            case 1:



                return new Statistics_Fragment();
            default:
                    return null;
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }
}
