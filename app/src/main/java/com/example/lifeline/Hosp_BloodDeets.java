package com.example.lifeline;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Hosp_BloodDeets extends AppCompatActivity {

    TextView HospCard_Bloodtype, HospCard_BloodtypeN, UsedText, DonatedText, UsedTextN, DonatedTextN, CapacityEdit, CapacityEditN;
    ProgressBar HospCard_PlusBar, HospCard_MinusBar;
    ImageView ArrowDown;
    ImageButton Increment1, Decrement1, Increment2, Decrement2, Increment1N, Decrement1N, Increment2N, Decrement2N;
    Button HospDeetsConfirm;
    private FirebaseUser user;
    private DatabaseReference reference;
    private String userID;
    int count1 = 0, count2 = 0, count3 = 0, count4 = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hosp__blood_deets);

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        userID = user.getUid();

        ArrowDown = findViewById(R.id.ArrowDown);
        ArrowDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Hosp_BloodDeets.this, hosp_home.class));
            }
        });

        getIncomingIntent();

        HospDeetsConfirm = findViewById(R.id.HospDeetsConfirm);
        Increment1 = findViewById(R.id.Increment1);
        Decrement1 = findViewById(R.id.Decrement1);
        Increment2 = findViewById(R.id.Increment2);
        Decrement2 = findViewById(R.id.Decrement2);
        Increment1N = findViewById(R.id.Increment1N);
        Increment2N = findViewById(R.id.Increment2N);
        Decrement1N = findViewById(R.id.Decrement1N);
        Decrement2N = findViewById(R.id.Decrement2N);
        UsedText = findViewById(R.id.UsedText);
        DonatedText = findViewById(R.id.DonatedText);
        UsedTextN = findViewById(R.id.UsedTextN);
        DonatedTextN = findViewById(R.id.DonatedTextN);

        UsedText.setText(Integer.toString(count1));
        DonatedText.setText(Integer.toString(count2));
        UsedTextN.setText(Integer.toString(count3));
        DonatedTextN.setText(Integer.toString(count4));

        Increment1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count1++;
                UsedText.setText(Integer.toString(count1));
            }
        });
        Decrement1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(count1 == 0){
                    Toast.makeText(Hosp_BloodDeets.this, "Can't Decrement Further", Toast.LENGTH_SHORT).show();
                }
                else{
                count1--;
                UsedText.setText(Integer.toString(count1));}
            }
        });

        Increment2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count2++;
                DonatedText.setText(Integer.toString(count2));
            }
        });

        Decrement2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(count2 == 0){
                    Toast.makeText(Hosp_BloodDeets.this, "Can't Decrement Further", Toast.LENGTH_SHORT).show();
                }
                else{
                count2--;
                DonatedText.setText(Integer.toString(count2));}
            }
        });

        Increment1N.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count3++;
                UsedTextN.setText(Integer.toString(count3));
            }
        });

        Decrement1N.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(count3 == 0){
                    Toast.makeText(Hosp_BloodDeets.this, "Can't Decrement Further", Toast.LENGTH_SHORT).show();
                }
                else{
                count3--;
                UsedTextN.setText(Integer.toString(count3));}
            }
        });

        Increment2N.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count4++;
                DonatedTextN.setText(Integer.toString(count4));
            }
        });

        Decrement2N.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(count4 == 0){
                    Toast.makeText(Hosp_BloodDeets.this, "Can't Decrement Further", Toast.LENGTH_SHORT).show();
                }
                else{
                count4--;
                DonatedTextN.setText(Integer.toString(count4));}
            }
        });

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
        CapacityEdit = findViewById(R.id.CapacityEdit);
        CapacityEditN = findViewById(R.id.CapacityEditN);

        HospCard_Bloodtype.setText(Btype);
        HospCard_BloodtypeN.setText(Btype);
        HospCard_PlusBar.setProgress(P);
        HospCard_MinusBar.setProgress(N);
        CapacityEdit.setText(Integer.toString(P));
        CapacityEditN.setText(Integer.toString(N));
    }
}