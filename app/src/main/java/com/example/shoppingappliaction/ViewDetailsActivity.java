package com.example.shoppingappliaction;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

public class ViewDetailsActivity extends AppCompatActivity {

    RelativeLayout buyNow_tv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_details);
        buyNow_tv = findViewById(R.id.buyNow_tv);

        buyNow_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent  intent = new Intent(ViewDetailsActivity.this,BuyActivity.class);
                startActivity(intent);
            }
        });
    }

}