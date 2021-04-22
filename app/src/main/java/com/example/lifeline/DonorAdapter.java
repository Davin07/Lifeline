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

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class DonorAdapter extends FirebaseRecyclerAdapter<DonDeet, DonorAdapter.myViewHolder> {

    public DonorAdapter(@NonNull FirebaseRecyclerOptions<DonDeet> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull DonDeet model) {
        holder.DCard_Bloodtype.setText(model.getBlood());
        holder.HospName.setText(model.getName());
        holder.HospAddress.setText(model.getAddress());
        holder.PhoneHospNo.setText(model.getPhoneno());
        if(model.getType().equals("Positive")){
            holder.type.setBackgroundResource(R.drawable.plus);
        }
        else if(model.getType().equals("Negative")){
            holder.type.setBackgroundResource(R.drawable.minus);
        }
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.donate_card, parent, false);
        return new myViewHolder(view);

    }

    class myViewHolder extends RecyclerView.ViewHolder{

        ImageView HandleBarD, DividerD, PhoneHosp, type;
        TextView DCard_Bloodtype, HospAddress, PhoneHospNo, HospName;
        public myViewHolder(@NonNull View itemView) {
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
