package com.example.soccerallianceapp;


import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.soccer_alliance_project_test.R;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * A simple {@link Fragment} subclass.
 */
public class Team_mngr_my_profile_Fragment extends Fragment implements View.OnClickListener{


    public NavController navController;
    private Context context;
    private static final int GALLERY_REQUEST_CODE = 105;
    ImageView tmp_user_image;

    public Team_mngr_my_profile_Fragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(getActivity(),R.id.dashboard_host_fragment);
        context = getActivity().getApplicationContext();

        tmp_user_image = view.findViewById(R.id.tmp_user_image);

        tmp_user_image.setOnClickListener(this);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_team_mngr_my_profile_, container, false);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == GALLERY_REQUEST_CODE){
            if(resultCode == Activity.RESULT_OK){
                Uri contentUri = data.getData();
                String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                String imageFileName = "JPEG_" + timeStamp +"."+getFileExt(contentUri);
                Log.d("tag", "onActivityResult: Gallery Image Uri:  " +  imageFileName);
                tmp_user_image.setImageURI(contentUri);

                //uploadImageToFirebase(imageFileName,contentUri);


            }

        }
    }

    private String getFileExt(Uri contentUri) {
        ContentResolver c = context.getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(c.getType(contentUri));
    }


    @Override
    public void onClick(View view) {
        if(view ==tmp_user_image){

            Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(gallery, GALLERY_REQUEST_CODE);
        }
    }
}
