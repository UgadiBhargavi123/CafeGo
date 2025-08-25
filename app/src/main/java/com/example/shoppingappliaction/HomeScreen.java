package com.example.shoppingappliaction;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
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
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class HomeScreen extends AppCompatActivity   {

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
    BottomNavigationView bottomNavigationView;
    String coffeeName,current_place;
    SearchListRecyclerVIew searchListRecyclerVIew;
    List<String> originalCoffeeList;  // ✅ Backup original list
    public static ArrayList<DetailsCoffeePojoClass> userlikeArrayList = new ArrayList<>();

    FrameLayout home_rl;

    private TextWatcher textWatcher; // ✅ Fixed initialization

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        bottomNavigationView = findViewById(R.id.navigationBar);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.home_rl, new HomeFragment())
                    .commit();
            bottomNavigationView.setSelectedItemId(R.id.like); // highlight "Home" tab
        }


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.home_rl, new HomeFragment())
                                .commit();
                        Toast.makeText(HomeScreen.this, "Home", Toast.LENGTH_SHORT).show();

                        break;
                    case R.id.like:
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.home_rl, new LikeFragment(HomeScreen.this,userlikeArrayList))
                                .commit();

                        Toast.makeText(HomeScreen.this, "Like", Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }

            ;
        });

    }

}
