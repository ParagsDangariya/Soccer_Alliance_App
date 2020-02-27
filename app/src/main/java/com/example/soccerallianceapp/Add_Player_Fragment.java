package com.example.soccerallianceapp;


import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.soccer_alliance_project_test.R;
import com.google.android.material.button.MaterialButton;


/**
 * A simple {@link Fragment} subclass.
 */
public class Add_Player_Fragment extends Fragment implements View.OnClickListener{



    Getdataservice service;
    public NavController navController;
    private Context context;
    MaterialButton add_new_player_btn;
    TextView add_player_name_edt_txt,add_player_role_edt_txt,add_player_strength_edt_txt;
    String name,role,strength,image;

    private static final int GALLERY_REQUEST_CODE = 105;
    ImageView add_player_user_image;

    public Add_Player_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_player, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        getActivity().getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));

        navController = Navigation.findNavController(getActivity(),R.id.host_fragment);
        context = getActivity().getApplicationContext();

        add_player_name_edt_txt = view.findViewById(R.id.add_player_name_edt_txt);
        add_player_role_edt_txt = view.findViewById(R.id.add_player_role_edt_txt);
        add_player_strength_edt_txt = view.findViewById(R.id.add_player_strength_edt_txt);

        add_new_player_btn = view.findViewById(R.id.add_new_player_btn);
        add_new_player_btn.setOnClickListener(this);

        add_player_user_image = view.findViewById(R.id.add_player_user_image);
        add_player_user_image.setOnClickListener(this);

        service = RetroFitInstance.getRetrofitInstance().create(Getdataservice.class);

    }

    @Override
    public void onClick(View view) {

        if(view == add_new_player_btn){

            name = add_player_name_edt_txt.getEditableText().toString().trim();
            role = add_player_role_edt_txt.getEditableText().toString().trim();
            strength = add_player_strength_edt_txt.getEditableText().toString().trim();
            if (TextUtils.isEmpty(name)) {
                add_player_name_edt_txt.setError("Player name is Required.");
                return;


            }else if(TextUtils.isEmpty(role)){
                add_player_role_edt_txt.setError("Role or Position of Player is Required.");
                return;

            }else if(TextUtils.isEmpty(strength)){
                add_player_strength_edt_txt.setError("Strength is Required.");
                return;

            }
        }
        if( view == add_player_user_image){


        }
    }
}
