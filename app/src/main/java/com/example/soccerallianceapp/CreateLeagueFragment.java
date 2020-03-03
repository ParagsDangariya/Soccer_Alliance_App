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
import android.webkit.MimeTypeMap;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.bumptech.glide.Glide;
import com.example.soccer_alliance_project_test.R;
import com.example.soccerallianceapp.pojo.League;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CreateLeagueFragment extends Fragment implements View.OnClickListener{
    private ImageView league_img;

    private TextInputEditText edt_league_name,edit_no_of_teams;
    private static final int GALLERY_REQUEST_CODE = 108;
    String leaguename,imageUri ="nophoto",uid="";
    int noofteams = 0;
    public NavController navController;
    private Context context;
    private StorageReference mStorageRef;
    FirebaseAuth fAuth;
    MaterialButton create_league_btn;
    Getdataservice service;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_create_league, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        context = getActivity().getApplicationContext();
        navController = Navigation.findNavController(getActivity(),R.id.dashboard_host_fragment);

        //league_name = view.findViewById(R.id.league_name_edt_txt);
        //no_of_leagues = view.findViewById(R.id.number_team_edt_txt);
        edt_league_name = view.findViewById(R.id.league_name_edt_txt);
        edit_no_of_teams = view.findViewById(R.id.number_team_edt_txt);
        create_league_btn = view.findViewById(R.id.create_league_btn);
        create_league_btn.setOnClickListener(this);

        league_img = view.findViewById(R.id.tmp_league_image);
        league_img.setOnClickListener(this);

        uid = fAuth.getInstance().getCurrentUser().getUid();
//        uid = fAuth.getCurrentUser().getUid();
        mStorageRef = FirebaseStorage.getInstance().getReference();

        service = RetroFitInstance.getRetrofitInstance().create(Getdataservice.class);
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

                        Glide.with(context).load(imageUri).into(league_img);

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
    public void onClick(View v) {
        if(v == league_img){

            Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(gallery, GALLERY_REQUEST_CODE);
        }

        if(v == create_league_btn){




            if (TextUtils.isEmpty(leaguename)) {
                edt_league_name.setError("League name is Required.");
                return;


            }else if(Integer.parseInt(edit_no_of_teams.getEditableText().toString().trim()) == 0){
                edit_no_of_teams.setError("Number of teams is Required.");
                return;

            }
            leaguename = edt_league_name.getEditableText().toString().trim();
            noofteams = Integer.parseInt(edit_no_of_teams.getEditableText().toString().trim());
            System.out.println("gotit"+leaguename+" "+noofteams+" "+imageUri+" "+uid);
            League league = new League(leaguename,imageUri,noofteams,uid);
            createleague(league);
        }
    }

    private void createleague(League league) {

        Call<League> call = service.createLeague(league);
        call.enqueue(new Callback<League>() {
            @Override
            public void onResponse(Call<League> call, Response<League> response) {
                if(!response.isSuccessful()){
                    int s = response.code();
                    System.out.println("code"+s);
                }

                Toast.makeText(context," Successfully added..",Toast.LENGTH_LONG).show();

                int s = response.code();

                System.out.println("code"+s);
            }

            @Override
            public void onFailure(Call<League> call, Throwable t) {

                System.out.println("error"+t.getMessage());
                Toast.makeText(context," no more hopes....",Toast.LENGTH_LONG).show();
            }
        });
    }
}