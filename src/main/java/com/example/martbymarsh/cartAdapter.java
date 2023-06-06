package com.example.martbymarsh;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
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
import com.google.firebase.firestore.FirebaseFirestore;

public class cartAdapter extends FirebaseRecyclerAdapter<cartModel,cartAdapter.ViewHolder>  {

    public cartAdapter(@NonNull FirebaseRecyclerOptions<cartModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull cartAdapter.ViewHolder holder, int position, @NonNull cartModel cartModel) {

            holder.item3.setText((cartModel.getItem3()).toString());
            holder.price3.setText((Integer.toString(cartModel.getPrice3())));
            //holder.quantity.setText((Integer.toString(cartModel.getQuantity())));
            //       Log.d("TAG",model.getImg());
            Glide.with(holder.image3.getContext()).load(cartModel.getImage3()).placeholder(R.drawable.blank)
                    .error(R.drawable.common_google_signin_btn_icon_dark_normal).into(holder.image3);



    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        int c = 0;
      //  if (c != 2) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cartitem, parent, false);
            // Log.d(TAG, "Parent is " + view.getParent());
            return new ViewHolder(view);


       // }
    }
    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView image3;
        TextView item3,price3;//,quantity;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            image3 = (ImageView)itemView.findViewById(R.id.image3);
            item3 = (TextView) itemView.findViewById(R.id.item3);
            price3 = (TextView)itemView.findViewById(R.id.price3);




        }
    }
}
