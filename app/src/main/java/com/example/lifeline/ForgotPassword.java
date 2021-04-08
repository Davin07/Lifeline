package com.example.lifeline;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPassword extends AppCompatActivity {

    private EditText ForgotPassEmail;
    private Button ForgotPassBtn;
    private ProgressBar progressBarreset;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        ForgotPassEmail = findViewById(R.id.ForgotPassEmail);
        ForgotPassBtn = findViewById(R.id.ForgotPassBtn);
        progressBarreset = findViewById(R.id.progressBarreset);

        auth = FirebaseAuth.getInstance();

        ForgotPassBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetPassword();
            }
        });

    }

    private void resetPassword(){
        String email = ForgotPassEmail.getText().toString().trim();

        if(email.isEmpty()){
            ForgotPassEmail.setError("Email is Required!");
            ForgotPassEmail.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            ForgotPassEmail.setError("Please provide a valid Email!");
            ForgotPassEmail.requestFocus();
            return;
        }

        progressBarreset.setVisibility(View.VISIBLE);

        auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(ForgotPassword.this, "Check your Email to reset your password!", Toast.LENGTH_LONG).show();
                    progressBarreset.setVisibility(View.GONE);
                    startActivity(new Intent(ForgotPassword.this, SignIn.class));
                }
                else{
                    Toast.makeText(ForgotPassword.this, "Failed! Try Again.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}