package com.example.lifeline;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.content.Intent;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    Button RegisterBtnHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RegisterBtnHome = (Button)findViewById(R.id.RegisterBtnHome);
        RegisterBtnHome.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, register_donor.class);

                // start the activity connect to the specified class
                startActivity(intent);
            }
        });
    }
}
