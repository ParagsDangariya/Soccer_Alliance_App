package com.example.soccerallianceapp;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.soccer_alliance_project_test.R;


/**
 * A simple {@link Fragment} subclass.
 * Sign up part-1 has Email and phone number field and decide user type.
 *
 */
public class SignUp1_Fragment extends Fragment implements View.OnClickListener {

    public NavController navController;
    private Context context;

    ConstraintLayout signup_team_manager_btn_type,signup_league_manager_btn_type;
    String user_type = "";
    ImageButton signup1_next_btn;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up1, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(getActivity(),R.id.host_fragment);
        context = getActivity().getApplicationContext();
        signup1_next_btn = view.findViewById(R.id.signup1_next_btn);
        signup_league_manager_btn_type = view.findViewById(R.id.signup_league_manager_btn_type);
        signup_team_manager_btn_type = view.findViewById(R.id.signup_team_manager_btn_type);
        signup_league_manager_btn_type.setOnClickListener(this);
        signup_team_manager_btn_type.setOnClickListener(this);
        signup1_next_btn.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if(view == signup_team_manager_btn_type){
            signup_team_manager_btn_type.setBackground(ContextCompat.getDrawable(context,R.drawable.user_type_selection_border));
            signup_league_manager_btn_type.setBackground(null);
            user_type = "Team Manager";
        }
        else if(view==signup_league_manager_btn_type){
            signup_league_manager_btn_type.setBackground(ContextCompat.getDrawable(context,R.drawable.user_type_selection_border));
            signup_team_manager_btn_type.setBackground(null);
            user_type = "League Manager";
        }
        else if(view==signup1_next_btn){
            navController.navigate(R.id.signUp2_Fragment);
        }
    }
}
