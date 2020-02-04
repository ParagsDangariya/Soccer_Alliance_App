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


/**
 * A simple {@link Fragment} subclass.
 */
public class SignUp3_Fragment extends Fragment implements View.OnClickListener{


    public NavController navController;
    private Context context;

    ImageButton signup3_next_btn;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up3, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(getActivity(),R.id.host_fragment);
        context = getActivity().getApplicationContext();

        signup3_next_btn = view.findViewById(R.id.signup3_next_btn);
        signup3_next_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view == signup3_next_btn){

            navController.navigate(R.id.signUp4_Fragment);

        }
    }
}
