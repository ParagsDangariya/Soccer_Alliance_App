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

public class Comman_adapter extends RecyclerView.Adapter<Comman_adapter.ViewHolder> {

    private ArrayList<Comman_Data_List> comman_data_lists;
    private Context context;
    private View.OnClickListener commanListener;

    public Comman_adapter(ArrayList<Comman_Data_List> comman_data_lists, Context context) {
        this.comman_data_lists = comman_data_lists;
        this.context = context;
    }

    @NonNull
    @Override
    public Comman_adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.comman_recycler_item,parent,false);

        return new Comman_adapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Comman_adapter.ViewHolder holder, int position) {

      /*  holder.item_name.setText(comman_data_lists.get(position).getItem_name());
        holder.item_image.setImageResource(comman_data_lists.get(position).getItem_image());

*/
    }

    public void setOnClickListener(View.OnClickListener clickListener){

        commanListener = clickListener;

    }

    @Override
    public int getItemCount() {
        return comman_data_lists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{


        TextView item_name;
        ImageView item_image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            item_name = itemView.findViewById(R.id.item_name);
            item_image = itemView.findViewById(R.id.item_icon);

            itemView.setTag(this);

            itemView.setOnClickListener(commanListener);

        }
    }


}
