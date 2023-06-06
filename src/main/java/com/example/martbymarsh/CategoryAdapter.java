package com.example.martbymarsh;

import android.content.Context;
import android.content.Intent;
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

public class CategoryAdapter extends FirebaseRecyclerAdapter<CategoryModel,CategoryAdapter.viewHolder> {

    public CategoryAdapter(@NonNull FirebaseRecyclerOptions<CategoryModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull CategoryAdapter.viewHolder holder, int position, @NonNull CategoryModel CategoryModel) {
        holder.category.setText((CategoryModel.getProduct()).toString());
        Glide.with(holder.img.getContext()).load(CategoryModel.getImage()).placeholder(R.drawable.blank)
                .error(R.drawable.common_google_signin_btn_icon_dark_normal).into(holder.img);

        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context=v.getContext();
                Intent intent=new Intent();
                intent =  new Intent(context, Products.class);
                intent.putExtra("category",CategoryModel.getProduct());
                context.startActivity(intent);
            }

        });
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.categoryxml,parent,false);
        // Log.d(TAG, "Parent is " + view.getParent());
        return new viewHolder(view);
    }

    class viewHolder extends RecyclerView.ViewHolder{

        ImageView img;
        TextView category;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            img = (ImageView)itemView.findViewById(R.id.image2);
            category = (TextView) itemView.findViewById(R.id.category_name);




        }
    }
}
