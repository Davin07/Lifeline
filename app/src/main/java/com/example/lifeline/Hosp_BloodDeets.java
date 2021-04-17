package com.example.lifeline;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class Hosp_BloodDeets extends AppCompatActivity {

    TextView HospCard_Bloodtype, HospCard_BloodtypeN;
    ProgressBar HospCard_PlusBar, HospCard_MinusBar;
    ImageView ArrowDown;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hosp__blood_deets);

        ArrowDown = findViewById(R.id.ArrowDown);
        ArrowDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Hosp_BloodDeets.this, hosp_home.class));
            }
        });

        getIncomingIntent();
    }

    private void getIncomingIntent() {
        if(getIntent().hasExtra("Type") && getIntent().hasExtra("PVal") && getIntent().hasExtra("NVal")){
                String Btype = getIntent().getStringExtra("Type");
                int P = getIntent().getIntExtra("PVal", 0);
                int N = getIntent().getIntExtra("NVal", 0);
                setStuff(Btype, P, N);
        }
    }

    private void setStuff(String Btype, int P, int N){

        HospCard_Bloodtype = findViewById(R.id.HospCard_Bloodtype);
        HospCard_BloodtypeN = findViewById(R.id.HospCard_BloodtypeN);
        HospCard_PlusBar = findViewById(R.id.HospCard_PlusBar);
        HospCard_MinusBar = findViewById(R.id.HospCard_MinusBar);

        HospCard_Bloodtype.setText(Btype);
        HospCard_BloodtypeN.setText(Btype);
        HospCard_PlusBar.setProgress(P);
        HospCard_MinusBar.setProgress(N);
    }
}