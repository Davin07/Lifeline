package com.example.lifeline;


import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class HospAdapter extends RecyclerView.Adapter<HospAdapter.ViewHolder> {

    HospDeet[] HospDeet;
    Context context;


    public HospAdapter(HospDeet[] HospDeet, hosp_home activity){
        this.HospDeet = HospDeet;
        this.context = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.hosp_card, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final HospDeet HospDeetList = HospDeet[position];
        holder.HospCard_Bloodtype.setText(HospDeetList.getBloodType());
        holder.HospCard_PlusBar.setProgress(HospDeetList.getPositive());
        holder.HospCard_MinusBar.setProgress(HospDeetList.getNegative());

        //On-Click for each ItemView
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Hosp_BloodDeets.class);
                intent.putExtra("Type", HospDeetList.getBloodType());
                intent.putExtra("PVal", HospDeetList.getPositive());
                intent.putExtra("NVal", HospDeetList.getNegative());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return HospDeet.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView HandleBar, Divider;
        TextView HospCard_Bloodtype;
        ProgressBar HospCard_PlusBar, HospCard_MinusBar;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            HandleBar = itemView.findViewById(R.id.HandleBar);
            Divider = itemView.findViewById(R.id.Divider);
            HospCard_Bloodtype = itemView.findViewById(R.id.HospCard_Bloodtype);
            HospCard_PlusBar = itemView.findViewById(R.id.HospCard_PlusBar);
            HospCard_MinusBar = itemView.findViewById(R.id.HospCard_MinusBar);
        }
    }

}
