package com.example.lifeline;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignIn extends AppCompatActivity {

    private TextView SignUp;
    private EditText SignIn_email, SignIn_password;
    private ImageView SignInBtn;
    private ProgressBar progressBar;
    private FirebaseAuth mAuth;
    private DatabaseReference uidRef, rootRef;
    private TextView ForgotPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in);

        mAuth = FirebaseAuth.getInstance();

        ForgotPass = findViewById(R.id.ForgotPass);
        SignIn_email = findViewById(R.id.SignIn_email);
        SignIn_password = findViewById(R.id.SignIn_password);
        SignInBtn = findViewById(R.id.SignInBtn);
        progressBar = findViewById(R.id.progressBar);

        ForgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignIn.this, ForgotPassword.class));
            }
        });

        SignInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userLogin();
            }
        });

        SignUp = findViewById(R.id.SignUp);

        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignIn.this, register_hosp.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(intent);
            }
        });
    }

    private void userLogin(){

        String email = SignIn_email.getText().toString().trim();
        String password = SignIn_password.getText().toString().trim();

        if (email.isEmpty()){
            SignIn_email.setError("Email is Required");
            SignIn_email.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            SignIn_email.setError("Please provide a valid email address");
            SignIn_email.requestFocus();
            return;
        }

        if (password.isEmpty()){
            SignIn_password.setError("Password is Required");
            SignIn_password.requestFocus();
            return;
        }
        if(password.length()<6){
            SignIn_password.setError("Password must be at least 6 characters");
            SignIn_password.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    //Redirect
                    onAuthSuccess();
                    //Intent intent = new Intent(SignIn.this, donor_home.class);
                    //startActivity(intent);
                }
                else{
                    Toast.makeText(SignIn.this, "Failed To Login", Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(View.GONE);
                }
            }
        });
    }

    private void onAuthSuccess(){

        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        rootRef = FirebaseDatabase.getInstance().getReference();
        uidRef = rootRef.child("Users").child(uid);
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.child("type").getValue(Long.class) == 1){
                    Intent intent  = new Intent(SignIn.this, donor_home.class);
                    //intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                    startActivity(intent);
                    finish();
                }
                else{
                    Intent intent = new Intent(SignIn.this, hosp_home.class);
                    //intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                    startActivity(intent);
                    finish();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        uidRef.addListenerForSingleValueEvent(valueEventListener);

    }
}
