package com.example.soccerallianceapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.soccer_alliance_project_test.R;

/*
* Upcoming matches will be which are coming in future and display details of match fixed by whom and who are inclueded.
*
* */
public class UpcomingMatchFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_upcoming_match, container, false);
    }
}
