package com.example.soccerallianceapp;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.soccer_alliance_project_test.R;
import com.example.soccerallianceapp.pojo.PlayerDetail.PlayerDetail;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import retrofit2.Call;


public class EditTeamFragment extends Fragment implements View.OnClickListener{

    public NavController DashboardNavController;
    MaterialButton update_new_player_btn,remove_player_btn;
    TextView edit_player_name_edt_txt,edit_player_role_edt_txt,edit_player_strength_edt_txt;
    String name,role,strength,imageUri ="nophoto",uid="";
    private Context context;
    Getdataservice service;
    int player_id;
    FirebaseAuth fAuth;
    private StorageReference mStorageRef;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            player_id = Integer.parseInt(getArguments().getString("player_id"));

        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_edit_team, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        DashboardNavController = Navigation.findNavController(getActivity(),R.id.dashboard_host_fragment);
        context = getActivity().getApplicationContext();

        service = RetroFitInstance.getRetrofitInstance().create(Getdataservice.class);

        update_new_player_btn = view.findViewById(R.id.update_new_player_btn);
        update_new_player_btn.setOnClickListener(this);

        remove_player_btn = view.findViewById(R.id.remove_player_btn);
        remove_player_btn.setOnClickListener(this);

        edit_player_name_edt_txt = view.findViewById(R.id.edit_player_name_edt_txt);
        edit_player_role_edt_txt = view.findViewById(R.id.edit_player_role_edt_txt);
        edit_player_strength_edt_txt = view.findViewById(R.id.edit_player_strength_edt_txt);
        mStorageRef = FirebaseStorage.getInstance().getReference();


        fAuth = FirebaseAuth.getInstance();

        uid =fAuth.getCurrentUser().getUid();

        service = RetroFitInstance.getRetrofitInstance().create(Getdataservice.class);


        Call<PlayerDetail> call = service.PlayerDetail(player_id);
    }

    @Override
    public void onClick(View v) {
        if(v == remove_player_btn){

        }

        if(v == update_new_player_btn){

        }

    }
}
