package com.example.martbymarsh;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SigninActivity extends AppCompatActivity {

    ImageView backButton;
    private EditText a1, a2;
    private Button login;
    private TextView forget_pass, sign_up;
    private FirebaseAuth mAuth;
    //DatabaseReference reff;
    private String name, password;
    private boolean loggedin=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);


        mAuth= FirebaseAuth.getInstance();

        //reff = FirebaseDatabase.getInstance().getReference().child("Member");

        a1 = findViewById(R.id.name);
        a2 = findViewById(R.id.password);

        name = a1.getText().toString();
        password = a2.getText().toString();


        backButton = findViewById(R.id.backbtn);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            onBackPressed();
            }
        });


        login = (Button) findViewById(R.id.try_login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = a1.getText().toString().trim();
                password = a2.getText().toString().trim();
                if (name.isEmpty()) {
                    a1.setError("Full name required");
                    a1.requestFocus();
                    return;
                }
                mAuth.signInWithEmailAndPassword(name, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            loggedin=true;
                            Toast.makeText(SigninActivity.this, "Welcome \nPlease Log in to get started!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(SigninActivity.this , HomeActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(getApplicationContext(), "LOGIN FAILED!", Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });

        forget_pass = findViewById(R.id.forget_pass_button);
        forget_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SigninActivity.this, ResetPass.class);
                startActivity(i);
                finish();
            }
        });

        sign_up = findViewById(R.id.LoginToSignup);
        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SigninActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });
    }
    public boolean getLogged(){
        return loggedin;
    }
}