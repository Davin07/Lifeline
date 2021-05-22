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

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


public class donor_home  extends AppCompatActivity {

    private ImageView hamburga;
    private TextView Logout;
    RecyclerView RVD;
    private RecyclerView.LayoutManager mLayoutManager;
    DonorAdapter myAdapter;


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
                finish();
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
        RVD.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<DonDeet> options =
                new FirebaseRecyclerOptions.Builder<DonDeet>()
                        .setQuery( FirebaseDatabase.getInstance().getReference().child("Requests"), DonDeet.class)
                        .build();

        myAdapter = new DonorAdapter(options);
        RVD.setAdapter(myAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        myAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        myAdapter.stopListening();
    }

    @Override
    public void onBackPressed() { }
}
