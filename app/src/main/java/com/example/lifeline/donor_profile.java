package com.example.lifeline;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class donor_profile extends AppCompatActivity {

    private FirebaseUser user;
    private DatabaseReference reference;
    private String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.donor_profile);

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        userID = user.getUid();

        final TextView Profile_Name = findViewById(R.id.Profile_Name);
        final TextView Profile_Bloodtype = findViewById(R.id.Profile_Bloodtype);
        final TextView Profile_Email = findViewById(R.id.Profile_Email);
        final TextView Profile_DOB = findViewById(R.id.Profile_DOB);
        final TextView Profile_Weight = findViewById(R.id.Profile_Weight);
        final TextView Profile_Height = findViewById(R.id.Profile_Height);
        final TextView Profile_PhoneNo = findViewById(R.id.Profile_PhoneNo);

        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Donor userprofile = snapshot.getValue(Donor.class);

                if (userprofile != null) {
                    String name = userprofile.name;
                    String bloodtype = userprofile.bloodtype;
                    String email = userprofile.email;
                    String dob = userprofile.dob;
                    String weight = userprofile.weight;
                    String height = userprofile.height;
                    String phoneno = userprofile.phoneno;

                    Profile_Name.setText(name);
                    Profile_Bloodtype.setText(bloodtype);
                    Profile_Email.setText(email);
                    Profile_DOB.setText(dob);
                    Profile_Weight.setText(weight+" kgs");
                    Profile_Height.setText(height + " cms");
                    Profile_PhoneNo.setText(phoneno);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(donor_profile.this, "Something went wrong!", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
}
