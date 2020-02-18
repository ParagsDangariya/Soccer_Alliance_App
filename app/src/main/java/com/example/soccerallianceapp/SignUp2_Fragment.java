package com.example.soccerallianceapp;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.soccer_alliance_project_test.R;
import com.google.android.material.textfield.TextInputEditText;

import static android.R.layout.simple_list_item_1;


/**
 * A simple {@link Fragment} subclass.
 */
public class SignUp2_Fragment extends Fragment implements View.OnClickListener{


    public NavController navController;
    private Context context;

    ImageButton signup2_next_btn;
    private AutoCompleteTextView signup2_country_edit_text,signup2_gender_edit_text;


    TextInputEditText signup2_name_edit_txt,signup2_age_edit_txt;

    String email,phone,user_type;
    private static final String[] GENDER = new String[] {
             "Male", "Female"
    };


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up2, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(getActivity(),R.id.host_fragment);
        context = getActivity().getApplicationContext();

        signup2_next_btn = view.findViewById(R.id.signup2_next_btn);
        signup2_next_btn.setOnClickListener(this);

        ArrayAdapter<String> countriesadapter = new ArrayAdapter<String>(context,
                simple_list_item_1, Countries_List.country);

        ArrayAdapter<String> genderadapter = new ArrayAdapter<String>(context,
                simple_list_item_1, GENDER);
        signup2_name_edit_txt = view.findViewById(R.id.signup2_name_edit_txt);
        signup2_age_edit_txt = view.findViewById(R.id.signup2_age_edit_txt);
        signup2_country_edit_text = view.findViewById(R.id.signup2_country_edit_text);
        signup2_gender_edit_text = view.findViewById(R.id.signup2_gender_edit_text);


        signup2_country_edit_text.setAdapter(countriesadapter);

        signup2_gender_edit_text.setAdapter(genderadapter);
        email = getArguments().getString("email");
        phone = getArguments().getString("phone");
        user_type = getArguments().getString("user-type");

    }

    @Override
    public void onClick(View view) {
        if(view == signup2_next_btn){

            String name = signup2_name_edit_txt.getEditableText().toString().trim();
            String age = signup2_age_edit_txt.getEditableText().toString().trim();
            String gender = signup2_gender_edit_text.getEditableText().toString().trim();
            String country = signup2_country_edit_text.getEditableText().toString().trim();

            Bundle bundle = new Bundle();

            bundle.putString("email", email);
            bundle.putString("Phone", phone);
            bundle.putString("user-type",user_type);
            bundle.putString("name", name);
            bundle.putString("age", age);
            bundle.putString("gender", gender);
            bundle.putString("country", country);




            navController.navigate(R.id.signUp3_Fragment,bundle);

        }
    }
}
