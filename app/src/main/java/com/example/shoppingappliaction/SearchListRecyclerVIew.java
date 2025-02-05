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

public class SearchListRecyclerVIew extends RecyclerView.Adapter<SearchListRecyclerVIew.ViewHolder> implements Filterable {
    private ArrayList<DetailsCoffeePojoClass> coffeeList;
    private ArrayList<DetailsCoffeePojoClass> coffeeListFull;
    CoffeeName coffeeName;

    public SearchListRecyclerVIew(Context context, ArrayList<DetailsCoffeePojoClass> coffeeList,CoffeeName coffeeName) {
        this.coffeeList = coffeeList;
        this.coffeeName = coffeeName;
        this.coffeeListFull = new ArrayList<>(coffeeList); // Copy of the original list
        getFilter();

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

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                coffeeName.setcoffeeName(currentItem.getCoffeeName(),"search");

            }
        });
    }



    @Override
    public int getItemCount() {
        return coffeeList.size();
    }

    @Override
    public Filter getFilter() {
        return coffeeFilter;
    }

    private Filter coffeeFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<DetailsCoffeePojoClass> filteredList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(coffeeListFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (DetailsCoffeePojoClass item : coffeeListFull) {
                    if (item.getCoffeeName().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            coffeeList.clear();
            coffeeList.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };

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

