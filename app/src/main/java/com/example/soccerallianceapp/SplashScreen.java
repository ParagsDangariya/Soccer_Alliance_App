package com.example.soccerallianceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
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

    FirebaseAuth fAuth ;
    FirebaseUser user;
    Getdataservice service;
    String uid ="",user_type,name,imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        fAuth = FirebaseAuth.getInstance();

        service = RetroFitInstance.getRetrofitInstance().create(Getdataservice.class);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent i = new Intent(SplashScreen.this,MainActivity.class);
                startActivity(i);
                finish();
            }
        },3000);


        if(fAuth.getCurrentUser() != null){
            uid = fAuth.getCurrentUser().getUid();
            user = fAuth.getCurrentUser();
            verifyuser(user);


            System.out.println("userdata"+uid);
        }
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
                    Toast.makeText(getApplicationContext(),"succesfully login."+user_type,Toast.LENGTH_LONG).show();
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
