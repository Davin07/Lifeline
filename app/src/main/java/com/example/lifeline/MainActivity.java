package com.example.lifeline;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.content.Intent;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    Button RegisterBtnHome;
    TextView Home_SignInText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Home_SignInText = findViewById(R.id.Home_SignInText);
        RegisterBtnHome = findViewById(R.id.RegisterBtnHome);
        RegisterBtnHome.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, register_donor.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                // start the activity connect to the specified class
                startActivity(intent);
            }
        });

        Home_SignInText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SignIn.class);
                //intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(intent);
            }
        });
    }
}
