package com.example.soccerallianceapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.soccer_alliance_project_test.R;

import java.util.ArrayList;

public class Matches_adapter extends RecyclerView.Adapter<Matches_adapter.ViewHolder> {

    private ArrayList<matches_data_list> matches_data_lists;
    private Context context;
    private View.OnClickListener matchListener;

    public Matches_adapter(ArrayList<matches_data_list> matches_data_lists, Context context) {
        this.matches_data_lists = matches_data_lists;
        this.context = context;
    }

    @NonNull
    @Override
    public Matches_adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.match_recycler_item,parent,false);

        return new Matches_adapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Matches_adapter.ViewHolder holder, int position) {

        holder.m_team1.setText(matches_data_lists.get(position).getTeam1_name());
        holder.m_team2.setText(matches_data_lists.get(position).getTeam2_name());
        holder.m_date.setText(matches_data_lists.get(position).getMatch_date());
        holder.m_time.setText(matches_data_lists.get(position).getMatch_time());

        /*---Set Team 1 and Team 2 Logo Here---*/

        // holder.item_image.setImageResource(matches_data_lists.get(position).getItem_image());
        //  Glide.with(context).load("https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.iconfinder.com%2Ficons%2F3222456%2Fbadge_club_football_shield_soccer_team_icon&psig=AOvVaw14UmH91fAPljOtw9wTLAfq&ust=1582501135750000&source=images&cd=vfe&ved=0CAIQjRxqFwoTCMjOk6Wq5ucCFQAAAAAdAAAAABAr").into(holder.item_image);

    }

    public void setOnClickListener(View.OnClickListener clickListener){

        matchListener = clickListener;

    }

    @Override
    public int getItemCount() {
        return matches_data_lists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView m_team1,m_team2,m_date,m_time;
        ImageView m_team1_logo,m_team2_logo;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            m_team2_logo = itemView.findViewById(R.id.m_team2_logo);
            m_team1_logo = itemView.findViewById(R.id.m_team1_logo);
            m_team2 = itemView.findViewById(R.id.m_team2);
            m_team1 = itemView.findViewById(R.id.m_team1);
            m_date = itemView.findViewById(R.id.m_date);
            m_time = itemView.findViewById(R.id.m_time);

            itemView.setTag(this);

            itemView.setOnClickListener(matchListener);

        }
    }


}
