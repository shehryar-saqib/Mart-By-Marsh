package com.example.martbymarsh;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class UserProfile extends AppCompatActivity {

    TextView email,phone_no;
    FirebaseFirestore fStore;
    FirebaseAuth mAuth;
    String id;
    Button forget;
    Button add;
    boolean logged = false;
    boolean reset = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.backbtn);

        try {
            phone_no = findViewById(R.id.number);
            email = findViewById(R.id.Email);
            forget = findViewById(R.id.forget_pass);
            add = findViewById(R.id.addChange);
            mAuth = FirebaseAuth.getInstance();
            fStore = FirebaseFirestore.getInstance();
        }catch (Exception e){}

        try {

            id = mAuth.getCurrentUser().getUid();
            //Log.d("TAG",id);


            id = mAuth.getCurrentUser().getUid();
            //if (id.equals(null)){
            DocumentReference doc = fStore.collection("student").document(id);
            doc.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
                @Override
                public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                    phone_no.setText(value.getString("Phone Number"));
                    email.setText(value.getString("Email"));
                    //String c = value.getString("count");
                }
            });
            logged=true;
            // }e
        } catch (Exception e) {
            logged = false;
        }
        //if (logged == true) {
            forget.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (logged == true) {
                       // mAuth.signOut();
                        //FirebaseAuth.getInstance().signOut();
                    String e = email.getText().toString();
                    mAuth.sendPasswordResetEmail(e).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                //mAuth.signOut();
                                //reset=true;
                                Toast.makeText(getApplicationContext(), "Check your Email", Toast.LENGTH_LONG).show();
                                Intent i = new Intent(UserProfile.this, SigninActivity.class);
                                startActivity(i);
                                //mAuth.signOut();
                                finish();
                            } else {
                                Toast.makeText(getApplicationContext(), "ERROR", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
                    if (reset){
                        FirebaseAuth.getInstance().signOut();
                    }
                }
            });
        //}
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String id = mAuth.getCurrentUser().getUid();
                    Intent i = new Intent(UserProfile.this, ResetAddress.class);
                    startActivity(i);
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(),"You're a Guest!",Toast.LENGTH_LONG).show();
                }
            }
        });


    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        onBackPressed();
        return super.onOptionsItemSelected(item);
    }



}