package com.example.shoppingappliaction;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

public class ParticularCoffeeRecyclerView extends RecyclerView.Adapter<ParticularCoffeeRecyclerView.ParticularViewHolder> {
    Context context1;
    ParticularCoffeeDetails particularCoffeeDetails1;
    ArrayList<DetailsCoffeePojoClass> detailsCoffeePojoClassArrayList;


    public ParticularCoffeeRecyclerView(ArrayList<DetailsCoffeePojoClass> detailsCoffeePojoClassArrayList,Context context, ParticularCoffeeDetails particularCoffeeDetails) {
        this.context1 = context;
        this.detailsCoffeePojoClassArrayList = detailsCoffeePojoClassArrayList;
        this.particularCoffeeDetails1 = particularCoffeeDetails;
    }

    @NonNull
    @Override
    public ParticularCoffeeRecyclerView.ParticularViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.particularcofeedata,parent,false);
        ParticularViewHolder holder = new ParticularViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ParticularCoffeeRecyclerView.ParticularViewHolder holder, int position) {
        holder.coffee_img.setImageBitmap(detailsCoffeePojoClassArrayList.get(position).getmImageView());

        holder.coffee_name_tv.setText(detailsCoffeePojoClassArrayList.get(position).getCoffeeName());
        holder.coffee_name_details_tv.setText(detailsCoffeePojoClassArrayList.get(position).getExtraDetails());
        holder.money_tv.setText("$ " + detailsCoffeePojoClassArrayList.get(position).getMoney());

        View.OnClickListener clickListener = v -> {
            try {
                // Save bitmap to cache
                File cachePath = new File(context1.getCacheDir(), "images");
                cachePath.mkdirs();
                File file = new File(cachePath, "coffee.png");
                try (FileOutputStream stream = new FileOutputStream(file)) {
                    detailsCoffeePojoClassArrayList.get(position)
                            .getmImageView()
                            .compress(Bitmap.CompressFormat.PNG, 100, stream);
                }

                // Create URI for the saved file
                Uri imageUri = Uri.fromFile(file);

                // Call interface method with URI string instead of byte[]
                String action = (v.getId() == R.id.addCart) ? "add" : "view";
                particularCoffeeDetails1.getParticularCoffeeDetails(
                        imageUri,
                        detailsCoffeePojoClassArrayList.get(position).getCoffeeName(),
                        detailsCoffeePojoClassArrayList.get(position).getExtraDetails(),
                        detailsCoffeePojoClassArrayList.get(position).getMoney(),
                        action
                );

            } catch (Exception e) {
                e.printStackTrace();
            }
        };

        holder.addCart.setOnClickListener(clickListener);
        holder.coffee_cardview.setOnClickListener(clickListener);
    }

    @Override
    public int getItemCount() {
        return detailsCoffeePojoClassArrayList.size();
    }

    public class ParticularViewHolder extends RecyclerView.ViewHolder {
        ImageView coffee_img;
        TextView coffee_name_tv,coffee_name_details_tv,money_tv;
        CardView addCart,coffee_cardview;


        public ParticularViewHolder(@NonNull View itemView) {
            super(itemView);
            coffee_img = itemView.findViewById(R.id.coffee_img);
            coffee_name_tv = itemView.findViewById(R.id.coffee_name_tv);
            coffee_name_details_tv = itemView.findViewById(R.id.coffee_name_details_tv);
            money_tv = itemView.findViewById(R.id.money_tv);
            addCart = itemView.findViewById(R.id.addCart);
            coffee_cardview = itemView.findViewById(R.id.coffee_cardview);
        }

    }


}
