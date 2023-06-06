package com.example.martbymarsh;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Gravity;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewFlipper;
public class HomeActivity extends AppCompatActivity {
    NavigationView nav;
    ActionBarDrawerToggle toggle;
    Toolbar toolbar;
    private DrawerLayout drawer;
    public int a=0;
    ViewFlipper flipper;
    RecyclerView recyclerView;
    //MainAdapter mainAdapter;
    CategoryAdapter categoryAdapter;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        recyclerView = (RecyclerView)findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAuth= FirebaseAuth.getInstance();

        FirebaseRecyclerOptions<CategoryModel> options =
                new FirebaseRecyclerOptions.Builder<CategoryModel>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child("categories"),CategoryModel.class)
                .build();

//*/

        // Creating sliding image view


       // toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_baseline_menu_24);

     //   int[] sliderImageId = new int[]{
       //         R.drawable._07544,R.drawable._45556,R.drawable._792093,R.drawable._07544, R.drawable._793242, R.drawable._805696
        //};
        int[] sliderImageId = new int[]{
                R.drawable.groc,R.drawable.groc2,R.drawable.groc3};
        flipper=(ViewFlipper)findViewById(R.id.flipper);

        drawer = findViewById(R.id.drawer);
        nav = (NavigationView)findViewById(R.id.nav_menu);
        toggle = new ActionBarDrawerToggle(this, drawer,R.string.open,R.string.close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.menu_home:
                        Toast.makeText(HomeActivity.this, "You're already on Home Page", Toast.LENGTH_SHORT).show();
                        // Home button makes the display jump to the same activity closing the navigation drawer
                        //Intent intent = new Intent(HomeActivity.this , HomeActivity.class);
                        //startActivity(intent);
                        drawer.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.user_profile:
                        Intent in=new Intent(HomeActivity.this,UserProfile.class);
                        startActivity(in);
                        drawer.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.cart:
                        try {

                            String id = mAuth.getCurrentUser().getUid();
                            Intent in2 = new Intent(HomeActivity.this, Cart.class);
                            startActivity(in2);
                        }catch (Exception e){
                            drawer.closeDrawer(GravityCompat.START);
                            Toast.makeText(getApplicationContext(),"You're logged in as guest!",Toast.LENGTH_LONG).show();
                        }
                        break;
                    case R.id.logout:
                        try {
                            String id = FirebaseAuth.getInstance().getCurrentUser().getUid();
                            FirebaseAuth.getInstance().signOut();
                            Intent in1 = new Intent(HomeActivity.this, AskActivity.class);
                            startActivity(in1);
                            drawer.closeDrawer(GravityCompat.START);
                        } catch(Exception e) {
                            Intent in22 = new Intent(HomeActivity.this, SigninActivity.class);
                            startActivity(in22);
                            Toast.makeText(getApplicationContext(),"You're a guest!", Toast.LENGTH_LONG).show();
                            drawer.closeDrawer(GravityCompat.START);
                        }
                        break;
                }
                return true;
            }
        });

        for (int i=0; i<sliderImageId.length;i++){
            showImage(sliderImageId[i]);
        }
        //mainAdapter = new MainAdapter(options);
        categoryAdapter = new CategoryAdapter(options);
        recyclerView.setAdapter(categoryAdapter);
    }

    public void showImage(int img){
        ImageView imageView = new ImageView(this);
        imageView.setBackgroundResource(img);

        flipper.addView(imageView);
        flipper.setFlipInterval(3000);
        flipper.setAutoStart(true);
        flipper.setInAnimation(this, android.R.anim.fade_in);
        flipper.setOutAnimation(this, android.R.anim.fade_out);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (a%2==0){
            drawer.openDrawer(GravityCompat.START);
            a=a+1;
        } else {
            drawer.closeDrawer(GravityCompat.START);
            a=a+1;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    //*
    @Override
    protected void onStart() {
        super.onStart();
        categoryAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        categoryAdapter.stopListening();
    }

     //*/


}



