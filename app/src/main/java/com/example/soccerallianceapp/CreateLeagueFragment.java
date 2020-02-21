package com.example.soccerallianceapp;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.provider.SyncStateContract;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.textclassifier.TextSelection;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.android.volley.toolbox.StringRequest;
import com.example.soccer_alliance_project_test.R;
import com.google.android.gms.common.internal.Constants;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class CreateLeagueFragment extends Fragment implements View.OnClickListener, TextView.OnEditorActionListener{
    private ImageView league_img;
    private TextInputLayout league_name,no_of_leagues;
    private TextInputEditText edt_league_name,edit_no_of_teams;
    private Button create_league_btn;
    public NavController navController;
    private Context context;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_create_league, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        context = getActivity().getApplicationContext();
        navController = Navigation.findNavController(getActivity(),R.id.host_fragment);
        
        league_name = view.findViewById(R.id.league_name_layout);
        no_of_leagues = view.findViewById(R.id.number_team_layout);
        edt_league_name = view.findViewById(R.id.league_name_edt_txt);
        edit_no_of_teams = view.findViewById(R.id.number_team_edt_txt);
        create_league_btn = view.findViewById(R.id.tmp_update_btn);

        create_league_btn.setOnClickListener(this);
        edt_league_name.setOnEditorActionListener(this);
        edit_no_of_teams.setOnEditorActionListener(this);

       // Constants.HideErrorOnTyping(league_name);
       // Constants.HideErrorOnTyping(no_of_leagues);
    }
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }


    @Override
    public void onClick(View v) {

    }

    @Override
    public boolean onEditorAction(TextView txt_view, int i, KeyEvent event) {
        if(i == EditorInfo.IME_ACTION_NEXT){
            if(league_name.hasFocus()) {
                league_name.clearFocus();
            }
        }
        else if(i == EditorInfo.IME_ACTION_DONE){
            if (no_of_leagues.hasFocus()){
                no_of_leagues.clearFocus();
            }
        }
        return false;
    }
    public void setText(View v){
StringRequest stringRequest = new StringRequest(Request.Method.GET,"http://localhost:8080/rest/api/createLeague",new Response.Listener<String>(){

    @Override
    public void onResponse(String response) {
        try {
            JSONObject jsonObject = new JSONObject(response);
            String success = jsonObject.getString("Success");

            if (success.equals("1")) {
                                        edt_league_name.setText("");
                                        edit_no_of_teams.setText("");
                                        Toast.makeText(context, "New League Created!!!", Toast.LENGTH_SHORT).show();
                                    }
                                    else
                                    {
                                         Toast.makeText(context,"There Is some problem!!",Toast.LENGTH_LONG).show();
                                    }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    },new Response.ErrorListener(){

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(context, "error:" + error.getMessage(), Toast.LENGTH_LONG).show();
    }
})
{
    protected Map<String, String> getParams() throws AuthFailureError {
        Map<String, String> params = new HashMap<>();
        params.put("League_name", edt_league_name.getText().toString());
        params.put("Number Of Teams", edit_no_of_teams.getText().toString());
        return params;
    }

    };
RequestQueue queue = Volley.newRequestQueue(context);
queue.add(stringRequest);


}

}






