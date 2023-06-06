package com.example.martbymarsh;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.sax.StartElementListener;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ResetPass extends AppCompatActivity {

    EditText email;
    Button forget;
    String emailText;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_pass);

        mAuth = FirebaseAuth.getInstance();

        email = findViewById(R.id.reset_email);
        forget = findViewById(R.id.forgetbtn);

        forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changepass();
            }
        });
    }
    public void changepass(){
        emailText = email.getText().toString();
        if (emailText.isEmpty()) {
            email.setError("Full name required");
            email.requestFocus();
            return;
        } else {
            mAuth.sendPasswordResetEmail(emailText).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(getApplicationContext(),"Check your Email", Toast.LENGTH_LONG).show();
                        Intent i = new Intent(ResetPass.this,SigninActivity.class);
                        startActivity(i);
                        finish();
                    }
                    else {
                        Toast.makeText(getApplicationContext(),"ERROR OK", Toast.LENGTH_LONG).show();
                    }
                }
            });
        }

    }
}