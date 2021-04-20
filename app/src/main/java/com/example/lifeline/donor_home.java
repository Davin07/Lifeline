package com.example.lifeline;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

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

public class donor_home  extends AppCompatActivity {

    private ImageView hamburga;
    private TextView Logout;
    RecyclerView RVD;
    private HospAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.donor_home);




        Logout = findViewById(R.id.Logout);
        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(donor_home.this, SignIn.class));
            }
        });

        hamburga = findViewById(R.id.hamburga);
        hamburga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(donor_home.this, donor_profile.class));
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
            }
        });

        SwipeRefreshLayout RefreshLayoutD = findViewById(R.id.RefreshLayoutD);

        RefreshLayoutD.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                finish();
                startActivity(getIntent());
            }
        });

        RVD = findViewById(R.id.RVD);
        RVD.setHasFixedSize(true);
        RVD.setLayoutManager(new LinearLayoutManager(donor_home.this));

        DonDeet[] DonDeet = new DonDeet[]{
                new DonDeet("KdenBye Layout", "A", "Positive", "Apollo Hospital", "9886387227")
        };

        DonorAdapter HospAdapter = new DonorAdapter(DonDeet, donor_home.this);
        RVD.setAdapter(HospAdapter);

    }
}
