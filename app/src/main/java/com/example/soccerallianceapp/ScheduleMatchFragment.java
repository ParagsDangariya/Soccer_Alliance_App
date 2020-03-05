package com.example.soccerallianceapp;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;

import com.example.soccer_alliance_project_test.R;
import com.example.soccerallianceapp.pojo.CreateSchedule.ScheduleMatch;
import com.example.soccerallianceapp.pojo.viewTeamListFromLeagueId.TeamList;
import com.example.soccerallianceapp.pojo.viewTeamListFromLeagueId.ViewTeamListFromLeagueId;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
//import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.R.layout.simple_selectable_list_item;

public class ScheduleMatchFragment extends Fragment implements View.OnClickListener, TimePickerDialog.OnTimeSetListener {

    public NavController DashboardNavController;
    private Context context;
    private ArrayList<Comman_Data_List> comman_data_List;
    private Comman_adapter comman_adapter;
    FirebaseAuth fAuth;
    Getdataservice service;
    MaterialButton Schedule_match_btn;

    Integer League_id = 1;
    String uid = "";
    int day, month, year;

    private AutoCompleteTextView schedule_match_edt_txt, schedule_match_team2_edt_txt;
    TextInputEditText schedule_match_date_edt_txt, schedule_match_time_layout_edt_txt, schedule_match_location_layout_edt_txt;

    String team2;
    String date;
    String time;
    String location;

    int team1id, team2id;
    int league_id = 1;
    String stime;
    String team1name = "", team2name = "";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        context = getActivity().getApplicationContext();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_schedule_match, container, false);

    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        fAuth = FirebaseAuth.getInstance();
        comman_data_List = new ArrayList<Comman_Data_List>();
        comman_adapter = new Comman_adapter(comman_data_List, context);

        schedule_match_edt_txt = (AutoCompleteTextView) view.findViewById(R.id.schedule_match_edt_txt);
        schedule_match_team2_edt_txt = (AutoCompleteTextView) view.findViewById(R.id.schedule_match_team2_edt_txt);
        schedule_match_date_edt_txt = view.findViewById(R.id.schedule_match_date_edt_txt);
        schedule_match_time_layout_edt_txt = view.findViewById(R.id.schedule_match_time_layout_edt_txt);
        schedule_match_location_layout_edt_txt = view.findViewById(R.id.schedule_match_location_layout_edt_txt);

        Schedule_match_btn = view.findViewById(R.id.Schedule_match_btn);
        Schedule_match_btn.setOnClickListener(this);

        // get Current Date
        Calendar calendar = Calendar.getInstance();

        day = calendar.get(Calendar.DAY_OF_MONTH);
        month = calendar.get(Calendar.MONTH);
        year = calendar.get(Calendar.YEAR);

//
//                schedule_match_date_edt_txt.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//
//                        DatePickerDialog dialog = DatePickerDialog.newInstance(new DatePickerDialog.OnDateSetListener() {
//                            @Override
//                            public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
//
//                                String strdate = year + "-" + (monthOfYear+1) + "-" + dayOfMonth;
//                                  schedule_match_date_edt_txt.setText(strdate);
//
//                            }
//                        },year,month,day) ;
//
//                             dialog.show();
//
//
//
//
//                    }
//                });




        schedule_match_time_layout_edt_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DialogFragment timepicker = new TimePickerFragment();

                timepicker.show(getActivity().getSupportFragmentManager(), "time Picker");


            }
        });


        uid = fAuth.getCurrentUser().getUid();
        Toast.makeText(getActivity(), "UID : " + uid, Toast.LENGTH_LONG).show();
        System.out.println("User Id : " + uid);


        service = RetroFitInstance.getRetrofitInstance().create(Getdataservice.class);


        // Call<ScheduleMatch> scheduleMatchCall = service.scheduleMatch(scdulematch);


        Log.d("step1", "after getService part");

        //League_id = getArguments().getInt("League_id");

