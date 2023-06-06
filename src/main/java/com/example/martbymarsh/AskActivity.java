package com.example.martbymarsh;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class AskActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask);
        final Button sign_in_button = findViewById(R.id.button);
        final Button sign_up_button = findViewById(R.id.button2);
        final Button visit_button = findViewById(R.id.button3);
        sign_in_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AskActivity.this, SigninActivity.class);
                startActivity(intent);
            }
        });
        sign_up_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AskActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });

        visit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AskActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
        try{
            FirebaseAuth.getInstance().signOut();
        } catch (Exception e){
        }
    }
}