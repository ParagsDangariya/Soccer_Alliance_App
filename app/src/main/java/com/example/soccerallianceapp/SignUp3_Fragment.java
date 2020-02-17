package com.example.soccerallianceapp;


import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.soccer_alliance_project_test.R;
import com.google.android.material.textfield.TextInputEditText;


/**
 * A simple {@link Fragment} subclass.
 */
public class SignUp3_Fragment extends Fragment implements View.OnClickListener{


    public NavController navController;
    private Context context;

    ImageButton signup3_next_btn;

    TextInputEditText signup3_new_password,signup3_confirm_password;

    String email,phone,name,gender,country,age;


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
        signup3_new_password = view.findViewById(R.id.signup3_new_password_edit_txt);
        signup3_confirm_password = view.findViewById(R.id.signup3_confirm_password_edit_txt);

        //email = getArguments().getString("email");
       phone = getArguments().getString("phone");
        name = getArguments().getString("name");
        gender = getArguments().getString("gender");
        country = getArguments().getString("country");
        age = getArguments().getString("age");

       // System.out.println(email);

    }

    @Override
    public void onClick(View view) {
        if(view == signup3_next_btn){

            String password = signup3_new_password.getEditableText().toString().trim();
            String confirmpassword = signup3_confirm_password.getEditableText().toString().trim();
            if(TextUtils.isEmpty(password)){
                signup3_new_password.setError("Password is Required.");
                return ;
            }else if(password.length() < 6){
                signup3_new_password.setError("Password Must be >= 6 Characters");
                return;
            }




            if(password == confirmpassword){
                signup3_confirm_password.setError("The confirm password conformation does not match!");
                return;
            }



            navController.navigate(R.id.signUp4_Fragment);

        }
    }
}
