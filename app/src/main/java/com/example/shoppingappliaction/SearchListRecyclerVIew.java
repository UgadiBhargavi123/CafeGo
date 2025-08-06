package com.example.shoppingappliaction;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class SearchListRecyclerVIew extends RecyclerView.Adapter<SearchListRecyclerVIew.ViewHolder>  {
    private ArrayList<DetailsCoffeePojoClass> coffeeList;
    private ArrayList<DetailsCoffeePojoClass> coffeeListFull;
    CoffeeName coffeeName;

    public SearchListRecyclerVIew(Context context, ArrayList<DetailsCoffeePojoClass> coffeeList, CoffeeName coffeeName) {
        this.coffeeList = coffeeList;
        this.coffeeName = coffeeName;
        this.coffeeListFull = new ArrayList<>(coffeeList); // Original copy
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchListRecyclerVIew.ViewHolder holder, int position) {
        DetailsCoffeePojoClass currentItem = coffeeList.get(position);
        holder.coffeeImageView.setImageBitmap(currentItem.getmImageView());
        holder.coffeeNameTextView.setText(currentItem.getCoffeeName());

        holder.itemView.setOnClickListener(v -> coffeeName.setcoffeeName(currentItem.getCoffeeName(), true));
    }

    @Override
    public int getItemCount() {
        return coffeeList.size();
    }

    public void filterList(ArrayList<DetailsCoffeePojoClass> filterdNames) {
        this.coffeeList = filterdNames;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView coffeeImageView;
        TextView coffeeNameTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            coffeeImageView = itemView.findViewById(R.id.coffee_iv);
            coffeeNameTextView = itemView.findViewById(R.id.coffeeName_tv);
        }
    }
}
