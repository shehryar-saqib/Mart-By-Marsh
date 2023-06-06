package com.example.martbymarsh;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class ResetAddress extends AppCompatActivity {

    EditText newAddress;
    EditText newPhoneNumber;
    Button save;
    Button save_phone;
    FirebaseAuth mAuth;
    FirebaseFirestore fStore;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_address);

        newAddress = findViewById(R.id.changeAdd);
        save = findViewById(R.id.submit);
        newPhoneNumber=findViewById(R.id.editTextPhone);
        //save_phone=findViewById(R.id.phone_number1);
        //String newadd = newAddress.getText().toString();
        mAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        try {

            id = mAuth.getCurrentUser().getUid();

            save.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    DocumentReference doc = fStore.collection("student").document(id);
                    String newadd = newAddress.getText().toString();
                    String phonee= newPhoneNumber.getText().toString();
                    doc.update("Address", newadd,"Phone Number", phonee).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Context context=v.getContext();
                                Intent intent1=new Intent();
                                intent1 =  new Intent(context, HomeActivity.class);
                                // intent.putExtra("img",model.getItem());
                                context.startActivity(intent1);
                                Toast.makeText(getApplicationContext(), "Information updated", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(getApplicationContext(), " NOT UPDATED", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                /*
                //Map<String,Object> user1 = new HashMap<>();
                //user1.put("Address",newAddress);
                doc.set(user1).addOnSuccessListener((OnSuccessListener) (aVoid) -> {
                    Log.d("TAG","SICC"+id);
                } ).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("TAG","FAILED"+ e.toString());
                    }
                });*/
                }

            });
            /*
            save_phone.setOnClickListener(new View.OnClickListener() {

                public void onClick(View v){
                    DocumentReference doc = fStore.collection("student").document(id);
                    String phonee= newPhoneNumber.getText().toString();
                    doc.update("Phone Number", phonee).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(getApplicationContext(), "UPDATED", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(getApplicationContext(), " NOT UPDATED", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
            });
*/
        } catch (Exception e ){

        }

    }
}