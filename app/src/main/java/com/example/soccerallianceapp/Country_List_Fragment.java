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
import com.example.soccerallianceapp.pojo.listLeagueDashboard.League;
import com.example.soccerallianceapp.pojo.listLeagueDashboard.ListLeagueDashboard;
import com.example.soccerallianceapp.pojo.listOfCountries.ListOfCountries;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Country_List_Fragment extends Fragment {

    public NavController DashboardNavController;
    private RecyclerView country_recycler_view;
    private Context context;
    private ArrayList<Comman_Data_List> comman_data_List ;
    private Comman_adapter comman_adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_country_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        context = getActivity().getApplicationContext();
        DashboardNavController = Navigation.findNavController(getActivity(), R.id.dashboard_host_fragment);
        /*--------Teams Adapter Configuration--------*/
        country_recycler_view = view.findViewById(R.id.country_recycler_view);
        comman_data_List = new ArrayList<Comman_Data_List>();
        comman_data_List.clear();
        comman_adapter = new Comman_adapter(comman_data_List, context);
        country_recycler_view.setLayoutManager(new LinearLayoutManager(context));
        country_recycler_view.setAdapter(comman_adapter);

        Getdataservice service = RetroFitInstance.getRetrofitInstance().create(Getdataservice.class);

        Call<ListOfCountries> listleaguecall = service.getListOfCountriesCall();
        System.out.println("call " + listleaguecall);
        listleaguecall.enqueue(new Callback<ListOfCountries>() {

            @Override
            public void onResponse(Call<ListOfCountries> call, Response<ListOfCountries> response) {
                Log.d("step2", "after onResponse");
                ListOfCountries realData = response.body();
                System.out.println("response" + realData);

                if (response.body() != null) {
                    if (realData.getStatus() == 200) {
                        for (String countries : realData.getCountries()) {

                            comman_data_List.add(new Comman_Data_List(countries));
                        }
                        comman_adapter.notifyDataSetChanged();

                        comman_adapter.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                                RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) view.getTag();
                                int position = viewHolder.getAdapterPosition();

                                Bundle bundle = new Bundle();
                                bundle.putString("country",comman_data_List.get(position).getItem_name());
                                bundle.putString("Coming_from" ,"Country_Fragment_Class");

                                //can not navigate from counrty to league frgment
                               // DashboardNavController.navigate(R.id.Leagues_Fragment,bundle);
                            }
                        });
                    }

                } else {
                    Toast.makeText(getActivity(), "Response empty", Toast.LENGTH_LONG).show();

                }

                comman_adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ListOfCountries> call, Throwable t) {

                System.out.println("Error : " + t.getMessage());
            }
        });
    }
}