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

public class register_donor extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText Register_Donor_name, Register_Donor_email,
            Register_Donor_password, Register_Donor_dob,
            Register_Donor_phoneno, Register_Donor_weight,
            Register_Donor_height, Register_Donor_bloodtype;
    private Button DonorRegisterBtn, Switch2;
    private ProgressBar progressBar1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_donor);

        mAuth = FirebaseAuth.getInstance();

        Register_Donor_name = findViewById(R.id.Register_Donor_name);
        Register_Donor_email = findViewById(R.id.Register_Donor_email);
        Register_Donor_password = findViewById(R.id.Register_Donor_password);
        Register_Donor_dob = findViewById(R.id.Register_Donor_dob);
        Register_Donor_phoneno = findViewById(R.id.Register_Donor_phoneno);
        Register_Donor_weight = findViewById(R.id.Register_Donor_weight);
        Register_Donor_height = findViewById(R.id.Register_Donor_height);
        Register_Donor_bloodtype = findViewById(R.id.Register_Donor_bloodtype);
        progressBar1 = findViewById(R.id.progressBar1);

        Switch2 = findViewById(R.id.Switch2);
        Switch2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(register_donor.this, register_hosp.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

        DonorRegisterBtn = findViewById(R.id.DonorRegisterBtn);
        DonorRegisterBtn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                registerdonor();
            }
        });
    }
    private void registerdonor() {
        String name = Register_Donor_name.getText().toString().trim();
        String email = Register_Donor_email.getText().toString().trim();
        String password = Register_Donor_password.getText().toString().trim();
        String dob = Register_Donor_dob.getText().toString().trim();
        String phoneno = Register_Donor_phoneno.getText().toString().trim();
        String weight = Register_Donor_weight.getText().toString().trim();
        String height = Register_Donor_height.getText().toString().trim();
        String bloodtype = Register_Donor_bloodtype.getText().toString().trim();


        if (name.isEmpty()){
            Register_Donor_name.setError("Name is Required");
            Register_Donor_name.requestFocus();
            return;
        }
        if (email.isEmpty()){
            Register_Donor_email.setError("Email is Required");
            Register_Donor_email.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            Register_Donor_email.setError("Please provide a valid email address");
            Register_Donor_email.requestFocus();
            return;
        }
        if (password.isEmpty()){
            Register_Donor_password.setError("Password is Required");
            Register_Donor_password.requestFocus();
            return;
        }
        if(password.length()<6){
            Register_Donor_password.setError("Password must be at least 6 characters");
            Register_Donor_password.requestFocus();
            return;
        }
        if (dob.isEmpty()){
            Register_Donor_dob.setError("DOB is Required");
            Register_Donor_dob.requestFocus();
            return;
        }
        if (phoneno.isEmpty()){
            Register_Donor_phoneno.setError("Phone No. is Required");
            Register_Donor_phoneno.requestFocus();
            return;
        }
        if(phoneno.length() != 10){
            Register_Donor_phoneno.setError("Please Enter a Valid Phone. No.");
            Register_Donor_phoneno.requestFocus();
            return;
        }
        if (weight.isEmpty()){
            Register_Donor_weight.setError("Weight is Required");
            Register_Donor_weight.requestFocus();
            return;
        }
        if (height.isEmpty()){
            Register_Donor_height.setError("Weight is Required");
            Register_Donor_height.requestFocus();
            return;
        }
        int weight_num = Integer.parseInt(weight);
        int height_num = Integer.parseInt(height);

        if(height_num < 163 && weight_num < 50){
            Register_Donor_weight.setError("Weight doesn't meet criteria");
            Register_Donor_weight.requestFocus();
            return;
        }

        if(bloodtype.isEmpty()){
            Register_Donor_bloodtype.setError("Blood Type is Required");
            Register_Donor_bloodtype.requestFocus();
            return;
        }


        progressBar1.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(register_donor.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Donor donor = new Donor(name, email, dob, phoneno, weight, height, bloodtype);
                    FirebaseDatabase.getInstance().getReference("Users")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                            .setValue(donor).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(register_donor.this, "Donor has been registered Successfully!", Toast.LENGTH_LONG).show();
                                progressBar1.setVisibility(View.GONE);
                                startActivity(new Intent(register_donor.this, donor_home.class));
                            }
                            else{
                                Toast.makeText(register_donor.this, "Failed to Register!", Toast.LENGTH_LONG).show();
                                progressBar1.setVisibility(View.GONE);
                            }
                        }
                    });
                }
                else{
                    Toast.makeText(register_donor.this, "Failure!", Toast.LENGTH_LONG).show();
                    progressBar1.setVisibility(View.GONE);
                }
            }
        });
    }
}
