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
import com.example.soccerallianceapp.pojo.viewregisteruserdetail.UserDetails;
import com.example.soccerallianceapp.pojo.viewregisteruserdetail.ViewregisterUserDetail;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginFragment extends Fragment implements View.OnClickListener {


    Getdataservice service;
    public NavController navController;
    private Context context;
    MaterialButton guest_login_btn,login_btn;
    TextView register_btn_on_login_page,forget_password_txt;

    String uid ="",user_type;
    TextInputEditText email_edit_txt,password_edit_txt;
    FirebaseUser user;

   UserDetails userDetails;
    //RequestQueue mqueue;
    //VollyGetMethod volly;

    FirebaseAuth fAuth ;

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

        service = RetroFitInstance.getRetrofitInstance().create(Getdataservice.class);

        if(fAuth.getCurrentUser() != null){
            uid = fAuth.getCurrentUser().getUid();
            user = fAuth.getCurrentUser();
            verifyuser(user);


            System.out.println("userdata"+uid);
        }

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
                        verifyuser(user);


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

    private void verifyuser(FirebaseUser user) {

        if(!user.isEmailVerified()){
            Toast.makeText(getContext(), "Verify your Account First.", Toast.LENGTH_LONG).show();
            navController.navigate(R.id.emailNotVerifiedFragment);
        }else {
            Toast.makeText(getContext(), "Logged in Successfully", Toast.LENGTH_SHORT).show();


            uid = user.getUid();
            System.out.println(uid);

            String url = "https://soccerallianceapp.appspot.com/rest/api/ViewregisterUserDetail&" + uid;


            System.out.println("url" + url);


            Call<ViewregisterUserDetail> call = service.ViewregisterUserDetail(uid);

            call.enqueue(new Callback<ViewregisterUserDetail>() {
                @Override
                public void onResponse(Call<ViewregisterUserDetail> call, Response<ViewregisterUserDetail> response) {
                    ViewregisterUserDetail viewregister = response.body();


                    UserDetails userDetails = viewregister.getUserDetails();


                    //user_type = userDetails.getUserType();


                    user_type = userDetails.getUserType();

                    System.out.println("string"+user_type);
                    Toast.makeText(context,"succesfully login."+user_type,Toast.LENGTH_LONG).show();
                    System.out.println("login"+user_type);
                    Intent i = new Intent(context,Dashboard_Activity.class);
                    i.putExtra("user_type",user_type);
                    startActivity(i);
                    getActivity().finish();
                }

                @Override
                public void onFailure(Call<ViewregisterUserDetail> call, Throwable t) {

                    System.out.println("error"+t.getMessage());
                    Toast.makeText(context," no more hopes on log in....",Toast.LENGTH_LONG).show();

                }
            });



            /*
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                JSONArray jsonArray = response.getJSONArray("UserDetails");

                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject employee = jsonArray.getJSONObject(i);

                                    String user_type = employee.getString("user_type");
                                   System.out.println("usertype"+user_type);
                                    //mTextViewResult.append(firstName + ", " + String.valueOf(age) + ", " + mail + "\n\n");
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    error.printStackTrace();
                    System.out.println("error" + error.getMessage());
                }
            });

            mqueue.add(request);




            System.out.println("user" + user_type);

             */



        }
    }

}
