package com.example.lifeline;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DonorAdapter extends RecyclerView.Adapter<DonorAdapter.ViewHolder> {

    DonDeet[] DonDeet;
    Context context;


    public DonorAdapter(DonDeet[] DonDeet, donor_home activity){
        this.DonDeet = DonDeet;
        this.context = activity;
    }

    @NonNull
    @Override
    public DonorAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.donate_card, parent, false);
        DonorAdapter.ViewHolder viewHolder = new DonorAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DonorAdapter.ViewHolder holder, int position) {
        final DonDeet DonDeetList = DonDeet[position];
        holder.DCard_Bloodtype.setText(DonDeetList.getBlood());
        holder.HospName.setText(DonDeetList.getName());
        holder.HospAddress.setText(DonDeetList.getAddress());
        holder.PhoneHospNo.setText(DonDeetList.getPhoneno());
        if(DonDeetList.getBtype().equals("Positive")){
            holder.type.setBackgroundResource(R.drawable.plus);
        }
        else if(DonDeetList.getBtype().equals("Negative")){
            holder.type.setBackgroundResource(R.drawable.minus);
        }

    }

    @Override
    public int getItemCount() {
        return DonDeet.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView HandleBarD, DividerD, PhoneHosp, type;
        TextView DCard_Bloodtype, HospAddress, PhoneHospNo, HospName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            HospName = itemView.findViewById(R.id.HospName);
            HandleBarD = itemView.findViewById(R.id.HandleBar);
            DividerD = itemView.findViewById(R.id.Divider);
            DCard_Bloodtype = itemView.findViewById(R.id.DCard_Bloodtype);
            PhoneHosp = itemView.findViewById(R.id.PhoneHosp);
            type = itemView.findViewById(R.id.type);
            HospAddress = itemView.findViewById(R.id.HospAddress);
            PhoneHospNo = itemView.findViewById(R.id.PhoneHospNo);

        }
    }
}
