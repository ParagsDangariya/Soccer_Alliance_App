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
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.soccer_alliance_project_test.R;
import com.google.android.material.textfield.TextInputEditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * A simple {@link Fragment} subclass.
 * Sign up part-1 has Email and phone number field and decide user type.
 *
 */
public class SignUp1_Fragment extends Fragment implements View.OnClickListener {

    public NavController navController;
    private Context context;

    ConstraintLayout signup_team_manager_btn_type,signup_league_manager_btn_type;
    String user_type = "",phone;
    ImageButton signup1_next_btn;
    //public static final String PHONE_VERIFICATION = "^[+0-9-\\(\\)\\s]*{6,14}$";


    private static Pattern p;
    private static Matcher m;
    String email ="";
    boolean isPhoneValid;


    TextInputEditText signup_email_edit_txt,signup_phone_edit_txt;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up1, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(getActivity(),R.id.host_fragment);
        context = getActivity().getApplicationContext();
        signup1_next_btn = view.findViewById(R.id.signup1_next_btn);
        signup_league_manager_btn_type = view.findViewById(R.id.signup_league_manager_btn_type);
        signup_team_manager_btn_type = view.findViewById(R.id.signup_team_manager_btn_type);
        signup_league_manager_btn_type.setOnClickListener(this);
        signup_team_manager_btn_type.setOnClickListener(this);
        signup1_next_btn.setOnClickListener(this);

        signup_email_edit_txt = view.findViewById(R.id.signup_email_edit_txt);
        signup_phone_edit_txt = view.findViewById(R.id.signup_phone_edit_txt);

    }

    @Override
    public void onClick(View view) {
        if(view == signup_team_manager_btn_type){
            signup_team_manager_btn_type.setBackground(ContextCompat.getDrawable(context,R.drawable.user_type_selection_border));
            signup_league_manager_btn_type.setBackground(null);
            user_type = "Team_Manager";
        }
        else if(view==signup_league_manager_btn_type){
            signup_league_manager_btn_type.setBackground(ContextCompat.getDrawable(context,R.drawable.user_type_selection_border));
            signup_team_manager_btn_type.setBackground(null);
            user_type = "League_Manager";
        }
        if(view==signup1_next_btn){



            if(TextUtils.isEmpty(signup_email_edit_txt.getEditableText().toString().trim())){
                signup_email_edit_txt.setError("Email is Required.");
                return;
            }else if(TextUtils.isEmpty(signup_phone_edit_txt.getEditableText().toString().trim())){
                signup_phone_edit_txt.setError("The Phone number is Required.");
                return;
            }

            else {
                if (!isValid(signup_phone_edit_txt.getEditableText().toString().trim())) {
                    signup_phone_edit_txt.setError("The Phone number is NOT valid!");
                    return;
                }
                /*

                if ((signup_phone_edit_txt.getEditableText().toString().length())<9 &&(signup_phone_edit_txt.getEditableText().toString().length())>11 ) {
                    signup_phone_edit_txt.setError("The Phone number does not have valid number input.");
                    return;

                }

                 */

            }



            if(TextUtils.isEmpty(user_type)){
                Toast.makeText(context,"Select User-type First for Registration",Toast.LENGTH_SHORT).show();
                return;
            }

            email = signup_email_edit_txt.getEditableText().toString().trim();
            System.out.println("email"+email);

            phone = signup_phone_edit_txt.getEditableText().toString().trim();



            Bundle bundle = new Bundle();
            bundle.putString("email", email);
            bundle.putString("Phone", phone);
            bundle.putString("user-type",user_type);


            navController.navigate(R.id.signUp2_Fragment,bundle);

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
