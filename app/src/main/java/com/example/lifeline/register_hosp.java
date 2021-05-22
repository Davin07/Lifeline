package com.example.lifeline;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class register_hosp extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText Register_Hosp_name, Register_Hosp_email,
            Register_Hosp_password, Register_Hosp_phoneno, Register_Hosp_address;
    Button HospRegisterBtn, Switch1;
    ProgressBar progressBar2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_hosp);

        mAuth = FirebaseAuth.getInstance();

        Register_Hosp_name = findViewById(R.id.Register_Hosp_name);
        Register_Hosp_email = findViewById(R.id.Register_Hosp_email);
        Register_Hosp_password = findViewById(R.id.Register_Hosp_password);
        Register_Hosp_phoneno = findViewById(R.id.Register_Hosp_phoneno);
        Register_Hosp_address = findViewById(R.id.Register_Hosp_address);
        progressBar2 = findViewById(R.id.progressBar2);

        Switch1 = findViewById(R.id.Switch1);
        Switch1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(register_hosp.this, register_donor.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
            }
        });


        HospRegisterBtn = findViewById(R.id.HospRegisterBtn);
        HospRegisterBtn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                registerhosp();
            }
        });
    }

    private void registerhosp() {
        String name = Register_Hosp_name.getText().toString().trim();
        String email = Register_Hosp_email.getText().toString().trim();
        String password = Register_Hosp_password.getText().toString().trim();
        String phoneno = Register_Hosp_phoneno.getText().toString().trim();
        String address = Register_Hosp_address.getText().toString().trim();

        if (name.isEmpty()){
            Register_Hosp_name.setError("Name is Required");
            Register_Hosp_name.requestFocus();
            return;
        }
        if (email.isEmpty()){
            Register_Hosp_email.setError("Email is Required");
            Register_Hosp_email.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            Register_Hosp_email.setError("Please provide a valid email address");
            Register_Hosp_email.requestFocus();
            return;
        }
        if (password.isEmpty()){
            Register_Hosp_password.setError("Password is Required");
            Register_Hosp_password.requestFocus();
            return;
        }
        if(password.length()<6){
            Register_Hosp_password.setError("Password must be at least 6 characters");
            Register_Hosp_password.requestFocus();
            return;
        }
        if (phoneno.isEmpty()){
            Register_Hosp_phoneno.setError("Phone No. is Required");
            Register_Hosp_phoneno.requestFocus();
            return;
        }
        if(phoneno.length() != 10){
            Register_Hosp_phoneno.setError("Please Enter a Valid Phone. No.");
            Register_Hosp_phoneno.requestFocus();
            return;
        }
        if (address.isEmpty()){
            Register_Hosp_address.setError("Phone No. is Required");
            Register_Hosp_address.requestFocus();
            return;
        }

        progressBar2.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(register_hosp.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Hospital hospital = new Hospital(name, email, phoneno, address);
                    FirebaseDatabase.getInstance().getReference("Users")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                            .setValue(hospital).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(register_hosp.this, "Hospital has been registered Successfully!", Toast.LENGTH_LONG).show();
                                progressBar2.setVisibility(View.GONE);
                                Intent intent = new Intent(register_hosp.this, hosp_home.class);
                                //intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                                startActivity(intent);
                                finish();
                            }
                            else{
                                Toast.makeText(register_hosp.this, "Failed to Register!", Toast.LENGTH_LONG).show();
                                progressBar2.setVisibility(View.GONE);
                            }
                        }
                    });
                }
                else{
                    Toast.makeText(register_hosp.this, "Failure!", Toast.LENGTH_LONG).show();
                    progressBar2.setVisibility(View.GONE);
                }
            }
        });
    }
}
