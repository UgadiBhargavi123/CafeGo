package com.example.shoppingappliaction;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class CartandLikeListRecyclerView extends RecyclerView.Adapter<CartandLikeListRecyclerView.ViewHolder> {

    private ArrayList<DetailsCoffeePojoClass> particularCoffeeRecyclerViewArrayList;
    private Context context;

    public CartandLikeListRecyclerView(ArrayList<DetailsCoffeePojoClass> particularCoffeeRecyclerViews, Context context){
        this.particularCoffeeRecyclerViewArrayList = particularCoffeeRecyclerViews;
        this.context = context;
    }

    @NonNull
    @Override
    public CartandLikeListRecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.like_recyclerview, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartandLikeListRecyclerView.ViewHolder holder, int position) {
        DetailsCoffeePojoClass item = particularCoffeeRecyclerViewArrayList.get(position);
        Toast.makeText(context, "size"+particularCoffeeRecyclerViewArrayList.size(), Toast.LENGTH_SHORT).show();

        if(item != null) {
            holder.subTv.setText(item.getExtraDetails() != null ? item.getExtraDetails() : "");
            holder.coffeeTv.setText(item.getCoffeeName() != null ? item.getCoffeeName() : "");
            holder.priceTv.setText(item.getMoney() != null ? item.getMoney() : "");
            if(item.getmImageView() != null){
                holder.imageView.setImageBitmap(item.getmImageView());
            }
        }
        else {
            Toast.makeText(context, "empty", Toast.LENGTH_SHORT).show();
        }

        holder.selectedItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File cachePath = new File(context.getCacheDir(), "images");
                cachePath.mkdirs();
                File file = new File(cachePath, "coffee.png");
                try (FileOutputStream stream = new FileOutputStream(file)) {
                    particularCoffeeRecyclerViewArrayList.get(position)
                            .getmImageView()
                            .compress(Bitmap.CompressFormat.PNG, 100, stream);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                // Create URI for the saved file
                Uri imageUri = Uri.fromFile(file);


                Intent intent = new Intent(context,ViewDetailsActivity.class);
                intent.putExtra("coffee_name", item.getCoffeeName());
                intent.putExtra("cart_like_view", true);
                intent.putExtra("extra_details", item.getExtraDetails());
                intent.putExtra("image_bitmap",imageUri.toString());

                intent.putExtra("money", item.getMoney());
                context.startActivity(intent);
            }
        });



    }

    @Override
    public int getItemCount() {
        return particularCoffeeRecyclerViewArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        CardView selectedItem;
        TextView subTv, coffeeTv, priceTv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.cof_iv);
            subTv = itemView.findViewById(R.id.subTv);
            coffeeTv = itemView.findViewById(R.id.coffeeTv);
            selectedItem = itemView.findViewById(R.id.selectedItem);
            priceTv = itemView.findViewById(R.id.priceTV);
        }
    }
}
