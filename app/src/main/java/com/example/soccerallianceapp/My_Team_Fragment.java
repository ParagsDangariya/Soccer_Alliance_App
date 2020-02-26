package com.example.soccerallianceapp;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.MimeTypeMap;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.bumptech.glide.Glide;
import com.example.soccer_alliance_project_test.R;
import com.example.soccerallianceapp.pojo.Team;
import com.example.soccerallianceapp.pojo.User;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class My_Team_Fragment extends Fragment implements View.OnClickListener {

    public NavController navController;
    private Context context;
    Getdataservice service;
    MaterialButton my_team_update_details_btn;
    String name,shorthand,uid,imageUri;
    TextInputEditText my_team_name_edt_txt,my_team_shorthand_edt_txt;
    FirebaseAuth fAuth;
    private static final int GALLERY_REQUEST_CODE = 106;
    private StorageReference mStorageRef;
    ImageView my_team_user_image;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_team, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        getActivity().getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));

        navController = Navigation.findNavController(getActivity(),R.id.dashboard_host_fragment);
        context = getActivity().getApplicationContext();

        service = RetroFitInstance.getRetrofitInstance().create(Getdataservice.class);

        my_team_update_details_btn= view.findViewById(R.id.my_team_update_details_btn);
        my_team_update_details_btn.setOnClickListener(this);

        my_team_name_edt_txt = view.findViewById(R.id.my_team_name_edt_txt);
        my_team_shorthand_edt_txt = view.findViewById(R.id.my_team_shorthand_edt_txt);

        my_team_user_image = view.findViewById(R.id.my_team_user_image);
        my_team_user_image.setOnClickListener(this);


        mStorageRef = FirebaseStorage.getInstance().getReference();
        fAuth = FirebaseAuth.getInstance();
        uid =fAuth.getCurrentUser().getUid();

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == GALLERY_REQUEST_CODE){
            if(resultCode == Activity.RESULT_OK){
                Uri contentUri = data.getData();
                String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                String imageFileName = "JPEG_" + timeStamp +"."+getFileExt(contentUri);
                Log.d("tag", "onActivityResult: Gallery Image Uri:  " +  imageFileName);
                //tmp_user_image.setImageURI(contentUri);

                uploadImageToFirebase(imageFileName,contentUri);


            }

        }
    }

    private void uploadImageToFirebase(String imageFileName, Uri contentUri) {

        final StorageReference image = mStorageRef.child("pictures/" + imageFileName);
        image.putFile(contentUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                image.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Log.d("tag", "onSuccess: Uploaded Image URl is " + uri.toString());
                        imageUri =uri.toString();
                        //Picasso.get().load(imageUri).into(tmp_user_image);

                        Glide.with(context).load(imageUri).into(my_team_user_image);

                    }
                });

                Toast.makeText(context, "Image Is Uploaded.", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(context, "Upload Failled.", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private String getFileExt(Uri contentUri) {

        ContentResolver c = context.getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(c.getType(contentUri));
    }

    @Override
    public void onClick(View view) {
        if(view == my_team_user_image){

            Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(gallery, GALLERY_REQUEST_CODE);
        }


        if(view == my_team_update_details_btn ){
             name = my_team_name_edt_txt.getEditableText().toString().trim();
             shorthand = my_team_shorthand_edt_txt.getEditableText().toString().trim();

            if (TextUtils.isEmpty(name)) {

                my_team_name_edt_txt.setError("Name is Required.");
                return ;
            }else if(TextUtils.isEmpty(shorthand)){

                my_team_shorthand_edt_txt.setError("ShortHand is Required.");
                return ;
            }


            System.out.println("uid on my team"+uid);

            Team team  = new Team(name,imageUri,shorthand,uid);

            Call<Team> call = service.CreateTeam(team);

            System.out.println("call pass");
            call.enqueue(new Callback<Team>() {
                @Override
                public void onResponse(Call<Team> call, Response<Team> response) {
                    if(!response.isSuccessful()){
                        int s = response.code();
                        System.out.println("code"+s);
                        Toast.makeText(context,"succesfully created...."+s,Toast.LENGTH_LONG).show();


                    }

                    System.out.println("code");
                }

                @Override
                public void onFailure(Call<Team> call, Throwable t) {

                    System.out.println("error"+t.getMessage());
                    Toast.makeText(context," no more hopes....",Toast.LENGTH_LONG).show();

                }
            });



        }
    }
}
