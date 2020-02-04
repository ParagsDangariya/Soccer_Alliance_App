package com.example.soccerallianceapp;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.soccer_alliance_project_test.R;


public class forgot_pass1_Fragment extends Fragment implements View.OnClickListener {

    public NavController navController;
    private Context context;

    ImageButton fp1_next_btn;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_forgot_pass1, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(getActivity(),R.id.host_fragment);
        context = getActivity().getApplicationContext();

        fp1_next_btn = view.findViewById(R.id.fp1_next_btn);
        fp1_next_btn.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if(view == fp1_next_btn){
            navController.navigate(R.id.forgot_pass2_Fragment);
        }
    }
}
