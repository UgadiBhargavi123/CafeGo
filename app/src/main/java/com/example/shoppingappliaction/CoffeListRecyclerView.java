package com.example.shoppingappliaction;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

class CoffeListRecyclerView extends RecyclerView.Adapter<CoffeListRecyclerView.ViewHolder> {

    private List<String> coffee_name;
    private Context context1;
    private int selectedPosition = -1;
    private CoffeeName coffeeNameListener;
    boolean isCoffeePosition;

    public CoffeListRecyclerView(List<String> coffee_list,boolean isCoffee, Context context, CoffeeName coffeeNameListener) {
        this.coffee_name = coffee_list;
        this.context1 = context;
        this.isCoffeePosition = isCoffee;
        this.coffeeNameListener = coffeeNameListener;
    }

    @NonNull
    @Override
    public CoffeListRecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.coffee_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CoffeListRecyclerView.ViewHolder holder, int position) {
        holder.coffe_name_tv.setText(coffee_name.get(position));

        if(isCoffeePosition){
            isCoffeePosition =false;
            holder.coffe_name_tv.setTextColor(Color.WHITE);
            holder.cardView.setCardBackgroundColor(ContextCompat.getColor(context1, R.color.orange));
            coffeeNameListener.setcoffeeName(coffee_name.get(position),false);

        }
        else {
            if (position == selectedPosition) {
                holder.coffe_name_tv.setTextColor(Color.WHITE);
                holder.cardView.setCardBackgroundColor(ContextCompat.getColor(context1, R.color.orange));
            } else {
                holder.coffe_name_tv.setTextColor(Color.BLACK);
                holder.cardView.setCardBackgroundColor(ContextCompat.getColor(context1, R.color.white));
            }
        }



        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedPosition = position;
                notifyDataSetChanged();

                if (coffeeNameListener != null) {
                    coffeeNameListener.setcoffeeName(coffee_name.get(position),false);
                }
            }
        });
    }

    // In CoffeListRecyclerView.java
    // Update list dynamically and auto-select first item
    public void updateListAndSelectFirst(List<String> newList) {
        this.coffee_name = newList;
        selectedPosition = 0; // Auto-select first item
        notifyDataSetChanged();

        // Notify listener for first item selection
        if (coffeeNameListener != null && !newList.isEmpty()) {
            coffeeNameListener.setcoffeeName(newList.get(0), false);
        }
    }



    @Override
    public int getItemCount() {
        return coffee_name.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView coffe_name_tv;
        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            coffe_name_tv = itemView.findViewById(R.id.coffee_name_tv);
            cardView = itemView.findViewById(R.id.cardview);
        }
    }
}
