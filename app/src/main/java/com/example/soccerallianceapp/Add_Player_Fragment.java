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
import com.example.soccerallianceapp.pojo.AddPlayerInTeam.AddPlayerInTeam;
import com.example.soccerallianceapp.pojo.Player;
import com.example.soccerallianceapp.pojo.Team;
import com.example.soccerallianceapp.pojo.listLeagueDashboard.ListLeagueDashboard;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class Add_Player_Fragment extends Fragment implements View.OnClickListener{



    Getdataservice service;
    public NavController navController;
    private Context context;
    MaterialButton add_new_player_btn;
    TextView add_player_name_edt_txt,add_player_role_edt_txt,add_player_strength_edt_txt;
    String name,role,strength,imageUri,uid="",position;

    private static final int GALLERY_REQUEST_CODE = 107;
    ImageView add_player_user_image;
    private StorageReference mStorageRef;

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

        navController = Navigation.findNavController(getActivity(),R.id.dashboard_host_fragment);
        context = getActivity().getApplicationContext();

        add_player_name_edt_txt = view.findViewById(R.id.add_player_name_edt_txt);
        add_player_role_edt_txt = view.findViewById(R.id.add_player_role_edt_txt);
        add_player_strength_edt_txt = view.findViewById(R.id.add_player_strength_edt_txt);
        //add_player_position_edt_txt = view.findViewById(R.id.add_player_position_edt_txt);

        add_new_player_btn = view.findViewById(R.id.add_new_player_btn);
        add_new_player_btn.setOnClickListener(this);

        add_player_user_image = view.findViewById(R.id.add_player_user_image);
        add_player_user_image.setOnClickListener(this);

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

                        Glide.with(context).load(imageUri).into(add_player_user_image);

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
        if( view == add_player_user_image){

            Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(gallery, GALLERY_REQUEST_CODE);

        }

        if(view == add_new_player_btn){

            name = add_player_name_edt_txt.getEditableText().toString().trim();
            role = add_player_role_edt_txt.getEditableText().toString().trim();
            strength = add_player_strength_edt_txt.getEditableText().toString().trim();
            //position = add_player_position_edt_txt.getEditableText().toString().trim();
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
            Player player = new Player(name,imageUri,role,strength,uid);

            addPlayer(player);
        }

    }

    private void addPlayer(Player player) {
        Call<Player> call = service.AddPlayerInTeam(player);

        System.out.println("call pass");
        call.enqueue(new Callback<Player>() {
            @Override
            public void onResponse(Call<Player> call, Response<Player> response) {


                //AddPlayerInTeam addPlayerInTeam = response.body();

                Player player = response.body();
                player.getMessage();
                if(player.getMessage() == null){


                if(!response.isSuccessful()){
                    int s = response.code();
                    System.out.println("code"+s);
                }
                }

                int s = response.code();


                System.out.println("code"+player.getMessage());
            }

            @Override
            public void onFailure(Call<Player> call, Throwable t) {

                System.out.println("error"+t.getMessage());
                Toast.makeText(context," no more hopes....",Toast.LENGTH_LONG).show();

            }
        });
    }
}
