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

public class Leagues_adapter extends RecyclerView.Adapter<Leagues_adapter.ViewHolder> {

    private ArrayList<Leagues_List> league_ArrayList;
    private Context context;
    private View.OnClickListener leagueListener;

    public Leagues_adapter(ArrayList<Leagues_List> league_ArrayList, Context context) {
        this.league_ArrayList = league_ArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public Leagues_adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.leagues_recycler_item,parent,false);

        return new Leagues_adapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Leagues_adapter.ViewHolder holder, int position) {

        holder.league_name.setText(league_ArrayList.get(position).getLeague_name());
        holder.league_image.setImageResource(league_ArrayList.get(position).getLeague_image());


    }

    public void setOnClickListener(View.OnClickListener clickListener){

        leagueListener = clickListener;

    }

    @Override
    public int getItemCount() {
        return league_ArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{


        TextView league_name;
        ImageView league_image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            league_name = itemView.findViewById(R.id.league_name);
            league_image = itemView.findViewById(R.id.league_icon);

            itemView.setTag(this);

            itemView.setOnClickListener(leagueListener);

        }
    }


}
