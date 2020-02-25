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
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.bumptech.glide.Glide;
import com.example.soccer_alliance_project_test.R;
import com.example.soccerallianceapp.pojo.User;
import com.example.soccerallianceapp.pojo.viewregisteruserdetail.UserDetails;
import com.example.soccerallianceapp.pojo.viewregisteruserdetail.ViewregisterUserDetail;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.R.layout.simple_list_item_1;


/**
 * A simple {@link Fragment} subclass.
 */
public class Team_mngr_my_profile_Fragment extends Fragment implements View.OnClickListener{

    Getdataservice service;
    public NavController navController;
    private Context context;
    private static final int GALLERY_REQUEST_CODE = 105;
    ImageView tmp_user_image;
    String imageUri,email,name,gender,country,user_type,password,phone;
    int age;
    FirebaseAuth fAuth;
    private AutoCompleteTextView tmp_gender_edit_text;
    TextInputEditText tmp_name_edt_txt,tmp_age_edt_txt,tmp_country_edt_txt,tmp_phone_edt_txt;
    String uid ="";
    MaterialButton tmp_update_btn;

    UserDetails userDetails;

    private StorageReference mStorageRef;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

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

        // login_progress = view.findViewById(R.id.login_progress);
        //login_progress.setOnClickListener(this);


        ArrayAdapter<String> genderadapter = new ArrayAdapter<String>(context,
                simple_list_item_1, Countries_List.gender);

        tmp_name_edt_txt = view.findViewById(R.id.tmp_name_edt_txt);
        tmp_age_edt_txt = view.findViewById(R.id.tmp_age_edt_txt);
        tmp_gender_edit_text = view.findViewById(R.id.tmp_gender_edit_text);
        tmp_country_edt_txt = view.findViewById(R.id.tmp_country_edt_txt);
        tmp_phone_edt_txt = view.findViewById(R.id.tmp_phone_edt_txt);

        tmp_gender_edit_text.setAdapter(genderadapter);

        tmp_update_btn = view.findViewById(R.id.tmp_update_btn);
        tmp_update_btn.setOnClickListener(this);




        mStorageRef = FirebaseStorage.getInstance().getReference();


        fAuth = FirebaseAuth.getInstance();

        uid =fAuth.getCurrentUser().getUid();
        System.out.println("uidparag"+uid);
        service = RetroFitInstance.getRetrofitInstance().create(Getdataservice.class);

        Call<ViewregisterUserDetail> call = service.ViewregisterUserDetail(uid);

        call.enqueue(new Callback<ViewregisterUserDetail>() {
            @Override
            public void onResponse(Call<ViewregisterUserDetail> call, Response<ViewregisterUserDetail> response) {
                ViewregisterUserDetail viewregister = response.body();


                userDetails = viewregister.getUserDetails();


                //user_type = userDetails.getUserType();

                name = userDetails.getFullName();
                System.out.println("uidparag"+name);
                email = userDetails.getEmail();
                phone = userDetails.getPhone();
                age = userDetails.getAge();
                gender = userDetails.getGender();
                country = userDetails.getCountry();
                user_type = userDetails.getUserType();
                imageUri = userDetails.getUserPhoto();


                System.out.println("uidparag"+name);
                tmp_name_edt_txt.setText(name);
                tmp_age_edt_txt.setText(String.valueOf(age));
                tmp_gender_edit_text.setText(gender);
                tmp_phone_edt_txt.setText(phone);
                tmp_country_edt_txt.setText(country);

                if(!(TextUtils.equals("nophoto",imageUri))){
                    Glide.with(context).load(imageUri).into(tmp_user_image);
                }


                System.out.println("string"+user_type);
                //Toast.makeText(context,"succesfully login."+user_type,Toast.LENGTH_LONG).show();

            }

            @Override
            public void onFailure(Call<ViewregisterUserDetail> call, Throwable t) {

                System.out.println("error"+t.getMessage());
                Toast.makeText(context," no more hopes on log in....",Toast.LENGTH_LONG).show();

            }
        });



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

                        Glide.with(context).load(imageUri).into(tmp_user_image);

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
        if(view ==tmp_user_image){

            Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(gallery, GALLERY_REQUEST_CODE);
        }
        if(view == tmp_update_btn){

            name = tmp_name_edt_txt.getEditableText().toString().trim();
            gender = tmp_gender_edit_text.getEditableText().toString().trim();
            country = tmp_country_edt_txt.getEditableText().toString().trim();
            phone = tmp_phone_edt_txt.getEditableText().toString().trim();


            if(TextUtils.isEmpty(name)){
                tmp_name_edt_txt.setError("Name is Required.");
                return ;
            }else if(TextUtils.isEmpty(tmp_age_edt_txt.getEditableText().toString().trim())){
                tmp_age_edt_txt.setError("Age is Required.");
                return ;
            }else if(TextUtils.isEmpty(gender)){
                tmp_gender_edit_text.setError("Gender is Required.");
                return ;
            }else if(TextUtils.isEmpty(country)){
                tmp_country_edt_txt.setError("Country is Required.");
                return ;
            }else if(TextUtils.isEmpty(tmp_phone_edt_txt.getEditableText().toString().trim())){
                tmp_phone_edt_txt.setError("The Phone number is Required.");
                return;
            }

            else {
                if (!isValid(tmp_phone_edt_txt.getEditableText().toString().trim())) {
                    tmp_phone_edt_txt.setError("The Phone number is NOT valid!");
                    return;
                }
                /*

                if ((signup_phone_edit_txt.getEditableText().toString().length())<9 &&(signup_phone_edit_txt.getEditableText().toString().length())>11 ) {
                    signup_phone_edit_txt.setError("The Phone number does not have valid number input.");
                    return;

                }

                 */

            }

            System.out.println("phone2"+phone);
            age = Integer.parseInt(tmp_age_edt_txt.getEditableText().toString().trim());

            User user = new User(uid,name,email,phone,gender,country,age,user_type,imageUri);


            Call<User> call = service.UpdateUserProfile(user);

            call.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    if(!response.isSuccessful()){
                        int s = response.code();
                        System.out.println("code"+s);
                        //Toast.makeText(context,"succesfully created...."+s,Toast.LENGTH_LONG).show();


                    }


                    int s = response.code();
                    Toast.makeText(context,"succesfully updated...."+s,Toast.LENGTH_LONG).show();
                }


                @Override
                public void onFailure(Call<User> call, Throwable t) {

                    System.out.println("error"+t.getMessage());
                    Toast.makeText(context," no more hopes....",Toast.LENGTH_LONG).show();

                }
            });

        }
    }
    public static boolean isValid(String s)
    {
        // The given argument to compile() method
        // is regular expression. With the help of
        // regular expression we can validate mobile
        // number.
        // 1) Begins with 0 or 91
        // 2) Then contains 7 or 8 or 9.
        // 3) Then contains 9 digits
        Pattern p = Pattern.compile("^[0-9]*$");

        // Pattern class contains matcher() method
        // to find matching between given number
        // and regular expression
        //s=s.replaceAll("[\\-\\+]", "");
        System.out.println("mobile"+s);
        Matcher m = p.matcher(s);
        return (m.find() && m.group().equals(s));
    }
}