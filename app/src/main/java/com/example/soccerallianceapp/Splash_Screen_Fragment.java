package com.example.soccerallianceapp;


import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.soccer_alliance_project_test.R;


public class Splash_Screen_Fragment extends Fragment {

    public NavController navController;
    private Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash__screen, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        getActivity().getWindow().setStatusBarColor(Color.TRANSPARENT);

        context = getActivity().getApplicationContext();
        navController = Navigation.findNavController(getActivity(), R.id.host_fragment);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                navController.navigate(R.id.loginFragment);
            }
        },3000);
    }
}
