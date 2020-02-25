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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//import com.example.soccerallianceapp.pojo.viewregisteruserdetail.User;


public class SignUp3_Fragment extends Fragment implements View.OnClickListener{


    public NavController navController;
    private Context context;

    Getdataservice service;
    //RequestQueue mqueue;
    ImageButton signup3_next_btn;
    FirebaseAuth fAuth;
    //VollyGetMethod volly;

    String uid ="";
    TextInputEditText signup3_new_password,signup3_confirm_password;

    String email,name,gender,country,user_type,password,phone;
    //int phone;
    int age;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            email = getArguments().getString("email");
            phone = getArguments().getString("Phone");
            user_type = getArguments().getString("user-type");
            name = getArguments().getString("name");
            gender = getArguments().getString("gender");
            country = getArguments().getString("country");
            age = getArguments().getInt("age");
        }
        System.out.println("phone3"+phone);
        System.out.println("s3"+gender);

    }
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


        fAuth = FirebaseAuth.getInstance();



        service = RetroFitInstance.getRetrofitInstance().create(Getdataservice.class);

        System.out.println("email"+email);

    }

    @Override
    public void onClick(View view) {
        if(view == signup3_next_btn){

            password = signup3_new_password.getEditableText().toString().trim();
            String confirmpassword = signup3_confirm_password.getEditableText().toString().trim();
            if(TextUtils.isEmpty(password)){
                signup3_new_password.setError("Password is Required.");
                return ;
            }else if(password.length() < 6){
                signup3_new_password.setError("Password Must be more than 6 Characters");
                return;
            }else if(TextUtils.isEmpty(confirmpassword)) {
                signup3_confirm_password.setError("The confirm password is Required.");
                return;
            }else if(!password.equals(confirmpassword)){
                    signup3_confirm_password.setError("The confirm password conformation does not match!");
                    return;
                }


            //System.out.println("Pass"+password);


            //System.out.println("Pass"+confirmpassword);

            fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                       // Toast.makeText(getContext(), "User Created.", Toast.LENGTH_SHORT).show();

                        uid =fAuth.getCurrentUser().getUid();


                        System.out.println("fire"+gender);
                        String url = "https://soccerallianceapp.appspot.com/rest/api/registerUser&"+uid+"&"+name+"&"+email+"&"+phone+"&"+gender+"&"+country+"&"+age+"&"+user_type+"&nophoto";

                        String user_photo = "nophone";


                        System.out.println("url"+url);

                        User user = new User(uid,name,email,phone,gender,country,age,user_type,user_photo);


                        Call<User> call = service.registerUser(user);

                        call.enqueue(new Callback<User>() {
                            @Override
                            public void onResponse(Call<User> call, Response<User> response) {
                                if(!response.isSuccessful()){
                                    int s = response.code();
                                    System.out.println("code"+s);
                                    //Toast.makeText(context,"succesfully created...."+s,Toast.LENGTH_LONG).show();


                                }


                                int s = response.code();
                                Toast.makeText(context,"succesfully created...."+s,Toast.LENGTH_LONG).show();
                            }


                            @Override
                            public void onFailure(Call<User> call, Throwable t) {

                                System.out.println("error"+t.getMessage());
                                Toast.makeText(context," no more hopes....",Toast.LENGTH_LONG).show();

                            }
                        });

                        /*

                        call.enqueue(new Callback<User>() {
                            @Override
                            public void onResponse(Call<User> call, Response<User> response) {

                                if(!response.isSuccessful()){
                                    int s = response.code();
                                    System.out.println("code"+s);
                                    //Toast.makeText(context,"succesfully created...."+s,Toast.LENGTH_LONG).show();


                                }


                                int s = response.code();
                                Toast.makeText(context,"succesfully created...."+s,Toast.LENGTH_LONG).show();
                            }
                            @Override
                            public void onFailure(Call<ResponseBody> call, Throwable t) {

                                System.out.println("error"+t.getMessage());
                                Toast.makeText(context," no more hopes....",Toast.LENGTH_LONG).show();

                            }
                        });



*/



                       /* JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                                new Response.Listener<JSONObject>() {
                                    @Override
                                    public void onResponse(JSONObject response) {
                                        try {
                                            //JSONArray jsonArray = response.getJSONArray("UserDetails");

                                            if((response.getInt("Status"))==200){
                                                Toast.makeText(context,"all good to go",Toast.LENGTH_LONG).show();

                                            }

                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(context,"getting error"+error.getMessage(),Toast.LENGTH_LONG).show();

                                error.printStackTrace();
                            }
                        });

                        RequestHandler.getInstance(context).addToRequestQueue(request);




                        */

                       //String response = volly.volleyget(url,context);

                        navController.navigate(R.id.signUp4_Fragment);


                    }else{
                        Toast.makeText(getContext(), "Error ! " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        System.out.println(task.getException().getMessage());

                        navController.navigate(R.id.signUp1_Fragment);
                    }
                }
            });




        }
    }
}
