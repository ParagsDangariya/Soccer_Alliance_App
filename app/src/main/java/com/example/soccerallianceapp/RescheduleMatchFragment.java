package com.example.soccerallianceapp;

import android.app.TimePickerDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.soccer_alliance_project_test.R;
import com.example.soccerallianceapp.pojo.CreateSchedule.ScheduleMatch;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.RadialPickerLayout;

import java.util.ArrayList;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RescheduleMatchFragment extends Fragment implements TimePickerDialog.OnTimeSetListener {

    public NavController DashboardNavController;
    private Context context;
    private ArrayList<Comman_Data_List> comman_data_List;
    private Comman_adapter comman_adapter;
    FirebaseAuth fAuth;
    Getdataservice service;
    MaterialButton reSchedule_match_btn;

    TextInputEditText reschedule_match_date_edt_txt,reschedule_match_time_layout_edt_txt,reschedule_match_location_layout_edt_txt;

    int team1id = 1,team2id = 17,leagueid = 1, scheduleid = 10 ;

    String date;
    String time;
    String location;

    int day, month, year;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_reschedule_match, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        context = getActivity().getApplicationContext();

        reschedule_match_date_edt_txt = view.findViewById(R.id.reschedule_match_date_edt_txt);
        reschedule_match_time_layout_edt_txt = view.findViewById(R.id.reschedule_match_time_layout_edt_txt);
        reschedule_match_location_layout_edt_txt = view.findViewById(R.id.reschedule_match_location_layout_edt_txt);


        reSchedule_match_btn = view.findViewById(R.id.reSchedule_match_btn);


        // get Current Date
        Calendar calendar = Calendar.getInstance();

        day = calendar.get(Calendar.DAY_OF_MONTH);
        month = calendar.get(Calendar.MONTH);
        year = calendar.get(Calendar.YEAR);


        reschedule_match_date_edt_txt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        DatePickerDialog dialog = DatePickerDialog.newInstance(new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {

                                String strdate = year + "-" + (monthOfYear+1) + "-" + dayOfMonth;
                                reschedule_match_date_edt_txt.setText(strdate);

                            }
                        },year,month,day) ;

                             dialog.show(getActivity().getFragmentManager(),"DatepickerDialog");

                    }
                });

        reschedule_match_time_layout_edt_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DialogFragment timepicker = new TimePickerFragment();

                timepicker.show(getActivity().getSupportFragmentManager(), "time Picker");


            }
        });

        service = RetroFitInstance.getRetrofitInstance().create(Getdataservice.class);
        Log.d("step1", "after getService part");


        reSchedule_match_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                date = reschedule_match_date_edt_txt.getText().toString();
                location = reschedule_match_location_layout_edt_txt.getEditableText().toString();
                time = reschedule_match_time_layout_edt_txt.getEditableText().toString();


                String url = "https://soccerallianceapp.appspot.com/rest/api/ReSchedule&" + location + "&" + date + "&" + time + "&" + team1id + "&" + team2id + "&" + leagueid + "&" + scheduleid + "";

                System.out.println("url" + url);

                ScheduleMatch reschedulematch = new ScheduleMatch(location,date,time,team1id,team2id,leagueid,scheduleid);

                System.out.println("Schedulematch : " + reschedulematch.toString());
                try{

                    Call<ScheduleMatch> call = service.Reschedule_match_call(reschedulematch);

                    System.out.println("data : " + call);

                    call.enqueue(new Callback<ScheduleMatch>() {
                        @Override
                        public void onResponse(Call<ScheduleMatch> call, Response<ScheduleMatch> response) {

                            if (!response.isSuccessful()) {
                                int sm = response.code();
                                System.out.println("code" + sm);
                                Toast.makeText(context, "ReSchedule Match not Successfully " + sm, Toast.LENGTH_LONG).show();


                            }

                            int sm = response.code();
                            System.out.println("code" + sm);
                            Toast.makeText(context, "ReSchedule Match Successfully done " + sm, Toast.LENGTH_LONG).show();


                        }

                        @Override
                        public void onFailure(Call<ScheduleMatch> call, Throwable t) {

                            System.out.println("error" + t.getMessage());
                            Toast.makeText(context, "Wrong thing happened", Toast.LENGTH_LONG).show();


                        }
                    });



                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        });

    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {


        // stime = hourOfDay + ":" + minute;

        reschedule_match_time_layout_edt_txt.setText("H : " + hourOfDay + "M: " + minute);

        System.out.println("Ontime set : " + reschedule_match_time_layout_edt_txt);

    }


}
