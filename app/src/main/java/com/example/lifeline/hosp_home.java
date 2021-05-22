package com.example.lifeline;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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

import java.util.ArrayList;

public class hosp_home extends AppCompatActivity {

    RecyclerView RV;
    private HospAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private FirebaseUser user;
    private DatabaseReference reference, reference1, czech, czech1;
    private String userID;
    private int AN, AP, BN, BP, ABP, ABN, OP, ON;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hosp_home);

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        reference1 = FirebaseDatabase.getInstance().getReference("Requests");
        userID = user.getUid();

        TextView Logout = findViewById(R.id.Logout);

        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(hosp_home.this, SignIn.class));
                finish();
            }
        });


        SwipeRefreshLayout RefreshLayout = findViewById(R.id.RefreshLayout);

        RefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                reference.child(userID).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        Hospital userprofile = snapshot.getValue(Hospital.class);

                        if(userprofile != null){

                            AN = userprofile.AN;
                            AP = userprofile.AP;
                            BP = userprofile.BP;
                            BN = userprofile.BN;
                            ABP = userprofile.ABP;
                            ABN = userprofile.ABN;
                            OP = userprofile.OP;
                            ON = userprofile.ON;
                            String name = userprofile.name;
                            String address = userprofile.address;
                            String phoneno = userprofile.phoneno;

                            czech = FirebaseDatabase.getInstance().getReference("Requests");
                            czech.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {

                                    //Table Doesn't Have A+ < 40
                                    if (!((snapshot.hasChild("Positive")) && (snapshot.hasChild("A")))) {
                                        if (AP < 40) {
                                            String id = name.concat("B1");
                                            reference1 = FirebaseDatabase.getInstance().getReference("Requests");
                                            Request request = new Request(name, address, "A", "Positive", phoneno);
                                            reference1.child(id).setValue(request);
                                        }
                                    } //Has A+ >40

                                    if(snapshot.hasChild(name.concat("B1"))){
                                        if (AP >= 40) {
                                            String id = name.concat("B1");
                                            reference1.child(id).removeValue();
                                        }
                                    }

                                    //Doesn't Have A- <40
                                    if (!((snapshot.hasChild("Negative")) && (snapshot.hasChild("A")))){
                                        if (AN < 40) {
                                            String id = name.concat("B2");
                                            reference1 = FirebaseDatabase.getInstance().getReference("Requests");
                                            Request request = new Request(name, address, "A", "Negative", phoneno);
                                            reference1.child(id).setValue(request);
                                        }
                                    } //Has A- > 40
                                    if(snapshot.hasChild(name.concat("B2"))){
                                        if (AN >= 40) {
                                            String id = name.concat("B2");
                                            reference1.child(id).removeValue();
                                        }
                                    }

                                    //Doesn't Have B+ < 40
                                    if (!((snapshot.hasChild("Positive")) && (snapshot.hasChild("B")))) {
                                        if (BP < 40) {
                                            String id = name.concat("B3");
                                            reference1 = FirebaseDatabase.getInstance().getReference("Requests");
                                            Request request = new Request(name, address, "B", "Positive", phoneno);
                                            reference1.child(id).setValue(request);
                                        }
                                    } //Has B+ > 40
                                    if(snapshot.hasChild(name.concat("B3"))){
                                        if (BP >= 40) {
                                            String id = name.concat("B3");
                                            reference1.child(id).removeValue();
                                        }
                                    }

                                    //Doesn't Have B- < 40
                                    if (!((snapshot.hasChild("Negative")) && (snapshot.hasChild("B")))) {
                                        if (BN < 40) {
                                            String id = name.concat("B4");
                                            reference1 = FirebaseDatabase.getInstance().getReference("Requests");
                                            Request request = new Request(name, address, "B", "Negative", phoneno);
                                            reference1.child(id).setValue(request);
                                        }
                                    } //Has B- > 40
                                    if(snapshot.hasChild(name.concat("B4"))){
                                        if (BN >= 40) {
                                            String id = name.concat("B4");
                                            reference1.child(id).removeValue();
                                        }
                                    }

                                    //Doesn't Have AB+ < 40
                                    if (!((snapshot.hasChild("Positive")) && (snapshot.hasChild("AB")))) {
                                        if (ABP < 40) {
                                            String id = name.concat("B5");
                                            reference1 = FirebaseDatabase.getInstance().getReference("Requests");
                                            Request request = new Request(name, address, "AB", "Positive", phoneno);
                                            reference1.child(id).setValue(request);
                                        }
                                    } //Has AB+ > 40
                                    if(snapshot.hasChild(name.concat("B5"))){
                                        if (ABP >= 40) {
                                            String id = name.concat("B5");
                                            reference1.child(id).removeValue();
                                        }
                                    }

                                    //Doesn't Have AB- < 40
                                    if (!((snapshot.hasChild("Negative")) && (snapshot.hasChild("AB")))) {
                                        if (ABN < 40) {
                                            String id = name.concat("B6");
                                            reference1 = FirebaseDatabase.getInstance().getReference("Requests");
                                            Request request = new Request(name, address, "AB", "Negative", phoneno);
                                            reference1.child(id).setValue(request);
                                        }
                                    } //Has AB- > 40
                                    if(snapshot.hasChild(name.concat("B6"))){
                                        if (ABN >= 40) {
                                            String id = name.concat("B6");
                                            reference1.child(id).removeValue();
                                        }
                                    }

                                    //Doesn't Have O+ < 40
                                    if (!((snapshot.hasChild("Positive")) && (snapshot.hasChild("O")))) {
                                        if (OP < 40) {
                                            String id = name.concat("B7");
                                            reference1 = FirebaseDatabase.getInstance().getReference("Requests");
                                            Request request = new Request(name, address, "O", "Positive", phoneno);
                                            reference1.child(id).setValue(request);
                                        }
                                    } //Has O+ > 40
                                    if(snapshot.hasChild(name.concat("B7"))){
                                        if (OP >= 40) {
                                            String id = name.concat("B7");
                                            reference1.child(id).removeValue();
                                        }
                                    }

                                    //Doesn't Have O- < 40
                                    if (!((snapshot.hasChild("Negative")) && (snapshot.hasChild("O")))) {
                                        if (ON < 40) {
                                            String id = name.concat("B8");
                                            reference1 = FirebaseDatabase.getInstance().getReference("Requests");
                                            Request request = new Request(name, address, "O", "Negative", phoneno);
                                            reference1.child(id).setValue(request);
                                        }
                                    } //Has O- > 40
                                    if(snapshot.hasChild(name.concat("B8"))){
                                        if (ON >= 40) {
                                            String id = name.concat("B8");
                                            reference1.child(id).removeValue();
                                        }
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {
                                    Toast.makeText(hosp_home.this, "No need to update", Toast.LENGTH_LONG).show();
                                }
                            });
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                finish();
                startActivity(getIntent());
            }
        });

        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Hospital userprofile = snapshot.getValue(Hospital.class);

                if (userprofile != null) {
                    AN = userprofile.AN;
                    AP = userprofile.AP;
                    BP = userprofile.BP;
                    BN = userprofile.BN;
                    ABP = userprofile.ABP;
                    ABN = userprofile.ABN;
                    OP = userprofile.OP;
                    ON = userprofile.ON;
                    String name = userprofile.name;
                    String address = userprofile.address;
                    String phoneno = userprofile.phoneno;

                    Log.d("Bruh", name);
                    Log.d("Bruh", address);
                    Log.d("Bruh", phoneno);

                    RV = findViewById(R.id.RV);
                    RV.setHasFixedSize(true);
                    RV.setLayoutManager(new LinearLayoutManager(hosp_home.this));

                    HospDeet[] HospDeet = new HospDeet[]{
                            new HospDeet("A", name, address, phoneno, AP, AN),
                            new HospDeet("B", name, address, phoneno, BP, BN),
                            new HospDeet("AB", name, address, phoneno, ABP, ABN),
                            new HospDeet("O", name, address, phoneno, OP, ON),
                    };

                    HospAdapter HospAdapter = new HospAdapter(HospDeet, hosp_home.this);
                    RV.setAdapter(HospAdapter);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(hosp_home.this, "Something went wrong!", Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public void onBackPressed() { }
}

