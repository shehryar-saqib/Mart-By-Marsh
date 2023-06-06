package com.example.martbymarsh;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class Products extends AppCompatActivity {

    ImageView img;
    RecyclerView recyclerView1;
    MainAdapter mainAdapter;
    TextView Category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);

        recyclerView1= findViewById(R.id.recycler1);
        recyclerView1.setLayoutManager(new LinearLayoutManager(this));

        String category = getIntent().getStringExtra("category");

        Category = findViewById(R.id.cate);
        Category.setText(category);

        FirebaseRecyclerOptions<model> options =
                new FirebaseRecyclerOptions.Builder<model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child(category),model.class)
                        .build();
        mainAdapter = new MainAdapter(options);
        recyclerView1.setAdapter(mainAdapter);

    }
    @Override
    protected void onStart() {
        super.onStart();
        mainAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mainAdapter.stopListening();
    }

}