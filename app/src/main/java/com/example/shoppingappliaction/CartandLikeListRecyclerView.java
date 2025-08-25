package com.example.shoppingappliaction;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

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
            } else {
//                holder.imageView.setImageResource(R.drawable.placeholder_image); // fallback image
            }
        }
        else {
            Toast.makeText(context, "empty", Toast.LENGTH_SHORT).show();
        }

        // Example click listener
        holder.itemView.setOnClickListener(v ->
                Toast.makeText(context, "Clicked: " + item.getCoffeeName(), Toast.LENGTH_SHORT).show()
        );
    }

    @Override
    public int getItemCount() {
        return particularCoffeeRecyclerViewArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView subTv, coffeeTv, priceTv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.cof_iv);
            subTv = itemView.findViewById(R.id.subTv);
            coffeeTv = itemView.findViewById(R.id.coffeeTv);
            priceTv = itemView.findViewById(R.id.priceTV);
        }
    }
}
