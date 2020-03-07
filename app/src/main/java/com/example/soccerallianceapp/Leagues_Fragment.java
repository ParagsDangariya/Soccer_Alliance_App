package com.example.soccerallianceapp;


import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ProgressBar;


import android.widget.Button;


import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.soccer_alliance_project_test.R;
import com.example.soccerallianceapp.pojo.listLeagueDashboard.League;
import com.example.soccerallianceapp.pojo.listLeagueDashboard.ListLeagueDashboard;

import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Leagues_Fragment extends Fragment {

    public NavController DashboardNavController;
    private RecyclerView league_recycler_view;
    private Context context;
    private ArrayList<Comman_Data_List> comman_data_List;
    private Comman_adapter comman_adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_leagues, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

         DashboardNavController = Navigation.findNavController(getActivity(),R.id.dashboard_host_fragment);
        context = getActivity().getApplicationContext();

        /*--------league Adapter Configuration--------*/
        comman_data_List = new ArrayList<Comman_Data_List>();

        Getdataservice service = RetroFitInstance.getRetrofitInstance().create(Getdataservice.class);
        Log.d("step1","after getService part");
        Call<ListLeagueDashboard> listleaguecall  = service.getListOfLeague_guestDashboardCall();

        System.out.println("call "+listleaguecall);
        listleaguecall.enqueue(new Callback<ListLeagueDashboard>() {

            @Override
            public void onResponse(Call<ListLeagueDashboard> call, Response<ListLeagueDashboard> response) {

                Log.d("step2" ,"after onResponse");
                ListLeagueDashboard realData = response.body();

                System.out.println("response" +response.body().toString());

                if (response.body() != null) {
                    if (realData.getStatus() == 200) {
                        for (League league : realData.getLeagues()) {

                            comman_data_List.add(new Comman_Data_List(
                                    league.getName(),
                                    league.getLogo(),
                                    league.getLeagueId()));
                        }
                        comman_adapter.notifyDataSetChanged();

                        comman_adapter.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                                RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) view.getTag();
                                int position = viewHolder.getAdapterPosition();

                                Bundle bundle = new Bundle();
                                bundle.putString("league_id",String.valueOf(comman_data_List.get(position).getIteam_id()));
                                bundle.putString("Coming_from" ,"Leagues_Fragment_Class");


                                DashboardNavController.navigate(R.id.teamListFragment,bundle);
                            }
                        });
                    }

                }else{
                    Toast.makeText(getActivity() ,"Response empty",Toast.LENGTH_LONG).show();
                    }
            }


            @Override
            public void onFailure(Call<ListLeagueDashboard> call, Throwable t) {

                System.out.println("Error : "+t.getMessage());

            }
        });

        league_recycler_view = view.findViewById(R.id.leagues_recycler_view);
        comman_adapter = new Comman_adapter(comman_data_List, context);

        league_recycler_view.setLayoutManager(new LinearLayoutManager(context));

        //league_recycler_view.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        //league_recycler_view.setItemAnimator(new DefaultItemAnimator());

        league_recycler_view.setAdapter(comman_adapter);

    }

}
