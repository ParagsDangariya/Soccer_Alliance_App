package com.example.soccerallianceapp;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.soccer_alliance_project_test.R;
import com.example.soccerallianceapp.pojo.leaguelistbyuserId.LeagueList;
import com.example.soccerallianceapp.pojo.leaguelistbyuserId.LeaguesFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LeagueListFragment extends Fragment implements View.OnClickListener {

    public NavController DashboardNavController;

    private RecyclerView league_recycler_view;

    private Context context;

    private ArrayList<Comman_Data_List> comman_data_List;

    private Comman_adapter comman_adapter;
    FirebaseAuth fAuth;
    FloatingActionButton create_league_floating_btn;
    String user_id="";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_league_list, container, false);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        league_recycler_view = view.findViewById(R.id.league_recycler_view);

        DashboardNavController = Navigation.findNavController(getActivity(),R.id.dashboard_host_fragment);

        /*--------league Adapter Configuration--------*/

        context = getActivity().getApplicationContext();

        comman_data_List = new ArrayList<Comman_Data_List>();

        comman_adapter = new Comman_adapter(comman_data_List,context);

        //@SuppressLint("WrongConstant") LinearLayoutManager manager = new LinearLayoutManager(getActivity().getApplicationContext(),LinearLayoutManager.VERTICAL,false);

        league_recycler_view.setLayoutManager(new LinearLayoutManager(context));
        league_recycler_view.setAdapter(comman_adapter);


        create_league_floating_btn = view.findViewById(R.id.create_league_floating_btn);
        create_league_floating_btn.setOnClickListener(this);
        Getdataservice service = RetroFitInstance.getRetrofitInstance().create(Getdataservice.class);
        user_id = fAuth.getInstance().getCurrentUser().getUid();
        Log.d("step1","after getService part");



        Call <LeaguesFragment> listleague = service.getLeagueList(user_id);

        System.out.println("call "+listleague);

        listleague.enqueue(new Callback<LeaguesFragment>() {
            @Override
            public void onResponse(Call<LeaguesFragment> call, Response<LeaguesFragment> response) {

                Log.d("step2" ,"after onResponse");

                try {


                    LeaguesFragment realData = response.body();


                    System.out.println("response" + response.body().toString());


                    if (response.body() != null) {
                        if (realData.getStatus() == 200) {

                            for (LeagueList leagues : realData.getLeagueList()) {

                                comman_data_List.add(new Comman_Data_List(


                                        leagues.getName(),
                                        leagues.getLogo(),
                                        leagues.getLeagueId()

                                ));

                            }


                            comman_adapter.notifyDataSetChanged();

                            comman_adapter.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                    RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) v.getTag();

                                    int position = viewHolder.getAdapterPosition();

                                    Bundle bundle = new Bundle();



                                   Integer league_id = comman_data_List.get(position).getIteam_id();

                                    Toast.makeText(context, "leagueid"+league_id , Toast.LENGTH_LONG).show();

                                    System.out.println("League Id : " + league_id);

                                    bundle.putInt("League_id", comman_data_List.get(position).getIteam_id());

                                    bundle.putString("League_name",String.valueOf(comman_data_List.get(position).getItem_name()));
                                    bundle.putString("League_Logo",String.valueOf(comman_data_List.get(position).getItem_image()));



                                    bundle.putString("Coming_from", "ListLeaguesFragment_Class");

                                    DashboardNavController.navigate(R.id.leagueOperationsFragment, bundle);


                                }
                            });


                        }
                    } else {

                        Toast.makeText(getActivity(), "REsponse empty", Toast.LENGTH_LONG).show();

                    }


                } catch (Exception e) {


                   e.printStackTrace();
                }
            }


            @Override
            public void onFailure(Call<LeaguesFragment> call, Throwable t) {

                System.out.println("Error : "+t.getMessage());

            }
        });





    }

    public void onResume() {



        super.onResume();

        comman_data_List.clear();




//
//        comman_data_List.add(new Comman_Data_List("League 1","",1));
//        comman_data_List.add(new Comman_Data_List("League 2","",1));
//        comman_data_List.add(new Comman_Data_List("League 3","",1));
//        comman_data_List.add(new Comman_Data_List("League 4","",1));
//        comman_data_List.add(new Comman_Data_List("League 5","",1));
//        comman_adapter.notifyDataSetChanged();
//        comman_adapter.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                DashboardNavController.navigate(R.id.leagueOperationsFragment);
//
//
//            }
//        });

    }


    @Override
    public void onClick(View view) {

        if(view == create_league_floating_btn){
            DashboardNavController.navigate(R.id.createLeagueFragment);
        }
    }
}
