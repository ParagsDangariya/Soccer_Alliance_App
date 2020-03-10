package com.example.soccerallianceapp;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;

import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.soccer_alliance_project_test.R;
import com.example.soccerallianceapp.pojo.Player;
import com.example.soccerallianceapp.pojo.PlayerDetail.PlayerDetail;
import com.example.soccerallianceapp.pojo.PlayerDetail.PlayerDetails;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.Date;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class EditTeamFragment extends Fragment implements View.OnClickListener{

    public NavController DashboardNavController;
    MaterialButton update_new_player_btn,remove_player_btn;
    TextView edit_player_name_edt_txt,edit_player_role_edt_txt,edit_player_strength_edt_txt;
    String name,role,strength,imageUri ="nophoto",uid="";
    private Context context;
    Getdataservice service;
    int player_id,team_id;
    FirebaseAuth fAuth;
    private StorageReference mStorageRef;
    PlayerDetails playerDetails;
    ImageView edit_player_user_image;
    private static final int GALLERY_REQUEST_CODE = 104;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            player_id = Integer.parseInt(getArguments().getString("player_id"));
            team_id = getArguments().getInt("team_id");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_edit_team, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        DashboardNavController = Navigation.findNavController(getActivity(),R.id.dashboard_host_fragment);
        context = getActivity().getApplicationContext();

        service = RetroFitInstance.getRetrofitInstance().create(Getdataservice.class);

        update_new_player_btn = view.findViewById(R.id.update_new_player_btn);
        update_new_player_btn.setOnClickListener(this);

        remove_player_btn = view.findViewById(R.id.remove_player_btn);
        remove_player_btn.setOnClickListener(this);

        edit_player_name_edt_txt = view.findViewById(R.id.edit_player_name_edt_txt);
        edit_player_role_edt_txt = view.findViewById(R.id.edit_player_role_edt_txt);
        edit_player_strength_edt_txt = view.findViewById(R.id.edit_player_strength_edt_txt);
        edit_player_user_image = view.findViewById(R.id.edit_player_user_image);
        edit_player_user_image.setOnClickListener(this);

        mStorageRef = FirebaseStorage.getInstance().getReference();


        fAuth = FirebaseAuth.getInstance();

        uid =fAuth.getCurrentUser().getUid();

        service = RetroFitInstance.getRetrofitInstance().create(Getdataservice.class);


        Call<PlayerDetail> call = service.PlayerDetail(player_id);

        call.enqueue(new Callback<PlayerDetail>() {
            @Override
            public void onResponse(Call<PlayerDetail> call, Response<PlayerDetail> response) {
               PlayerDetail playerDetail = response.body();

               playerDetails = playerDetail.getPlayerDetails();

               name = playerDetails.getFullName();
               role = playerDetails.getPosition();
               imageUri = playerDetails.getPlayerPhoto();
               strength = playerDetails.getStrength();

                edit_player_name_edt_txt.setText(name);
                edit_player_role_edt_txt.setText(role);
                edit_player_strength_edt_txt.setText(strength);

                if(!(TextUtils.equals("nophoto",imageUri))){
                    Glide.with(context).load(imageUri).centerCrop().into(edit_player_user_image);
                }
            }

            @Override
            public void onFailure(Call<PlayerDetail> call, Throwable t) {

            }
        });
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

                        Glide.with(context).load(imageUri).centerCrop().into(edit_player_user_image);

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
        if(v == remove_player_btn){

            Call<ResponseBody> call = service.removePlayerFromTeam(player_id,team_id);
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if(!response.isSuccessful()){
                        int s = response.code();
                        System.out.println("code"+s);
                        Toast.makeText(context," error in response..",Toast.LENGTH_LONG).show();

                    }
                    Toast.makeText(context," Successfully Player removed..",Toast.LENGTH_LONG).show();

                    int s = response.code();

                    System.out.println("code"+s);
                    Bundle bundleeditplayer = new Bundle();
                    bundleeditplayer.putInt("team_id",team_id);
                    bundleeditplayer.putString("Coming_from","EditScreen");
                    //bundleeditplayer.putInt("player_id",player_id);
                    DashboardNavController.navigate(R.id.player_List_Fragment,bundleeditplayer,new NavOptions.Builder()
                            .setPopUpTo(R.id.editTeamFragment,
                                    true).build());
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    System.out.println("error"+t.getMessage());
                    Toast.makeText(context," go in failure....",Toast.LENGTH_LONG).show();

                }
            });
        }

        if(v == update_new_player_btn){

            name = edit_player_name_edt_txt.getEditableText().toString().trim();
            role = edit_player_role_edt_txt.getEditableText().toString().trim();
            strength = edit_player_strength_edt_txt.getEditableText().toString().trim();
            //phone = tmp_phone_edt_txt.getEditableText().toString().trim();

            if (TextUtils.isEmpty(name)) {
                edit_player_name_edt_txt.setError("Player name is Required.");
                return;

            }else if(TextUtils.isEmpty(role)){
                edit_player_role_edt_txt.setError("Role or Position of Player is Required.");
                return;

            }else if(TextUtils.isEmpty(strength)){
                edit_player_strength_edt_txt.setError("Strength is Required.");
                return;

            }

            System.out.println("player name"+name+" role"+role+" strength"+strength+" image"+imageUri+" uid"+uid);
            Player player = new Player(player_id,name,imageUri,role,strength);


            updatePlayer(player,service);
        }
        if(v == edit_player_user_image){

            Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(gallery, GALLERY_REQUEST_CODE);
        }

    }

    private void updatePlayer(Player player, Getdataservice service) {

        Call<Player> call = service.ModifyPlayerDetails(player);

        call.enqueue(new Callback<Player>() {
            @Override
            public void onResponse(Call<Player> call, Response<Player> response) {

                if(!response.isSuccessful()){
                    int s = response.code();
                    System.out.println("code"+s);
                    Toast.makeText(context," error in response..",Toast.LENGTH_LONG).show();

                }
                Toast.makeText(context," Successfully updated..",Toast.LENGTH_LONG).show();

                int s = response.code();

                System.out.println("code"+s);

                Bundle bundleeditplayer = new Bundle();
                bundleeditplayer.putInt("team_id",team_id);
                bundleeditplayer.putString("Coming_from","EditScreen");
                //bundleeditplayer.putInt("player_id",player_id);
                DashboardNavController.navigate(R.id.player_List_Fragment,bundleeditplayer,new NavOptions.Builder()
                        .setPopUpTo(R.id.editTeamFragment,
                                true).build());
            }

            @Override
            public void onFailure(Call<Player> call, Throwable t) {

                System.out.println("error"+t.getMessage());
                Toast.makeText(context," go in failure....",Toast.LENGTH_LONG).show();

            }
        });
    }


}
