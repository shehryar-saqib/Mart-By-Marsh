package com.example.martbymarsh;

import android.content.Context;
import android.content.Intent;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.Map;
import java.util.concurrent.Executor;

import static java.lang.Thread.sleep;

public class MainAdapter extends FirebaseRecyclerAdapter<model,MainAdapter.myViewHolder>{
    public int a =0;
    public MainAdapter(@NonNull FirebaseRecyclerOptions<model> options) {

        super(options);

    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull model model) {
        holder.item1.setText((model.getItem()).toString());
        holder.price.setText((Integer.toString(model.getPrice())));
        Log.d("TAG",model.getImg());
        Glide.with(holder.image.getContext()).load(model.getImg()).placeholder(R.drawable.blank)
                .error(R.drawable.common_google_signin_btn_icon_dark_normal).into(holder.image);



            holder.image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try{
                    DatabaseReference reff;
                    DatabaseReference countreff;
                    FirebaseFirestore fStore;
                    FirebaseAuth mAuth;
                    mAuth = FirebaseAuth.getInstance();
                    fStore = FirebaseFirestore.getInstance();
                    String id = mAuth.getCurrentUser().getUid();
                    DocumentReference doc = fStore.collection("student").document(id);
                    doc.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                            User user = documentSnapshot.toObject(User.class);
                            doc.update("total", user.getTotal() + model.getPrice());
                            DatabaseReference reff;
                            FirebaseDatabase root;
                            DatabaseReference countreff;
                            FirebaseFirestore fStore;
                            FirebaseAuth mAuth;
                            mAuth = FirebaseAuth.getInstance();
                            fStore = FirebaseFirestore.getInstance();
                            root = FirebaseDatabase.getInstance();

                            reff = root.getReference("Cart").child(id).child(String.valueOf(user.getCount()));
                            reff.child("image3").setValue(model.getImg());
                            reff.child("item3").setValue(model.getItem());
                            //
                            reff.child("price3").setValue(model.getPrice());
                            // int to = model.getPrice();
                            //   int too = reff.child("total").);
                            //count co = documentSnapshot.toObject(count.class);
                            doc.update("count", user.getCount() + 1);

                            Log.d("Cont", String.valueOf(user.getCount()));
                        }

                    });
                    // try {
                    Context context = v.getContext();
                    Intent intent1 = new Intent();

                    intent1 = new Intent(context, Cart.class);
                    // intent.putExtra("img",model.getItem());
                    context.startActivity(intent1);
                    //}catch (Exception e){
                    //Toast.makeText(Product"You're logged in as guest", Toast.LENGTH_LONG).show();
                    // }
                    }catch (Exception e){
                    }
                    }

            });

    }
    public void addcart(model model){
     //   /*
        FirebaseDatabase root;
        DatabaseReference reff;
        DatabaseReference countreff;
        FirebaseFirestore fStore;
        FirebaseAuth mAuth;
    //    int a = 1;
        mAuth = FirebaseAuth.getInstance();
        root = FirebaseDatabase.getInstance();
        fStore = FirebaseFirestore.getInstance();

        // */

        String id = mAuth.getCurrentUser().getUid();

        DocumentReference doc = fStore.collection("student").document(id);
        doc.addSnapshotListener((Executor) this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                String c = (value.getString("Phone Number"));
                //email.setText(value.getString("Email"));
                FirebaseDatabase root;
                DatabaseReference reff;
                DatabaseReference countreff;
                FirebaseFirestore fStore;
                FirebaseAuth mAuth;
                //    int a = 1;
                //mAuth = FirebaseAuth.getInstance();
                root = FirebaseDatabase.getInstance();
                //fStore = FirebaseFirestore.getInstance();
                String count = (value.getString("Address"));
                //Log.d("cunt", String.valueOf(count));
                //reff = root.getReference("Cart").child(id).child(String.valueOf(count));
                //reff.child("image3").setValue(model.getImg());
                //reff.child("item3").setValue(model.getItem());
                //
                // reff.child("price3").setValue(model.getPrice());
                //doc.update("count",count+1);
            }
        });
        /*
        DocumentReference doc = fStore.collection("Posts").document(id);
        doc.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                FirebaseDatabase root;
                DatabaseReference reff;
                DatabaseReference countreff;
                FirebaseFirestore fStore;
                FirebaseAuth mAuth;
                //    int a = 1;
                //mAuth = FirebaseAuth.getInstance();
                root = FirebaseDatabase.getInstance();
                //fStore = FirebaseFirestore.getInstance();
                int count = Integer.parseInt(documentSnapshot.getString("count"));
                //Log.d("cunt", String.valueOf(count));
                //reff = root.getReference("Cart").child(id).child(String.valueOf(count));
                //reff.child("image3").setValue(model.getImg());
                //reff.child("item3").setValue(model.getItem());
                //
                // reff.child("price3").setValue(model.getPrice());
                //doc.update("count",count+1);
            }

        });

         */


//        doc.update("Address", newadd,"Phone Number", phonee);



        //reff = root.getReference("Cart").child(id).child(String.valueOf(a));

//        a=a+1;

        //int b = Integer.parseInt(countreff.getKey());
       // int a = Integer.parseInt(countreff.getKey())+1;
    //    Log.d("count", String.valueOf(a));
        //countreff.setValue(String.valueOf(Integer.parseInt(countreff.getKey())+1));


       // countreff.setValue(Integer.parseInt(countreff.getKey())+1);
    }

    @NonNull

    @Override
    public myViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //String TAG = "TAG";
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
       // Log.d(TAG, "Parent is " + view.getParent());
        return new myViewHolder(view);
    }


    class myViewHolder extends RecyclerView.ViewHolder{

        ImageView image;
        TextView item1,price;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            image = (ImageView)itemView.findViewById(R.id.image1);
            item1 = (TextView) itemView.findViewById(R.id.item1);
            price = (TextView)itemView.findViewById(R.id.price1);



        }
    }
}