//        Toast.makeText(context, "Schedule MAtch Fragment (League Id ): " + League_id, Toast.LENGTH_LONG).show();


        Call<ViewTeamListFromLeagueId> call = service.viewTeamListFromLeagueId(League_id);

        System.out.println(call);

        call.enqueue(new Callback<ViewTeamListFromLeagueId>() {
            @Override
            public void onResponse(Call<ViewTeamListFromLeagueId> call, Response<ViewTeamListFromLeagueId> response) {

                Log.d("Response : ", " " + response);

                ViewTeamListFromLeagueId maindata = response.body();

                System.out.println("Maindata" + maindata);


                if (response.body() != null) {

                    //   List<TeamList> teamLists = response.body().getTeamList();

                    for (TeamList teamList : maindata.getTeamList()) {
                        comman_data_List.add(new Comman_Data_List(


                                teamList.getTeamid(),
                                teamList.getTeamName(),
                                teamList.getLogo()

                        ));

                    }


                    String[] listofteam = new String[comman_data_List.size()];


                    for (int i = 0; i < comman_data_List.size(); i++) {
                        listofteam[i] = comman_data_List.get(i).getItem_name();


                        System.out.print("teamlists." + listofteam[i]);
                    }


                    ArrayAdapter<String> teamadapter = new ArrayAdapter<>(context,
                            simple_selectable_list_item, listofteam);


                    schedule_match_edt_txt.setAdapter(teamadapter);


                    ArrayAdapter<String> teamadapter2 = new ArrayAdapter<>(context,
                            simple_selectable_list_item, listofteam);

                    schedule_match_team2_edt_txt.setAdapter(teamadapter2);

                    schedule_match_edt_txt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View arg1, int position, long id) {

                            Object item = parent.getItemIdAtPosition(position);

                            System.out.println("Team1 id (Selected from Dropdown) : " + item);


                        }
                    });

                    schedule_match_team2_edt_txt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int p, long id) {

                            team2id = comman_data_List.get(p).getIteam_id();

                            System.out.println("Team2 id SM 2 : " + team2id);


                        }
                    });
                } else {
                    Toast.makeText(getActivity(), "Response empty", Toast.LENGTH_LONG).show();

                }

            }

            @Override
            public void onFailure(Call<ViewTeamListFromLeagueId> call, Throwable t) {

            }
        });


    }


    @Override
    public void onClick(View view) {

        if (view == Schedule_match_btn) {


            date = schedule_match_date_edt_txt.getEditableText().toString();

            location = schedule_match_location_layout_edt_txt.getEditableText().toString();

            time = schedule_match_time_layout_edt_txt.getEditableText().toString();


        }


        //league_id = getArguments().getInt("League_id");

        Getdataservice service1 = RetroFitInstance.getRetrofitInstance().create(Getdataservice.class);


        String url = "https://soccerallianceapp.appspot.com/rest/api/CreateSchedule&" + location + "&" + date + "&" + time + "&" + team1id + "&" + team2id + "&" + league_id + "";


        System.out.println("url" + url);

        ScheduleMatch schedulematch = new ScheduleMatch(location, date, time, team1id, team2id, league_id);


        System.out.println("Team1 id (Selected from Dropdown) : " + team1id);

        System.out.println("Team2 id (Selected from Dropdown) : " + team2id);

        System.out.println("Schedulematch : " + schedulematch.toString());
        try {

            Call<ScheduleMatch> data = service1.SCHEDULE_MATCH_CALL(schedulematch);

            System.out.println("data : " + data);

            data.enqueue(new Callback<ScheduleMatch>() {
                @Override
                public void onResponse(Call<ScheduleMatch> data, Response<ScheduleMatch> response) {

                    if (!response.isSuccessful()) {
                        int sm = response.code();
                        System.out.println("code" + sm);
                        Toast.makeText(context, "Schedule Match Successfully " + sm, Toast.LENGTH_LONG).show();


                    }

                    int sm = response.code();
                    System.out.println("code" + sm);


                    Toast.makeText(context, "Schedule Match Successfully " + sm, Toast.LENGTH_LONG).show();


                    //DashboardNavController.navigate(R.id.leagueOperationsFragment);

                }


                @Override
                public void onFailure(Call<ScheduleMatch> data, Throwable t) {


                    System.out.println("error" + t.getMessage());
                    Toast.makeText(context, "Wrong thing happened", Toast.LENGTH_LONG).show();

                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {


        stime = hourOfDay + ":" + minute;

        schedule_match_time_layout_edt_txt.setText("H : " + hourOfDay + "M: " + minute);

        System.out.println("Ontime set : " + schedule_match_date_edt_txt);

    }


}

