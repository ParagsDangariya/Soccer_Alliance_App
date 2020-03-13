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
import androidx.navigation.Navigation;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.bumptech.glide.Glide;
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


public class RescheduleMatchFragment extends Fragment  {

    public NavController DashboardNavController;
    private Context context;
    private ArrayList<Comman_Data_List> comman_data_List;
    private Comman_adapter comman_adapter;
    FirebaseAuth fAuth;
    Getdataservice service;
    MaterialButton reSchedule_match_btn;
    private TimePickerDialog timePickerDialog;

    TextInputEditText reschedule_match_date_edt_txt, reschedule_match_time_layout_edt_txt, reschedule_match_location_layout_edt_txt;

    int league_id, up_match_id, up_team1_id, up_team2_id, up_schedule_id;
    String up_team1name, up_team2name, up_team1icon, up_team2icon, up_match_date;

    ImageView team1_logo, team2_logo;
    TextView team1, team2;

    String date;
    String time;
    String location;

    int day, month, year;
    int currenthour, currentminiute;
    String ampm;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_reschedule_match, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        DashboardNavController = Navigation.findNavController(getActivity(), R.id.dashboard_host_fragment);
        context = getActivity().getApplicationContext();

        team1_logo = view.findViewById(R.id.Reschedule_team1_logo);
        team2_logo = view.findViewById(R.id.Reschedule_team2_logo);
        team1 = view.findViewById(R.id.Reschedule_team1);
        team2 = view.findViewById(R.id.Reschedule_team2);

        reschedule_match_date_edt_txt = view.findViewById(R.id.reschedule_match_date_edt_txt);
        reschedule_match_time_layout_edt_txt = view.findViewById(R.id.reschedule_match_time_layout_edt_txt);
        reschedule_match_location_layout_edt_txt = view.findViewById(R.id.reschedule_match_location_layout_edt_txt);
        reSchedule_match_btn = view.findViewById(R.id.reSchedule_match_btn);

        if (getArguments() != null) {
            if (getArguments().getString("Coming_from").equals("Reschedulematch")) {

                league_id = getArguments().getInt("League_id");
                up_match_id = getArguments().getInt("up_match_id");
                up_team1_id = getArguments().getInt("up_team1_id");
                up_team2_id = getArguments().getInt("up_team2_id");
                up_team1name = getArguments().getString("up_team1name");
                up_team2name = getArguments().getString("up_team2name");
                up_team1icon = getArguments().getString("up_team1logo");
                up_team2icon = getArguments().getString("up_team2logo");
                up_match_date = getArguments().getString("up_match_date");
                up_schedule_id = getArguments().getInt("up_schedule_id");

                Log.d("RESChedule Match", "in if else");

                System.out.println("UP_match id " + up_match_id);


            }
        }


        team1.setText(up_team1name);
        team2.setText(up_team2name);
        Glide.with(context).load(up_team1icon).fitCenter().into(team1_logo);
        Glide.with(context).load(up_team2icon).fitCenter().into(team2_logo);

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

                        String strdate = year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;
                        reschedule_match_date_edt_txt.setText(strdate);

                    }
                }, year, month, day);

                dialog.show(getActivity().getFragmentManager(), "DatepickerDialog");

            }
        });

        reschedule_match_time_layout_edt_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                currenthour = calendar.get(Calendar.HOUR_OF_DAY);
                currentminiute = calendar.get(Calendar.MINUTE);

                timePickerDialog = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        if (hourOfDay >= 12) {
                            ampm = "PM";
                        } else {
                            ampm = "AM";
                        }
                        reschedule_match_time_layout_edt_txt.setText(hourOfDay + ":" + minute + ampm);
                    }
                }, currenthour, currentminiute, false);

                timePickerDialog.show();
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

                if (TextUtils.isEmpty(date)) {
                    reschedule_match_date_edt_txt.setError("Date is Required.");
                    return;
                } else if (TextUtils.isEmpty(time)) {
                    reschedule_match_time_layout_edt_txt.setError("Time is Required.");
                    return;
                } else if (TextUtils.isEmpty(location)) {
                    reschedule_match_location_layout_edt_txt.setError("Location is Required.");
                    return;
                }

                String url = "https://soccerallianceapp.appspot.com/rest/api/ReSchedule&" + location + "&" + date + "&" + time + "&" + up_team1_id + "&" + up_team2_id + "&" + league_id + "&" + up_schedule_id + "";
                System.out.println("url" + url);

                ScheduleMatch reschedulematch = new ScheduleMatch(location, date, time, up_team1_id, up_team2_id, league_id, up_schedule_id);
                System.out.println("Schedulematch : " + reschedulematch.toString());
                try {
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
                            DashboardNavController.navigate(R.id.leagueOperationsFragment);
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
}
