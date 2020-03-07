package com.example.soccerallianceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.example.soccer_alliance_project_test.R;
import com.example.soccerallianceapp.pojo.viewregisteruserdetail.UserDetails;
import com.example.soccerallianceapp.pojo.viewregisteruserdetail.ViewregisterUserDetail;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashScreen extends AppCompatActivity {


    String TAG= "splash screen";
    FirebaseAuth fAuth ;
    FirebaseUser user;
    Getdataservice service;
    String uid ="",user_type,name,imageUri;

    @Override
    protected void onStart() {
        super.onStart();
        fAuth = FirebaseAuth.getInstance();

        service = RetroFitInstance.getRetrofitInstance().create(Getdataservice.class);
/*
        if(fAuth.getCurrentUser() != null){
            uid = fAuth.getCurrentUser().getUid();
            user = fAuth.getCurrentUser();
            verifyuser(user);


            System.out.println("userdata"+uid);
        }

 */

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Log.i(TAG, "onCreate: ");
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent i = new Intent(SplashScreen.this,MainActivity.class);
                startActivity(i);
                finish();
            }
        },3000);



    }

    private void verifyuser(FirebaseUser user) {

        if(!user.isEmailVerified()){
            Toast.makeText(getApplicationContext(), "Verify your Account First.", Toast.LENGTH_LONG).show();
            //Bundle bundle = new Bundle();
            //bundle.putInt("verify",verifynumber);

            Intent i = new Intent(SplashScreen.this,MainActivity.class);
            startActivity(i);
            finish();

        }else {
            Toast.makeText(getApplicationContext(), "Logged in Successfully", Toast.LENGTH_SHORT).show();


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



                    name = userDetails.getFullName();
                    imageUri = userDetails.getUserPhoto();
                    user_type = userDetails.getUserType();

                    System.out.println("string"+user_type);
                    //Toast.makeText(getApplicationContext(),"succesfully login."+user_type,Toast.LENGTH_LONG).show();
                    System.out.println("login"+user_type);
                    Intent i = new Intent(SplashScreen.this,Dashboard_Activity.class);
                    i.putExtra("user_type",user_type);
                    i.putExtra("name",name);
                    i.putExtra("imageUri",imageUri);
                    startActivity(i);
                    finish();
                }

                @Override
                public void onFailure(Call<ViewregisterUserDetail> call, Throwable t) {

                    System.out.println("error"+t.getMessage());
                    Toast.makeText(getApplicationContext()," no more hopes on log in....",Toast.LENGTH_LONG).show();

                }
            });





        }
    }

}
