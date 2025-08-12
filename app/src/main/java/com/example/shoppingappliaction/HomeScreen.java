package com.example.shoppingappliaction;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class HomeScreen extends AppCompatActivity implements CoffeeName, ParticularCoffeeDetails {

    RecyclerView coffee_list_recyclerview, particularCoffeeDetails_recyclerview, searchRecyclerVIew;
    CoffeListRecyclerView coffee_list_Adapter;
    List<String> coffee_list;
    ParticularCoffeeRecyclerView particularCoffeeRecyclerViewAdapter;
    ArrayList<DetailsCoffeePojoClass> detailsCoffeePojoClassArrayList, searchCoffeePojoClassArrayList;
    TextView currentLocation_tv;
    ArrayAdapter<String> listViewAdapter;
    EditText coffeeName_et;
    ImageView search_iv;
    RelativeLayout search_rl;
    String coffeeName,current_place;
    SearchListRecyclerVIew searchListRecyclerVIew;
    List<String> originalCoffeeList;  // ✅ Backup original list


    private TextWatcher textWatcher; // ✅ Fixed initialization

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        // Setup UI
        coffee_list = new ArrayList<>();
        coffee_list.add("Cappuccino");
        coffee_list.add("Espresso");
        coffee_list.add("Latte");
        coffee_list.add("Mochiato");

        originalCoffeeList = new ArrayList<>(coffee_list);

        detailsCoffeePojoClassArrayList = new ArrayList<>();

        coffeeName_et = findViewById(R.id.searchView);
        search_iv = findViewById(R.id.search_iv);
        searchRecyclerVIew = findViewById(R.id.recyclerView);
        search_rl = findViewById(R.id.search_rl);
        coffee_list_recyclerview = findViewById(R.id.coffee_list_recyclerview);
        particularCoffeeDetails_recyclerview = findViewById(R.id.particular_coffe_recyclerview);
        currentLocation_tv = findViewById(R.id.current_location_tv);

        Bundle bundle = getIntent().getExtras();
        if(bundle!= null){
            current_place = bundle.getString("current_place");
            if(current_place != null){
                currentLocation_tv.setText(current_place);
            }
        }

        // Setup coffee list
        coffee_list_recyclerview.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        coffee_list_Adapter = new CoffeListRecyclerView(coffee_list, true, this, this);
        coffee_list_recyclerview.setAdapter(coffee_list_Adapter);

        // Setup search list
        searchCoffeePojoClassArrayList = new ArrayList<>();
        searchCoffeePojoClassArrayList.add(new DetailsCoffeePojoClass(BitmapFactory.decodeResource(getResources(), R.drawable.cap_1), "Cappuccino", "", ""));
        searchCoffeePojoClassArrayList.add(new DetailsCoffeePojoClass(BitmapFactory.decodeResource(getResources(), R.drawable.esp_1), "Espresso", "", ""));
        searchCoffeePojoClassArrayList.add(new DetailsCoffeePojoClass(BitmapFactory.decodeResource(getResources(), R.drawable.lat_1), "Latte", "", ""));
        searchCoffeePojoClassArrayList.add(new DetailsCoffeePojoClass(BitmapFactory.decodeResource(getResources(), R.drawable.moc_1), "Mochiato", "", ""));

        searchListRecyclerVIew = new SearchListRecyclerVIew(this, searchCoffeePojoClassArrayList, this);
        searchRecyclerVIew.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        searchRecyclerVIew.setAdapter(searchListRecyclerVIew);

        // ✅ Proper TextWatcher initialization
        textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                if (s.toString().isEmpty()) {
                    // ✅ Restore original list
                    coffee_list_recyclerview.post(() -> {
                        coffee_list_Adapter.updateListAndSelectFirst(originalCoffeeList);
                    });
                    searchRecyclerVIew.setVisibility(View.GONE);
                } else {
                    searchRecyclerVIew.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                filter(editable.toString());
            }
        };
        coffeeName_et.addTextChangedListener(textWatcher);

        // Setup details recycler view
        particularCoffeeDetails_recyclerview.setLayoutManager(new GridLayoutManager(this, 2));
    }

    // ✅ Called when coffee is selected from list or search
    @Override
    public void setcoffeeName(String coffeeName, boolean fromSearchList) {
        if (fromSearchList) {
            // Only update the EditText if called from search list click
            coffeeName_et.removeTextChangedListener(textWatcher);
            coffeeName_et.setText(coffeeName);
            coffeeName_et.setSelection(coffeeName.length()); // move cursor to end
            coffeeName_et.addTextChangedListener(textWatcher);
            searchRecyclerVIew.setVisibility(View.GONE);
            coffee_list_recyclerview.post(() -> {
                List<String> newCoffeeList = new ArrayList<>();
                newCoffeeList.add(coffeeName);
                coffee_list_Adapter.updateListAndSelectFirst(newCoffeeList);
            });
        }


        // Now load the correct coffee variant details
        detailsCoffeePojoClassArrayList.clear();

        Bitmap bitmap = null, bitmap1 = null, bitmap2 = null, bitmap3 = null;

        if(coffeeName.equalsIgnoreCase("Cappuccino")){

            bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.img1);
            bitmap1 = BitmapFactory.decodeResource(getResources(),R.drawable.cap_1);
            bitmap2 = BitmapFactory.decodeResource(getResources(),R.drawable.cap_2);
            bitmap3 = BitmapFactory.decodeResource(getResources(),R.drawable.cap_3);
        }
        else if(coffeeName.equalsIgnoreCase("Espresso")) {
            bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.esp_1);
            bitmap1 = BitmapFactory.decodeResource(getResources(),R.drawable.esp_2);
            bitmap2 = BitmapFactory.decodeResource(getResources(),R.drawable.exp_3);
            bitmap3 = BitmapFactory.decodeResource(getResources(),R.drawable.esp_4);
        }
        else if(coffeeName.equalsIgnoreCase("Latte")) {
            bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.lat_1);
            bitmap1 = BitmapFactory.decodeResource(getResources(),R.drawable.lat_2);
            bitmap2 = BitmapFactory.decodeResource(getResources(),R.drawable.lat_3);
            bitmap3 = BitmapFactory.decodeResource(getResources(),R.drawable.lat_4);
        }
        else {
            bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.moc_1);
            bitmap1 = BitmapFactory.decodeResource(getResources(),R.drawable.moc_2);
            bitmap2 = BitmapFactory.decodeResource(getResources(),R.drawable.moc_3);
            bitmap3 = BitmapFactory.decodeResource(getResources(),R.drawable.moc_4);
        }

        // Add variants
        detailsCoffeePojoClassArrayList.add(new DetailsCoffeePojoClass(bitmap, coffeeName, "with Chocolate", "4.53"));
        detailsCoffeePojoClassArrayList.add(new DetailsCoffeePojoClass(bitmap1, coffeeName, "with Oreo", "4.73"));
        detailsCoffeePojoClassArrayList.add(new DetailsCoffeePojoClass(bitmap2, coffeeName, "with KitKat", "4.83"));
        detailsCoffeePojoClassArrayList.add(new DetailsCoffeePojoClass(bitmap3, coffeeName, "with Milk", "4.93"));

        if (particularCoffeeRecyclerViewAdapter == null) {
            particularCoffeeRecyclerViewAdapter = new ParticularCoffeeRecyclerView(detailsCoffeePojoClassArrayList, this, this);
            particularCoffeeDetails_recyclerview.setAdapter(particularCoffeeRecyclerViewAdapter);
        } else {
            particularCoffeeRecyclerViewAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void getParticularCoffeeDetails(Uri bitmap, String coffeeName, String extraDetails, String money, String add_view) {
        if (!add_view.equalsIgnoreCase("add")) {
            Intent intent = new Intent(HomeScreen.this, ViewDetailsActivity.class);
            intent.putExtra("coffee_name", coffeeName);
            intent.putExtra("extra_details", extraDetails);
            intent.putExtra("image_bitmap",bitmap.toString());
            intent.putExtra("money", money);
            startActivity(intent);
        }
        Toast.makeText(this, coffeeName + "__" + extraDetails + "__" + money + "__" + add_view, Toast.LENGTH_SHORT).show();
    }

    // ✅ Fixed filter method
    private void filter(String text) {
        ArrayList<DetailsCoffeePojoClass> filteredList = new ArrayList<>();

        for (DetailsCoffeePojoClass item : searchCoffeePojoClassArrayList) {
            if (item.getCoffeeName().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }

        searchListRecyclerVIew.filterList(filteredList);
    }
}
