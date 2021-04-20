package com.example.lifeline;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class hosp_home extends AppCompatActivity {

    RecyclerView RV;
    private HospAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private FirebaseUser user;
    private DatabaseReference reference;
    private String userID;
    private int AN, AP, BN, BP, ABP, ABN, OP, ON;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hosp_home);

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        userID = user.getUid();

        ImageButton Logout = findViewById(R.id.Logout);

        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(hosp_home.this, SignIn.class));
            }
        });


        SwipeRefreshLayout RefreshLayout = findViewById(R.id.RefreshLayout);

        RefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                finish();;
                startActivity(getIntent());
            }
        });

        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Hospital userprofile = snapshot.getValue(Hospital.class);

                if(userprofile != null){
                    AN = userprofile.AN;
                    AP = userprofile.AP;
                    BP = userprofile.BP;
                    BN = userprofile.BN;
                    ABP = userprofile.ABP;
                    ABN = userprofile.ABN;
                    OP = userprofile.OP;
                    ON = userprofile.ON;

                    RV = findViewById(R.id.RV);
                    RV.setHasFixedSize(true);
                    RV.setLayoutManager(new LinearLayoutManager(hosp_home.this));

                    HospDeet[] HospDeet = new HospDeet[]{
                            new HospDeet("A", AP, AN),
                            new HospDeet("B", BP, BN),
                            new HospDeet("AB", ABP, ABN),
                            new HospDeet("O", OP, ON),
                    };

                    HospAdapter HospAdapter = new HospAdapter(HospDeet, hosp_home.this);
                    RV.setAdapter(HospAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(hosp_home.this, "Something went wrong!", Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public void onRestart()
    {
        super.onRestart();
        finish();
        startActivity(getIntent());
    }
}
