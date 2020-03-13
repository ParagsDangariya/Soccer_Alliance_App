package com.example.soccerallianceapp;

import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
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
import androidx.navigation.Navigation;

import com.example.soccer_alliance_project_test.R;
import com.example.soccerallianceapp.pojo.CreateSchedule.ScheduleMatch;
import com.example.soccerallianceapp.pojo.viewTeamListFromLeagueId.TeamList;
import com.example.soccerallianceapp.pojo.viewTeamListFromLeagueId.ViewTeamListFromLeagueId;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
//import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.R.layout.simple_selectable_list_item;

public class ScheduleMatchFragment extends Fragment implements View.OnClickListener {

    public NavController DashboardNavController;
    private Context context;
    private ArrayList<Comman_Data_List> comman_data_List;
    private Comman_adapter comman_adapter;
    FirebaseAuth fAuth;
    Getdataservice service;
    MaterialButton Schedule_match_btn;
    ArrayList<TeamList> testlist;
    private SimpleDateFormat timeformatter;
    private TimePickerDialog timePickerDialog;

    int League_id;
    String uid = "";
    int day, month, year;
    int currenthour,currentminiute;
    String ampm;

    private AutoCompleteTextView schedule_match_edt_txt, schedule_match_team2_edt_txt;
    TextInputEditText schedule_match_date_edt_txt, schedule_match_time_layout_edt_txt, schedule_match_location_layout_edt_txt;

