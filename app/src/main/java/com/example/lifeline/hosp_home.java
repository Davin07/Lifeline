package com.example.lifeline;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class hosp_home extends AppCompatActivity {

    RecyclerView RV;
    private HospAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hosp_home);

        RV = findViewById(R.id.RV);
        RV.setHasFixedSize(true);
        RV.setLayoutManager(new LinearLayoutManager(this));

        HospDeet[] HospDeet = new HospDeet[]{
                new HospDeet("A", 50, 40),
                new HospDeet("B", 60, 50),
                new HospDeet("AB", 100, 90),
                new HospDeet("O", 90, 80),
        };

        HospAdapter HospAdapter = new HospAdapter(HospDeet, hosp_home.this);
        RV.setAdapter(HospAdapter);
    }
}
