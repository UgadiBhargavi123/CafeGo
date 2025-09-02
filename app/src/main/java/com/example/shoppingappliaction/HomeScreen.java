package com.example.shoppingappliaction;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

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
    public static ArrayList<DetailsCoffeePojoClass> usercartArrayList = new ArrayList<>();

    FrameLayout home_rl;

    private TextWatcher textWatcher; // ✅ Fixed initialization

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

       Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            current_place = bundle.getString("current_place");
        }

        bottomNavigationView = findViewById(R.id.navigationBar);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.home_rl, new HomeFragment(current_place))
                    .commit();
            bottomNavigationView.setSelectedItemId(R.id.home); // highlight "Home" tab
        }


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.home_rl, new HomeFragment(current_place))
                                .commit();
                        return true;

                    case R.id.like:
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.home_rl, new LikeFragment(HomeScreen.this, userlikeArrayList))
                                .commit();
                        return true;

                    case R.id.bag:
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.home_rl, new AddToCartFragment(HomeScreen.this, usercartArrayList))
                                .commit();
                        return true;
                }
                return false;
            }
        });

    }

}
