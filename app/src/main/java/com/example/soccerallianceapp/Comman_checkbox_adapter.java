package com.example.soccerallianceapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.soccer_alliance_project_test.R;

import java.util.ArrayList;

public class Comman_checkbox_adapter extends RecyclerView.Adapter<Comman_checkbox_adapter.ViewHolder> {

    private ArrayList<Comman_Checkbox_Data_List> comman_checkbox_data_list;
    private Context context;
    private View.OnClickListener comman_checkbox_Listener;

    public Comman_checkbox_adapter(ArrayList<Comman_Checkbox_Data_List> comman_checkbox_data_list, Context context) {
        this.comman_checkbox_data_list = comman_checkbox_data_list;
        this.context = context;
    }

    @NonNull
    @Override
    public Comman_checkbox_adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.comman_checkbox_recycler_item,parent,false);

        return new Comman_checkbox_adapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Comman_checkbox_adapter.ViewHolder holder, int position) {

        holder.check_item_name.setText(comman_checkbox_data_list.get(position).getCheck_item_name());
        holder.check_item_image.setImageResource(comman_checkbox_data_list.get(position).getCheck_item_image());


    }

    public void setOnClickListener(View.OnClickListener clickListener){

        comman_checkbox_Listener = clickListener;

    }

    @Override
    public int getItemCount() {
        return comman_checkbox_data_list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        CheckBox item_checkbox;
        TextView check_item_name;
        ImageView check_item_image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            item_checkbox = itemView.findViewById(R.id.item_checkbox);
            check_item_name = itemView.findViewById(R.id.checked_item_name);
            check_item_image = itemView.findViewById(R.id.checked_item_icon);

            itemView.setTag(this);

            itemView.setOnClickListener(comman_checkbox_Listener);

        }
    }


}