    String team2;
    String date;
    String time;
    String location;
    int team1id, team2id;
    String stime;
    String team1name = "", team2name = "";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_schedule_match, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        DashboardNavController = Navigation.findNavController(getActivity(), R.id.dashboard_host_fragment);
        context = getActivity().getApplicationContext();
        fAuth = FirebaseAuth.getInstance();
        comman_data_List = new ArrayList<Comman_Data_List>();
        comman_adapter = new Comman_adapter(comman_data_List, context);
        try {
            if (getArguments() != null) {
                League_id = getArguments().getInt("League_id");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

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

        schedule_match_date_edt_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dialog = DatePickerDialog.newInstance(new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
                        String strdate = year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;
                        schedule_match_date_edt_txt.setText(strdate);
                    }
                }, year, month, day);
                dialog.show(getActivity().getFragmentManager(), "DatePicketDialog");
            }
        });

        schedule_match_time_layout_edt_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                currenthour =calendar.get(Calendar.HOUR_OF_DAY);
                currentminiute = calendar.get(Calendar.MINUTE);

                timePickerDialog = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        if (hourOfDay >= 12)
                        {
                            ampm = "PM";
                        }
                        else{
                            ampm = "AM";
                        }
                        schedule_match_time_layout_edt_txt.setText(hourOfDay+":"+minute+ampm);
                    }
                },currenthour,currentminiute,false );

                timePickerDialog.show();
            }
        });

        uid = fAuth.getCurrentUser().getUid();
        //Toast.makeText(getActivity(), "UID : " + uid, Toast.LENGTH_LONG).show();
        System.out.println("User Id : " + uid);
        service = RetroFitInstance.getRetrofitInstance().create(Getdataservice.class);
        Log.d("step1", "after getService part (View match list by leagueID)");
        Call<ViewTeamListFromLeagueId> call = service.viewTeamListFromLeagueId(League_id);
        call.enqueue(new Callback<ViewTeamListFromLeagueId>() {
            @Override
            public void onResponse(Call<ViewTeamListFromLeagueId> call, Response<ViewTeamListFromLeagueId> response) {
                Log.d("Response : ", " " + response);
                ViewTeamListFromLeagueId maindata = response.body();
                testlist = new ArrayList<>(maindata.getTeamList());
                System.out.println("TestList :" + testlist);
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
                            Log.i("Test Size", String.valueOf(testlist.size()));
                            getTeamId(testlist);
                        }
                    });

                    schedule_match_team2_edt_txt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int p, long id) {
                            team2id = comman_data_List.get(p).getIteam_id();
                            System.out.println("Team2 id SM 2 : " + team2id);
                            getTeamId(testlist);
                        }
                    });
                } else {
                    Toast.makeText(getActivity(), "Response empty", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ViewTeamListFromLeagueId> call, Throwable t) {
                System.out.println("On Failure in ViewTeamList" + t);
            }
        });
    }

    @Override
    public void onClick(View view) {
        if (view == Schedule_match_btn) {

            team1name = schedule_match_edt_txt.getText().toString();
            team2name = schedule_match_team2_edt_txt.getText().toString();
            date = schedule_match_date_edt_txt.getText().toString();
            location = schedule_match_location_layout_edt_txt.getText().toString();
            time = schedule_match_time_layout_edt_txt.getText().toString();

            if (TextUtils.isEmpty(team1name)) {
                schedule_match_edt_txt.setError("Team 1 is Required.");
                return;
            } else if (TextUtils.isEmpty(team2name)) {
                schedule_match_team2_edt_txt.setError("Team 2 is Required");
                return;
            } else if (TextUtils.isEmpty(date)) {
                schedule_match_date_edt_txt.setError("Date is Required.");
                return;
            } else if (TextUtils.isEmpty(time)) {
                schedule_match_time_layout_edt_txt.setError("Time is Required.");
                return;
            } else if (TextUtils.isEmpty(location)) {
                schedule_match_location_layout_edt_txt.setError("Location is Required.");
                return;
            } else if (team1name.equals(team2name)) {
                schedule_match_team2_edt_txt.setError("Team 1 and Team 2 must be Different!");
                return;
            }
        }

        String url = "https://soccerallianceapp.appspot.com/rest/api/CreateSchedule&" + location + "&" + date + "&" + time + "&" + team1id + "&" + team2id + "&" + League_id + "";
        System.out.println("url" + url);

        ScheduleMatch schedulematch = new ScheduleMatch(location, date, time, team1id, team2id, League_id);

        System.out.println("Schedulematch : " + schedulematch.toString());
        try {
            Call<ScheduleMatch> data = service.SCHEDULE_MATCH_CALL(schedulematch);
            System.out.println("data : " + data);
            data.enqueue(new Callback<ScheduleMatch>() {
                @Override
                public void onResponse(Call<ScheduleMatch> data, Response<ScheduleMatch> response) {
                    if (!response.isSuccessful()) {
                        int sm = response.code();
                        System.out.println("code" + sm);
                        Toast.makeText(context, "Error(in If Else) : " + sm, Toast.LENGTH_LONG).show();
                    }

                    int sm = response.code();
                    System.out.println("code" + sm);
                    Toast.makeText(context, "Schedule Match Successfully (After if) : " + sm, Toast.LENGTH_LONG).show();
                    DashboardNavController.navigate(R.id.leagueOperationsFragment);

                }

                @Override
                public void onFailure(Call<ScheduleMatch> data, Throwable t) {
                    System.out.println("error : " + t.getMessage());
                    Toast.makeText(context, "Wrong thing happened", Toast.LENGTH_LONG).show();
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

  public void getTeamId(ArrayList<TeamList> testlist1) {
        team1name = schedule_match_edt_txt.getText().toString();
        team2name = schedule_match_team2_edt_txt.getText().toString();
        for (int x = 0; x < testlist1.size(); x++) {
            Log.i("Test Size in for loop", String.valueOf(testlist1.size()));
            System.out.println("Team Name" + schedule_match_edt_txt.getText());

            if (testlist1.get(x).getTeamName().equals(team1name)) {
                team1id = testlist.get(x).getTeamid();
                System.out.println("Found team1 id : " + team1id);
                //Log.i("Id of team",testlist1.get(x).getTeamid().toString());
            }
            if (testlist1.get(x).getTeamName().equals(team2name)) {
                team2id = testlist.get(x).getTeamid();
                System.out.println("Found team 2 id : " + team2id);
                //Log.i("Id of team",testlist1.get(x).getTeamid().toString());
            }
        }
    }
}

