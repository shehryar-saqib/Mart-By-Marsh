package com.example.martbymarsh;

import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

//import com.google.firebase.auth.FirebaseAuth;

import java.util.HashMap;
import java.util.Map;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class SignupActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Spinner city_spin, area_spin;
    private String city, area;
    private String[] city_arr, area_arr;
    private EditText n, e, p, p1, p2, p3;
    private TextView login;
    private FirebaseAuth mAuth;
    private Button submit_button;
    private String name, email, phone_num, address, password, password_2;
    private ProgressBar pBar;
    FirebaseFirestore fStore;
    String user_id;
   // DatabaseReference Root;
    //DatabaseReference refference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        //    mAuth = FirebaseAuth.getInstance();

        n = findViewById(R.id.name);
        e = findViewById(R.id.email);
        p = findViewById(R.id.PhoneNumber);
        p1 = findViewById(R.id.editTextTextPostalAddress);
        p2 = findViewById(R.id.editTextTextPassword);
        p3 = findViewById(R.id.editTextTextPassword2);
        name = n.getText().toString();
        email = e.getText().toString();
        phone_num = p.getText().toString();
        address = p1.getText().toString();
        password = p2.getText().toString();
        password_2 = p3.getText().toString();
        pBar=(ProgressBar)findViewById(R.id.progressBar);

//      Getting area of the city
        city_arr = new String[]{"H-12", "H-13", "I-10", "G-9"};
        city_spin = findViewById(R.id.city);
        city_spin.setOnItemSelectedListener(this);
        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, city_arr);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        city_spin.setAdapter(aa);
        city = city_arr[city_spin.getSelectedItemPosition()];


//      Condition to check if passwords match or not
        boolean cond;
        if (password.equals(password_2)) {
            cond = true;
        } else {
            cond = false;
        }
        if (cond == false) {
            Toast.makeText(this, "The passwords do not match!", Toast.LENGTH_SHORT).show();
            password_2 = "";
        }


//      Submit Button
        submit_button = (Button) findViewById(R.id.submit_button);
        submit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = n.getText().toString();
                email = e.getText().toString();
                phone_num = p.getText().toString();
                address = p1.getText().toString();
                password = p2.getText().toString();
                password_2 = p3.getText().toString();
                pBar.setVisibility(View.VISIBLE);

                String email = e.getText().toString().trim();
                String name = n.getText().toString().trim();
                String pass = p2.getText().toString().trim();
                String pass2 = p3.getText().toString().trim();
                String address = p1.getText().toString().trim();
                String phone_num = p.getText().toString().trim();




                if (name.isEmpty()) {
                    n.setError("Full name required");
                    n.requestFocus();
                    return;
                }
                if (email.isEmpty()) {
                    e.setError("Email is required");
                    e.requestFocus();
                    return;
                }

                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    e.setError("Please give a proper email");
                }
                if (pass.isEmpty()) {
                    p2.setError("Password required");
                    p2.requestFocus();
                    return;
                }
                if (pass.length() < 6) {
                    p2.setError("Password should be greater than 6 letters");
                    p2.requestFocus();
                    return;
                }



                mAuth = FirebaseAuth.getInstance();
                fStore = FirebaseFirestore.getInstance();

                mAuth.createUserWithEmailAndPassword(email, pass)
                        .addOnCompleteListener(SignupActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    User user = new User(name, email,phone_num,address,pass,0,0);
                                    //refference.setValue(user);
                                    ///*
                                    user_id = mAuth.getCurrentUser().getUid();
                                    DocumentReference doc = fStore.collection("student").document(user_id);
                                    Map<String,Object> user1 = new HashMap<>();
                                    user1.put("Name",name);
                                    user1.put("Email",email);
                                    user1.put("Phone Number",phone_num);
                                    user1.put("Address",address);
                                    user1.put("count",0);
                                    user1.put("total",0);
                                    doc.set(user1).addOnSuccessListener((OnSuccessListener) (aVoid) -> {
                                        Log.d("TAG","SICC"+user_id);
                                    } ).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Log.d("TAG","FAILED"+ e.toString());
                                        }
                                    });

                                     //*/
                                   // insertData();
                                    FirebaseDatabase.getInstance().getReference("Users")
                                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                            .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                pBar.setVisibility(View.INVISIBLE);
                                                //insertData();
                                                Toast.makeText(SignupActivity.this, "Welcome \nPlease Log in to get started!", Toast.LENGTH_SHORT).show();
                                                Intent intent = new Intent(SignupActivity.this , SigninActivity.class);
                                                startActivity(intent);
                                            } else {
                                                pBar.setVisibility((View.INVISIBLE));
                                                Toast.makeText(getApplicationContext(), "REGISTRATION FAILED", Toast.LENGTH_LONG).show();
                                            }
                                        }
                                    });
                                    //insertData();
                                } else {
                                    pBar.setVisibility((View.INVISIBLE));
                                    Toast.makeText(getApplicationContext(), "REGISTRATION FAILED", Toast.LENGTH_LONG).show();
                                }

                            }
                        });



            }
        });

        // Click listener for Button on the bottom
        login = findViewById(R.id.SignUpToLogin);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignupActivity.this, SigninActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        ;
        ;
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        ;
        ;
    }


}





