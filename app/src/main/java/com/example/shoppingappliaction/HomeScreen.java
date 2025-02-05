package com.example.shoppingappliaction;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class HomeScreen extends AppCompatActivity implements CoffeeName,ParticularCoffeeDetails {

    RecyclerView coffee_list_recyclerview,particularCoffeeDetails_recyclerview,searchRecyclerVIew;
    CoffeListRecyclerView coffee_list_Adapter;
    List<String> coffee_list;
    ParticularCoffeeRecyclerView particularCoffeeRecyclerViewAdapter;
    ArrayList<DetailsCoffeePojoClass> detailsCoffeePojoClassArrayList,searchCoffeePojoClassArrayList;

    ArrayAdapter<String> listViewAdapter;
    EditText coffeeName_et;
    ImageView search_iv;
    RelativeLayout search_rl;
    String coffeeName;
    SearchListRecyclerVIew searchListRecyclerVIew;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        coffee_list = new ArrayList<>();
        coffee_list.add("Cappuccino");
        coffee_list.add("Espresso");
        coffee_list.add("Latte");
        coffee_list.add("Mochiato");
        detailsCoffeePojoClassArrayList = new ArrayList<>();
        coffeeName_et = findViewById(R.id.searchView);
        search_iv = findViewById(R.id.search_iv);
        searchRecyclerVIew = findViewById(R.id.recyclerView);
        search_rl = findViewById(R.id.search_rl);

        coffee_list_recyclerview = findViewById(R.id.coffee_list_recyclerview);
        particularCoffeeDetails_recyclerview = findViewById(R.id.particular_coffe_recyclerview);
        coffee_list_recyclerview.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        coffee_list_Adapter = new CoffeListRecyclerView(coffee_list, true,this, this);
        coffee_list_recyclerview.setAdapter(coffee_list_Adapter);
        listViewAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, coffee_list);
        coffeeName_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                listViewAdapter.getFilter().filter(s); // Filter ListView based on input
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        searchCoffeePojoClassArrayList = new ArrayList<>();
        searchCoffeePojoClassArrayList.add(new DetailsCoffeePojoClass(BitmapFactory.decodeResource(getResources(),R.drawable.cap_1),"Cappuccino","",""));
        searchCoffeePojoClassArrayList.add(new DetailsCoffeePojoClass(BitmapFactory.decodeResource(getResources(),R.drawable.esp_1),"Espresso","",""));
        searchCoffeePojoClassArrayList.add(new DetailsCoffeePojoClass(BitmapFactory.decodeResource(getResources(),R.drawable.lat_1),"Latte","",""));
        searchCoffeePojoClassArrayList.add(new DetailsCoffeePojoClass(BitmapFactory.decodeResource(getResources(),R.drawable.moc_1),"Mochiato","",""));
        searchListRecyclerVIew = new SearchListRecyclerVIew(getApplicationContext(),searchCoffeePojoClassArrayList,this);
        searchRecyclerVIew.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false));
        searchRecyclerVIew.setAdapter(searchListRecyclerVIew);


        coffeeName_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // Not needed
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.toString().isEmpty()){
                    searchRecyclerVIew.setVisibility(View.GONE);
                    searchListRecyclerVIew.getFilter().filter(s.toString());
                }
                else {
                    coffeeName_et.setText(coffeeName);
                    searchRecyclerVIew.setVisibility(View.VISIBLE);
                    searchListRecyclerVIew.getFilter().filter(s.toString());
                }

            }

            @Override
            public void afterTextChanged(Editable s) {
                // Not needed

            }
        });






        // Handle Search Icon Click (Optional)
        particularCoffeeDetails_recyclerview.setLayoutManager(new GridLayoutManager(this,2));


    }

    @Override
    public void setcoffeeName(String coffeeName,String search) {
        if(!search.isEmpty()){
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    coffeeName_et.setText(coffeeName);
                }
            });
            searchRecyclerVIew.setVisibility(View.GONE);

        }
        Bitmap bitmap = null , bitmap1=null, bitmap2 = null,bitmap3 = null;


        detailsCoffeePojoClassArrayList.clear();
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
        }else if(coffeeName.equalsIgnoreCase("Latte")) {
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
        detailsCoffeePojoClassArrayList.add(new DetailsCoffeePojoClass(bitmap,coffeeName,"with Choclate","4.53"));
        detailsCoffeePojoClassArrayList.add(new DetailsCoffeePojoClass(bitmap1,coffeeName,"with oreo","4.73"));
        detailsCoffeePojoClassArrayList.add(new DetailsCoffeePojoClass(bitmap2,coffeeName,"with kitkat","4.83"));
        detailsCoffeePojoClassArrayList.add(new DetailsCoffeePojoClass(bitmap3,coffeeName,"with milk","4.93"));
        if (particularCoffeeRecyclerViewAdapter == null) {
            particularCoffeeRecyclerViewAdapter = new ParticularCoffeeRecyclerView(detailsCoffeePojoClassArrayList, this, this);
            particularCoffeeDetails_recyclerview.setAdapter(particularCoffeeRecyclerViewAdapter);
        } else {
            particularCoffeeRecyclerViewAdapter.notifyDataSetChanged();
        }

        Toast.makeText(this, "Selected: " + search, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getParticularCoffeeDetails(String coffeeName, String extaraDetails,String money,String add_view) {
        if(add_view.equalsIgnoreCase("add")){

        }
        else {
            Intent intent = new Intent(HomeScreen.this,ViewDetailsActivity.class);
            intent.putExtra("coffee_name",coffeeName);
            intent.putExtra("extra_details",extaraDetails);
            intent.putExtra("money",money);

            startActivity(intent);
        }
        Toast.makeText(this, coffeeName+"__"+extaraDetails+"__"+money+"__"+add_view, Toast.LENGTH_SHORT).show();
    }
}
