package com.example.soccerallianceapp;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.soccer_alliance_project_test.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class LoginFragment extends Fragment implements View.OnClickListener {


    public NavController navController;
    private Context context;
    MaterialButton guest_login_btn,login_btn;
    TextView register_btn_on_login_page,forget_password_txt;

    TextInputEditText email_edit_txt,password_edit_txt;
    FirebaseUser user;


    String uid="";
    FirebaseAuth fAuth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        getActivity().getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));

        navController = Navigation.findNavController(getActivity(),R.id.host_fragment);
        context = getActivity().getApplicationContext();

        register_btn_on_login_page = view.findViewById(R.id.register_btn_on_login_page);
        register_btn_on_login_page.setOnClickListener(this);

        guest_login_btn = view.findViewById(R.id.guest_login_btn);
        guest_login_btn.setOnClickListener(this);

        forget_password_txt = view.findViewById(R.id.forget_password_txt);
        forget_password_txt.setOnClickListener(this);

        login_btn = view.findViewById(R.id.login_btn);
        login_btn.setOnClickListener(this);


        email_edit_txt = view.findViewById(R.id.email_edit_txt);

        password_edit_txt = view.findViewById(R.id.password_edit_txt);

        fAuth = FirebaseAuth.getInstance();



    }

    @Override
    public void onClick(View view) {







        if(view == login_btn){

            String email = email_edit_txt.getText().toString().trim();
            String password = password_edit_txt.getText().toString().trim();

            if(TextUtils.isEmpty(email)){
                email_edit_txt.setError("Email is Required.");
                return;
            }

            if(TextUtils.isEmpty(password)){
                password_edit_txt.setError("Password is Required.");
                return;
            }else if(password.length() < 6){
                password_edit_txt.setError("Password Must be >= 6 Characters");
                return;
            }

            fAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){

                        //Toast.makeText(getContext(), "Logged in Successfully", Toast.LENGTH_SHORT).show();

                        uid = fAuth.getCurrentUser().getUid();
                         user = fAuth.getCurrentUser();
                        verifieduser(user);


                    }else {

                        Toast.makeText(getContext(), "Error ! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });



        }
        else if(view==register_btn_on_login_page){
            navController.navigate(R.id.signUp1_Fragment);
        }

        
        else if(view == forget_password_txt){
            navController.navigate(R.id.forgot_pass1_Fragment);
        }


        else if(view == guest_login_btn){
            Intent i = new Intent(context,Dashboard_Activity.class);
            startActivity(i);
        }

    }

    private void verifieduser(FirebaseUser user) {

        if(!user.isEmailVerified()){
            Toast.makeText(getContext(), "Verify your Account First.", Toast.LENGTH_LONG).show();
            navController.navigate(R.id.emailNotVerifiedFragment);
        }else {
            Toast.makeText(getContext(), "Logged in Successfully", Toast.LENGTH_SHORT).show();

            Intent i = new Intent(context,Dashboard_Activity.class);
            i.putExtra("user_type","Team_Manager");
            startActivity(i);

        }
    }
}
