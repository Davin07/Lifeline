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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class HospAdapter extends RecyclerView.Adapter<HospAdapter.ViewHolder> {

    HospDeet[] HospDeet;
    Context context;
    DatabaseReference reference;

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
        holder.PercentP.setText(Integer.toString(HospDeetList.getPositive()));
        holder.PercentN.setText(Integer.toString(HospDeetList.getNegative()));
        if((HospDeetList.getPositive() < 40) || (HospDeetList.getNegative() < 40)){
            holder.Alert.setVisibility(View.VISIBLE);
        }



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

        ImageView HandleBar, Divider, Alert;
        TextView HospCard_Bloodtype, PercentP, PercentN;
        ProgressBar HospCard_PlusBar, HospCard_MinusBar;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            HandleBar = itemView.findViewById(R.id.HandleBar);
            Divider = itemView.findViewById(R.id.Divider);
            HospCard_Bloodtype = itemView.findViewById(R.id.HospCard_Bloodtype);
            HospCard_PlusBar = itemView.findViewById(R.id.HospCard_PlusBar);
            HospCard_MinusBar = itemView.findViewById(R.id.HospCard_MinusBar);
            Alert = itemView.findViewById(R.id.Alert);
            PercentP = itemView.findViewById(R.id.PercentP);
            PercentN = itemView.findViewById(R.id.PercentN);
        }
    }

}
